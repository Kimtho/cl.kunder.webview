package cl.kunder.webview;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.cordova.CordovaActivity;

public class WebViewActivity extends CordovaActivity {
    static Dialog dialog;
    static Activity activity2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Aqui debo crear el loading
        activity2 = this;
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        Boolean shouldShowLoading = false;
        try{
            shouldShowLoading = b.getBoolean("shouldShowLoading");
        }
        catch(Exception e){

        }
        showLoading();
        activity2.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        loadUrl("file:///android_asset/www/" + url);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                hideLoading();
                activity2.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        }, 700);


    }

    public static boolean showLoading() {
        // Loading spinner
        activity2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog = new Dialog(activity2, android.R.style.Theme_Light_NoTitleBar_Fullscreen);

                RelativeLayout layoutPrincipal = new RelativeLayout(activity2);

                layoutPrincipal.setBackgroundColor(Color.parseColor("#00cbff"));

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);


                dialog.setContentView(layoutPrincipal);


                dialog.show();
                dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        });

        return true;
    }

    public static boolean hideLoading() {
        // Loading spinner
        activity2.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.hide();

            }
        });
        return true;
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
