package cl.kunder.webview;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.cordova.CordovaActivity;

import java.util.ArrayList;

public class WebViewLastActivity extends CordovaActivity {

    Activity activity2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity2 = this;
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");



        loadUrl("file:///android_asset/www/" + url);
    }

    /**
     * Revisa si existe el plugin cordova-plugin-wkwebview-polyfill
     * @return boolean
     */
    private boolean isPluginWkwebviewPolyfillActive() {
        return classExists("com.tkyj.cdv.DR") || classExists("com.tkyaji.cordova.DecryptResource");
    }


    /**
     * Revisa si existe la clase con nombre className dentro del
     * contexto donde esté integrado este plugin
     * @param className Nombre de clase
     * @return boolean
     */
    private boolean classExists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
