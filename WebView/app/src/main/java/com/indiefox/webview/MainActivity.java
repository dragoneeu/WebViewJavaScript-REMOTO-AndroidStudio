package com.indiefox.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView view;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (WebView) findViewById(R.id.webView3);
        view.getSettings().setJavaScriptEnabled(true);
        view.addJavascriptInterface(this, "DeclaracaoDoJavaScript");
        view.setWebViewClient(new MeuNavegador());
        view.loadUrl("http://SEUSITE.com/enviacomando.html");
        view.setWebChromeClient(new WebChromeClient());

    }

    // A CLASSE ABAIXO CRIADA PARA PARAR TRAVAMENTO  WEBVIEW
    private class MeuNavegador extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    // A CLASSE ABAIXO CRIADA PARA RECEBER COMANDO REMOTO DO SEU SITE
    @JavascriptInterface
    public void recebendoComando(String usuario, String id, String ativa) {
        String idRecebido = id;
        String userRecebido = usuario;
        String ativaRecebido = ativa;

        Toast.makeText(this, "Id: " + idRecebido + "Nome : " + userRecebido, Toast.LENGTH_LONG).show();
    }


}