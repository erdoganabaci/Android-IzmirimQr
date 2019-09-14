package webfirmam.app.talatpasa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QrMenuActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_menu);
        listView = findViewById(R.id.listView);

        String mTitle[] = {"Misafir/Yeni Yolcu Üyelik İşlemleri", "Mevcut Yolcu Üyelik İşlemleri", "Sisteme Giriş","Karrekod Okuyucu İle Ödeme"};
        //String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
        int images[] = {R.drawable.misafiryolcu, R.drawable.mevcutyolcu, R.drawable.sistemegiris,R.drawable.sistemegiris};
        // so our images and other things are set in array
        int imagesRigt[] = {R.drawable.misafiryolcu, R.drawable.mevcutyolcu, R.drawable.sistemegiris};


        MyAdapter adapter = new MyAdapter(this, mTitle, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(QrMenuActivity.this, "Misafir/Yeni Yolcu Üyelik İşlemleri", Toast.LENGTH_SHORT).show();
                    Intent intentQR = new Intent(QrMenuActivity.this,MisafirYeniActivity.class);
                    startActivity(intentQR);
                }
                if (position ==  1) {
                    Toast.makeText(QrMenuActivity.this, "Mevcut Yolcu Üyelik ", Toast.LENGTH_SHORT).show();
                }
                if (position ==  2) {
                    Toast.makeText(QrMenuActivity.this, "Sisteme Girişi", Toast.LENGTH_SHORT).show();
                    Intent loginQr = new Intent(QrMenuActivity.this,LoginActivity.class);
                    startActivity(loginQr);
                }
                if (position ==  3) {
                    Toast.makeText(QrMenuActivity.this, "Karekod Description", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // so item click is done now check list view

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        //String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[],int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
           // this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            //TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            //myDescription.setText(rDescription[position]);




            return row;
        }
    }
}
