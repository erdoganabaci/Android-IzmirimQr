package webfirmam.app.talatpasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MisafirYeni2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_yeni2);
    }

    public void save2(View view){
        Intent intent = new Intent(MisafirYeni2Activity.this,MisafirYeni2OnayActivity.class);
        startActivity(intent);
        Toast.makeText(MisafirYeni2Activity.this, "Üyelik İşleminiz Tamamlanmıştır", Toast.LENGTH_SHORT).show();

    }
}
