package webfirmam.app.talatpasa;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("tıklandı"+finalI);
                    Intent intent = new Intent(MainActivity.this,ActivityOne.class);

                    if (finalI == 0){
                        //3 parçalı olcak izban otobüs vapur ayır
                        Intent intentEshot = new Intent(MainActivity.this,EshotActivity.class);
                        startActivity(intentEshot);
                        //intent.putExtra("info","http://www.eshot.gov.tr/tr/UlasimSaatleri/288?AspxAutoDetectCookieSupport=1");
                        //startActivity(intent);
                    }else if (finalI == 1){
                        intent.putExtra("info","https://online.eshot.gov.tr/giris#TL_Yukle");
                        startActivity(intent);
                    }else if (finalI == 2){
                        intent.putExtra("info","https://online.eshot.gov.tr/giris?AspxAutoDetectCookieSupport=1#Bakiye_Sorgula");
                        startActivity(intent);
                    }else if (finalI == 3){
                        //qr kodları

                        Intent intentQR = new Intent(MainActivity.this,QrMenuActivity.class);
                        startActivity(intentQR);
                        //Intent intentQR = new Intent(MainActivity.this,QRActivity.class);
                        //startActivity(intentQR);


                    }else if (finalI == 4){
                        Toast.makeText(getApplicationContext(),"Proje Hakkında",Toast.LENGTH_LONG).show();
                        Intent intentQR = new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(intentQR);

                    }else if (finalI == 5){
                        intent.putExtra("info","http://izmiraekoo.meb.k12.tr/");
                        startActivity(intent);

                    }
                }
            });
        }
    }
}
