package m.sambit.kisannetworkchallenge.activity;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.model.Contacts;

/**
 *  This activity shows the details of selected name from Contact List from fragment {@link m.sambit.kisannetworkchallenge.fragments.ContactFragment}{@link MainActivity}
 */
public class ContactDetailsActivity extends AppCompatActivity {

    private TextInputEditText nameInput, phoneInput;
    private MaterialButton sendMessageButton;
    private String phone, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        name = getIntent().getExtras().getString("name");
        phone = getIntent().getExtras().getString("phone");

        nameInput = findViewById(R.id.contact_name_input);
        phoneInput = findViewById(R.id.contact_phone_input);
        sendMessageButton = findViewById(R.id.send_message_button);

        nameInput.setText(name);
        phoneInput.setText(String.valueOf(phone));

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otpActivity = new Intent(ContactDetailsActivity.this, OTPActivity.class);
                otpActivity.putExtra("phone", phone);
                otpActivity.putExtra("name", name);
                otpActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(otpActivity);
            }
        });
    }
}
