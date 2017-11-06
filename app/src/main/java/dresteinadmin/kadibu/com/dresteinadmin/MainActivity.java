package dresteinadmin.kadibu.com.dresteinadmin;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import dresteinadmin.kadibu.com.dresteinadmin.Connectivity;

public class MainActivity extends AppCompatActivity {
    private String Url="http://drestein.in/adminapp";
    private WebView webview;
    private ProgressBar pBar;
    private FrameLayout frame;
    private Button button;
    Connectivity cd;
    private TextView t2;
    private TextView t;
    private ImageView img;
    private ProgressDialog pBar1;
    //private int i=0;
   // public static final int **LOAD_CACHE_ELSE_NETWORK**;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame=(FrameLayout)findViewById(R.id.frame);
        pBar=(ProgressBar)findViewById(R.id.progressbar);

        if (savedInstanceState!=null){
            ((WebView)findViewById(R.id.web)).restoreState(savedInstanceState);
        }

        webview=(WebView)findViewById(R.id.web);
        button=(Button)findViewById(R.id.button);
        img=(ImageView)findViewById(R.id.imageView);
        t=(TextView)findViewById(R.id.text);
        Typeface face = Typeface.createFromAsset(getAssets(),"RussoOne-Regular.ttf");
        t.setTypeface(face);
        pBar.setMax(100);
        pBar1= ProgressDialog.show(MainActivity.this,"WELCOME ADMIN","Loading...");
        cd=new Connectivity(this);
        if(cd.isConnected()){
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setAppCacheEnabled(true);
            //webview.getSettings().getCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webview.setVerticalScrollBarEnabled(false);
            webview.loadUrl(Url);
            webview.setVisibility(View.VISIBLE);

        }
        else
        {
            Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            button.setVisibility(View.VISIBLE);
            img.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            webview.setVisibility(View.GONE);

        }
            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cd.isConnected()){
                    webview.getSettings().setJavaScriptEnabled(true);
                    webview.setVerticalScrollBarEnabled(false);
                    webview.loadUrl(Url);
                    webview.setVisibility(View.VISIBLE);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    button.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);
                    t.setVisibility(View.VISIBLE);
                    webview.setVisibility(View.GONE);

                }
            }
        });
        webview.setWebViewClient(new HelpClient());
        webview.setWebChromeClient(new WebChromeClient()
        {

            public void onProgressChanged(WebView view,int progress)
            {
                frame.setVisibility(view.VISIBLE);
                pBar.setProgress(progress);

                if(progress==100){
                    frame.setVisibility(view.GONE);
                }
                super.onProgressChanged(view,progress);

            }


        });
        pBar.setProgress(0);
    }

    private class HelpClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            frame.setVisibility(view.VISIBLE);
            if(!cd.isConnected()){
                button.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);
                t.setVisibility(View.VISIBLE);
                webview.setVisibility(View.GONE);
            }else
            {
                webview.setVisibility(View.VISIBLE);
            }
            return true;

        }
        @Override
        public void onReceivedError (WebView view, int errorCode,
                                     String description, String failingUrl) {
            if (errorCode == ERROR_TIMEOUT) {
                view.stopLoading();}
                button.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);
                t.setVisibility(View.VISIBLE);
                webview.setVisibility(View.GONE);
// may not be needed
            }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(pBar1.isShowing()){
                pBar1.dismiss();
            }
        }
    }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (webview.canGoBack()) {
                    if(cd.isConnected()){
                        webview.getSettings().setJavaScriptEnabled(true);
                        webview.setVerticalScrollBarEnabled(false);
                        webview.loadUrl(Url);
                        webview.setVisibility(View.VISIBLE);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                        button.setVisibility(View.VISIBLE);
                        img.setVisibility(View.VISIBLE);
                        t.setVisibility(View.VISIBLE);
                        webview.setVisibility(View.GONE);

                    }
                    webview.goBack();
                    return true;
                }
            }
            return super.onKeyDown(keyCode, event);
        }
    }
