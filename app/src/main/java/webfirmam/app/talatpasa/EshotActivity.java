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

public class EshotActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshot);

        listView = findViewById(R.id.listViewEshot);

        String mTitle[] = {"OTOBÜS", "İZBAN", "VAPUR","İZMİR METRO"};
        //String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
        int images[] = {R.drawable.bus, R.drawable.izban, R.drawable.vapur,R.drawable.metro};
        // so our images and other things are set in array
        //int imagesRigt[] = {R.drawable.misafiryolcu, R.drawable.mevcutyolcu, R.drawable.sistemegiris};


        EshotActivity.MyAdapter adapter = new EshotActivity.MyAdapter(this, mTitle, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(EshotActivity.this,ActivityOne.class);

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(EshotActivity.this, "Otobüs Saatleri", Toast.LENGTH_LONG).show();
                    intent.putExtra("info","https://www.eshot.gov.tr/UlasimKartlari.aspx");
                    startActivity(intent);

                }
                if (position ==  1) {
                    Toast.makeText(EshotActivity.this, "İzban Saatleri", Toast.LENGTH_SHORT).show();
                    intent.putExtra("info","http://www.izban.com.tr/sayfalar/SeferSaatleri.aspx?MenuId=22");
                    startActivity(intent);

                }
                if (position ==  2) {
                    Toast.makeText(EshotActivity.this, "Vapur Saatleri", Toast.LENGTH_SHORT).show();
                    intent.putExtra("info","https://www.izdeniz.com.tr/(X(1)S(fxmhp5tnvefvpo3jk50t3bz3))/default.aspx?AspxAutoDetectCookieSupport=1");
                    startActivity(intent);
                }
                if (position ==  3) {

                    Toast.makeText(EshotActivity.this, "İzmir Metro Saatleri", Toast.LENGTH_SHORT).show();
                    intent.putExtra("info","https://www.izmirmetro.com.tr/SeferPlani/35");
                    startActivity(intent);
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
