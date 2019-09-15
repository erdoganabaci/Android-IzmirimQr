package webfirmam.app.talatpasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MevcutKartYeni2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mevcut_kart_yeni2);
    }

    public void saveMevcut(View view){
        Intent intent = new Intent(MevcutKartYeni2Activity.this,MevcutKartYeni2OnayActivity.class);
        startActivity(intent);
        Toast.makeText(MevcutKartYeni2Activity.this, "Üyelik İşleminiz Tamamlanmıştır", Toast.LENGTH_SHORT).show();
    }
}
