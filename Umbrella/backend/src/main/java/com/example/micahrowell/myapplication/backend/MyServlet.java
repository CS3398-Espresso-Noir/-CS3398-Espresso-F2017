package com.example.micahrowell.myapplication.backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    static Logger Log = Logger.getLogger("com.example.micahrowell.myapplication.backend.MyServlet");

    StringBuffer json = new StringBuffer();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        URL url = new URL("http://api.wunderground.com/api/d59e8a61df5f52ab/conditions/q/TX/San_Marcos.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        JSONObject jo = new JSONObject(json.toString());

        req.setAttribute("joke", jo.getJSONObject("value").getString("joke"));
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    /*
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Log.info("Sending the list email.");

        String outString;
        outString = "<p>Sending the list email.</p><p><strong>Note:</strong> ";
        outString = outString.concat("the servlet must be deployed to App Engine in order to ");
        outString = outString.concat("send the email. Running the server locally writes a message ");
        outString = outString.concat("to the log file instead of sending an email message.</p>");

        resp.getWriter().println(outString);

        // Note: Ensure that the [PRIVATE_KEY_FILENAME].json has read
        // permissions set.
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/[PRIVATE_KEY_FILE]"))
                .setDatabaseUrl("https://[FIREBASE_PROJECT_ID].firebaseio.com/")
                .build();

        try {
            FirebaseApp.getInstance();
        }
        catch (Exception error){
            Log.info("doesn't exist...");
        }

        try {
            FirebaseApp.initializeApp(options);
        }
        catch(Exception error){
            Log.info("already exists...");
        }

        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("userCities");

        // This fires when the servlet first runs, returning all the existing values
        // only runs once, until the servlet starts up again.
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                Log.info("new value: "+ document);

                String todoText = "Don't forget to...\n\n";

                Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();

                while(children.hasNext()){
                    DataSnapshot childSnapshot = (DataSnapshot) children.next();
                    todoText = todoText + " * " + childSnapshot.getValue().toString() + "\n";
                }

                // Now send the email

                // Note: When an application running in the development server calls the Mail
                // service to send an email message, the message is printed to the log.
                // The Java development server does not send the email message.

                // You can test the email without waiting for the cron job to run by
                // loading http://[FIREBASE_PROJECT_ID].appspot.com/send-email in your browser.

                Properties props = new Properties();
                Session session = Session.getDefaultInstance(props, null);
                try {
                    Message msg = new MimeMessage(session);
                    //Make sure you substitute your project-id in the email From field
                    msg.setFrom(new InternetAddress("reminder@[FIREBASE_PROJECT_ID].appspotmail.com",
                            "Reminder"));
                    msg.addRecipient(Message.RecipientType.TO,
                            new InternetAddress("[EMAIL@GMAIL.COM]", "Recipient"));
                    msg.setSubject("Things to do today");
                    msg.setText(todoText);
                    Transport.send(msg);
                } catch (MessagingException | UnsupportedEncodingException e) {
                    Log.warning(e.getMessage());
                }

                // Note: in a production application you should replace the hard-coded email address
                // above with code that populates msg.addRecipient with the app user's email address.
            }

            @Override
            public void onCancelled(DatabaseError error){
                System.out.println("Error: "+error);
            }
        });
    }
    */
}