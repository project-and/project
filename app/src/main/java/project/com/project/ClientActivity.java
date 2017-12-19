package project.com.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener{
private DatabaseReference databaseReference;
private EditText Name;
private Spinner spinner1;
private EditText service;
private EditText phone;
private EditText city;
private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Name = (EditText) findViewById(R.id.Name);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        service = (EditText) findViewById(R.id.service);
        phone = (EditText) findViewById(R.id.phone);
        city = (EditText) findViewById(R.id.city);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
    }
    private void saveUserInformation(){
        String Name = EditText.getText().toString().trim();
        Spinner spinner1 = Spinner.getSpinner().toString().trim();
        String service = EditText.getText().toString().trim();
        String phone = EditText.getText().toString().trim();
        String city = EditText.getText().toString().trim();
        UserInformation userInformation = new UserInformation(Name, spinner1, service, phone, city);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Save...", Toast.LENGTH_LONG).show();
    }




    @Override
    public void onClick(View view) {
        if(view == btnSave){
            saveUserInformation();
        }


    }
}
