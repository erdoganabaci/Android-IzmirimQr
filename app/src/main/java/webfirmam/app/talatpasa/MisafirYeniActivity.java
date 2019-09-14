package webfirmam.app.talatpasa;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MisafirYeniActivity extends AppCompatActivity {
    EditText emailText;
    EditText nameText;
    EditText phoneText;
    EditText passwordText;
    EditText passwordAgainText;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_yeni);
        emailText = findViewById(R.id.emailText);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        passwordText = findViewById(R.id.passwordText);
        passwordAgainText = findViewById(R.id.passwordAgainText);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }


    public void save(View view){
        registerUser();

    }

    public void registerUser (){
        final String name = nameText.getText().toString().trim();
        final String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String passwordAgain = passwordAgainText.getText().toString().trim();
        final String phone = phoneText.getText().toString().trim();
        final String cardNum = "123-456";


        if (name.isEmpty()) {
            nameText.setError(getString(R.string.input_error_name));
            nameText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailText.setError(getString(R.string.input_error_email));
            emailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError(getString(R.string.input_error_email_invalid));
            emailText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordText.setError(getString(R.string.input_error_password));
            passwordText.requestFocus();
            return;
        }
        if (passwordAgain.isEmpty()) {
            passwordAgainText.setError(getString(R.string.input_error_password));
            passwordAgainText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordText.setError(getString(R.string.input_error_password_length));
            passwordText.requestFocus();
            return;
        }
        if (passwordAgain.length() < 6) {
            passwordAgainText.setError(getString(R.string.input_error_password_length));
            passwordAgainText.requestFocus();
            return;
        }
        if (!password.equals(passwordAgain)) {
            passwordAgainText.setError(getString(R.string.input_error_passwordAgain));
            passwordAgainText.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            phoneText.setError(getString(R.string.input_error_phone));
            phoneText.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            phoneText.setError(getString(R.string.input_error_phone_invalid));
            phoneText.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    phone,
                                    cardNum
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MisafirYeniActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                        Toast.makeText(MisafirYeniActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(MisafirYeniActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}
