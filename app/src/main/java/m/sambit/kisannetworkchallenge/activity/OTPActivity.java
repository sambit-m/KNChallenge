package m.sambit.kisannetworkchallenge.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.button.MaterialButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import m.sambit.kisannetworkchallenge.room.DatabaseClient;
import m.sambit.kisannetworkchallenge.room.MessageList;
import m.sambit.kisannetworkchallenge.util.Endpoints;
import m.sambit.kisannetworkchallenge.util.Utility;
import m.sambit.kisannetworkchallenge.R;

/**
 * SMS to send from this activity
 * Calling TextLocal API using Volley
 */
public class OTPActivity extends AppCompatActivity {

    private TextInputEditText messageInput;
    private MaterialButton sendButton;
    private String phone, sms, name;
    private int randomPIN;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        phone = getIntent().getExtras().getString("phone");
        name = getIntent().getExtras().getString("name");

        coordinatorLayout = findViewById(R.id.layout);
        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);

        sms = "Hi. Your OTP is: ";

        //Generate random six-digit number
        randomPIN = (int) (Math.random() * 900000) + 100000;
        sms += String.valueOf(randomPIN);

        messageInput.setText(sms);

        final View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
            }
        };

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check for internet connection before calling API
                //Showing snackbar if there no internet
                if (!Utility.checkInternet(OTPActivity.this)) {
                    // Define the click listener as a member
                    // Pass in the click listener when displaying the Snackbar
                    Snackbar.make(coordinatorLayout, "Connect to internet!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Dismiss", myOnClickListener)
                            .show(); // Don’t forget to show!
                } else
                    sendSMS();
            }
        });
    }

    /**
     * Method is called to send sms using textlocal api using Volley
     */
    private void sendSMS() {
        String url = Endpoints.SMS_GATEWAY + "&numbers=" + phone + "&message=" + sms;
        final ProgressDialog pDialog = new ProgressDialog(OTPActivity.this);
        pDialog.setMessage("Sending...");
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String status = new JSONObject(response).optString("status");

                            if (status.equals("success")) {
                                Toast.makeText(OTPActivity.this, status, Toast.LENGTH_LONG).show();
                                saveMessage();
                            } else {

                                JSONArray error = new JSONObject(response).getJSONArray("errors");
                                JSONObject jsonObject = error.getJSONObject(0);
                                String message = jsonObject.optString("message");
                                Utility.showDialog(message, OTPActivity.this);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Utility.showDialog(error.getMessage(), OTPActivity.this);
                        // hide the progress dialog
                        pDialog.dismiss();

                    }
                }
        );
        Volley.newRequestQueue(OTPActivity.this).add(postRequest);
    }


    /**
     * Method is called to store message in room database
     */
    private void saveMessage() {

        class saveMessage extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a message
                MessageList messageList = new MessageList();
                messageList.setName(name);
                messageList.setOtp(String.valueOf(randomPIN));
                messageList.setDateTime(Utility.getCurrentDateTime());

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .messageDao()
                        .insert(messageList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }

        saveMessage saveMessage = new saveMessage();
        saveMessage.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainActivityIntent = new Intent(OTPActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
