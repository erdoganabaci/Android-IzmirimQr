package webfirmam.app.talatpasa;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity {
    private WebView webView;
    private CustomWebViewClient webViewClient;
    private String Url ;
    Handler handler = new Handler();
    boolean doubleBackToExitPressedOnce = false;

    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        if(getIntent() != null)
        {
            String info = getIntent().getStringExtra("info");

            String dataUrl = getIntent().getStringExtra("info");

            Url = dataUrl;
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Yükleniyor...");
            webViewClient = new CustomWebViewClient();
            webView = findViewById(R.id.webview1);//webview mızı xml anasayfa.xml deki webview bağlıyoruz
            webView.getSettings().setBuiltInZoomControls(true); //zoom yapılmasına izin verir
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(webViewClient); //oluşturduğumuz webViewClient objesini webViewımıza set ediyoruz
            webView.loadUrl(Url);

        }

    }


    private class CustomWebViewClient extends WebViewClient {

        //Alttaki methodların hepsini kullanmak zorunda deilsiniz
        //Hangisi işinize yarıyorsa onu kullanabilirsiniz.
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) { //Sayfa yüklenirken çalışır
            super.onPageStarted(view, url, favicon);

            if(!mProgressDialog.isShowing())//mProgressDialog açık mı kontrol ediliyor
            {

                try{
                    mProgressDialog.show();//mProgressDialog açık değilse açılıyor yani gösteriliyor ve yükleniyor yazısı çıkıyor

                }catch (Exception e){
                    e.printStackTrace();
                }
                handler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            mProgressDialog.dismiss();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, 5000);

            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {//sayfamız yüklendiğinde çalışıyor.
            super.onPageFinished(view, url);

            if(mProgressDialog.isShowing()){//mProgressDialog açık mı kontrol açıksa kapat ama sayfa yüklenmesi tamalanırsa
                try {
                    mProgressDialog.dismiss();//mProgressDialog açıksa kapatılıyor

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Bu method açılan sayfa içinden başka linklere tıklandığında açılmasına yarıyor.
            //Bu methodu override etmez yada edip içini boş bırakırsanız ilk url den açılan sayfa dışında başka sayfaya geçiş yapamaz

            view.loadUrl(url);//yeni tıklanan url i açıyor
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {

            //	if(errorCode !=null){
            //		Timeout
            //	} şeklinde kullanabilirsiniz



        }
    }
    public void onBackPressed() //Android Back Buttonunu Handle ediyoruz. Back butonu bir önceki sayfaya geri dönecek
    {
        if(webView.canGoBack()){//eğer varsa bir önceki sayfaya gidecek
            webView.goBack();
        }else if (doubleBackToExitPressedOnce){//Sayfa yoksa uygulamadan çıkacak
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Çıkmak için 2 kere basınız.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
