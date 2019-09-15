package webfirmam.app.talatpasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }
    public void loginQr(View view){
        Intent intent = new Intent(Login2Activity.this,LoginQrOdemeActivity.class);
        startActivity(intent);
        Toast.makeText(Login2Activity.this, "Qr Kod Ödeme İşleminiz Tamamlanmıştır", Toast.LENGTH_LONG).show();

    }
}
