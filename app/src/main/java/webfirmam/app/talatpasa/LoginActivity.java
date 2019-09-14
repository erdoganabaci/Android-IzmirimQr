package webfirmam.app.talatpasa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailTextLogin;
    EditText passwordTextLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailTextLogin = findViewById(R.id.emailTextLogin);
        passwordTextLogin = findViewById(R.id.passwordTextLogin);
        emailTextLogin = findViewById(R.id.emailTextLogin);

        auth=FirebaseAuth.getInstance();


    }
    public void login(View view){
        String email = emailTextLogin.getText().toString();
        String password = passwordTextLogin.getText().toString();

        Toast.makeText(getApplicationContext(),"Login Edildi",Toast.LENGTH_LONG).show();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //İşlem başarısız olursa kullanıcıya bir Toast mesajıyla bildiriyoruz.
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

                //İşlem başarılı olduğu takdir de giriş yapılıp MainActivity e yönlendiriyoruz.
                else {
                    startActivity(new Intent(LoginActivity.this, LoginQrCodeActivity.class));
                    Toast.makeText(LoginActivity.this, "Hoşgeldin "+auth.getCurrentUser().getEmail(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
