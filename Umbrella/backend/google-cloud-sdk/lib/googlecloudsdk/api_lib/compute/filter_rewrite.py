# Copyright 2017 Google Inc. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""Compute resource filter expression rewrite backend.

Refer to the core.resource.resource_expr_rewrite docstring for expression
rewrite details.

Cloud SDK filter expressions are One Platform compliant. Compute API
filter expressions have limited functionality and are not compatible with
One Platform. This module rewrites client-side filter expressions to compute
server-side filter expressions. Both the client-side and server-side
expressions must be applied.

Compute API filter expressions have these operators:
  eq
  ne
and these operand types:
  string
  bool
  integer
  float

eq and ne on string operands treat the operand as a regular expression pattern.
The patterns must completely match the entire string (they are implicitly
anchored).  The ~ operator is implicitly unanchored, so there are some gyrations
in the ~ and !~ RE rewrite code to handle that.  Multiple terms can be AND'ed
by enclosing adjacent terms in parenthesis.

Explicit AND, OR or NOT operators are not supported.

To use in compute Run(args) methods:

  from googlecloudsdk.api_lib.compute import filter_rewrite
    ...
  args.filter, backend_filter = filter_rewrite.Rewriter().Rewrite(args.filter)
    ...
    filter=backend_filter,
    ...
  )

When compute becomes One Platform compliant this module can be discarded and
the compute code can simply use

  Request(
    ...
    filter=args.filter,
    ...
  )
"""

import re

from googlecloudsdk.core.resource import resource_expr_rewrite


def ConvertEQPatternToFullMatch(pattern):
  r"""Returns filter = pattern converted to a full match RE pattern.

  This function converts pattern such that the compute filter expression
    subject eq ConvertEQPatternToFullMatch(pattern)
  matches (the entire subject matches) IFF
    re.search(r'\b' + re.escape(pattern) + r'\b', subject)
  matches (pattern matches anywhere in subject).

  Args:
    pattern: A filter = pattern that partially matches the subject string.

  Returns:
    The converted = pattern suitable for the compute eq filter match operator.
  """
  # re.escape() also escapes " which is a good thing in this context.
  return r'".*\b{pattern}\b.*"'.format(pattern=re.escape(pattern))


def ConvertHASPatternToFullMatch(pattern):
  r"""Returns filter : pattern converted to a full match RE pattern.

  This function converts pattern such that the compute filter expression
    subject eq ConvertREPatternToFullMatch(pattern)
  matches (the entire subject matches) IFF
    re.search(r'\b' + re.escape(pattern) + r'\b', subject)  # no trailing '*'
    re.search(r'\b' + re.escape(pattern[:-1]), subject)     # trailing '*'
  matches (pattern matches anywhere in subject).

  Args:
    pattern: A filter : pattern that partially matches the subject string.

  Returns:
    The converted : pattern suitable for the compute eq filter match operator.
  """
  left = r'.*\b'
  if pattern.endswith('*'):
    # OnePlatform : operator unanchored right.
    pattern = pattern[:-1]
    right = '.*'
  else:
    right = r'\b.*'
  # re.escape() also escapes " which is a good thing in this context.
  return r'"{left}{pattern}{right}"'.format(
      left=left, pattern=re.escape(pattern), right=right)


def ConvertREPatternToFullMatch(pattern, wordmatch=False):
  """Returns filter ~ pattern converted to a full match RE pattern.

  This function converts pattern such that the compute filter expression
    subject eq ConvertREPatternToFullMatch(pattern)
  matches (the entire subject matches) IFF
    re.search(pattern, subject)  # wordmatch=False
  matches (pattern matches anywhere in subject).

  Args:
    pattern: A RE pattern that partially matches the subject string.
    wordmatch: True if ^ and $ anchors should be converted to word boundaries.

  Returns:
    The converted ~ pattern suitable for the compute eq filter match operator.
  """
  if wordmatch:
    # Convert ^ and $ to \b except if they are in a [...] character class or are
    # \^ or \$ escaped.

    # 0: not in class, 1: first class char, 2: subsequent class chars
    cclass = 0
    # False: next char is not escaped, True: next char is escaped (literal)
    escape = False
    full = []
    for c in pattern:
      if escape:
        escape = False
      elif c == '\\':
        escape = True
      elif cclass:
        if c == ']':
          if cclass == 1:
            cclass = 2
          else:
            cclass = 0
        elif c != '^':
          cclass = 2
      elif c == '[':
        cclass = 1
      elif c in ('^', '$'):
        c = r'\b'
      full.append(c)
    pattern = ''.join(full)
  return '".*(' + pattern.replace('"', r'\"') + ').*"'


class Rewriter(resource_expr_rewrite.Backend):
  """Compute resource filter expression rewriter backend.

  This rewriter builds a list of tokens that is joined into a string at the
  very end. This makes it easy to apply the NOT and - logical inversion ops.
  """

  _INVERT = {'eq': 'ne', 'ne': 'eq'}

  def Rewrite(self, expression, defaults=None):
    frontend, backend_tokens = super(Rewriter, self).Rewrite(
        expression, defaults=defaults)
    backend = ' '.join(backend_tokens) if backend_tokens else None
    return frontend, backend

  def RewriteNOT(self, expr):
    if expr[0] == '(':
      return None
    expr[1] = self._INVERT[expr[1]]
    return expr

  def RewriteAND(self, left, right):
    return ['('] + left + [')', '('] + right + [')']

  def RewriteOR(self, left, right):
    return None

  def RewriteTerm(self, key, op, operand):
    """Rewrites <key op operand>."""
    if isinstance(operand, list):
      # foo:(bar,baz) needs OR
      return None

    try:
      float(operand)
      numeric = True
    except ValueError:
      if operand.lower() in ('true', 'false'):
        operand = operand.lower()
        numeric = True
      else:
        numeric = False

    if op == ':':
      if not numeric:
        operand = ConvertHASPatternToFullMatch(operand)
      op = 'eq'
    elif op in ('=', '!='):
      op = 'ne' if op.startswith('!') else 'eq'
      if not numeric:
        operand = ConvertEQPatternToFullMatch(operand)
    elif op in ('~', '!~'):
      # All re match operands are strings.
      op = 'ne' if op.startswith('!') else 'eq'
      operand = ConvertREPatternToFullMatch(
          operand, wordmatch=key in ('region', 'zone'))
    else:
      return None

    return [key, op, operand]
