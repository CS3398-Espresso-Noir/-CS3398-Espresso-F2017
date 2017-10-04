var functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);
functions.auth.user().onCreate(event => {
	admin.database().ref('users/' + user.uid).set(userObject);
});
