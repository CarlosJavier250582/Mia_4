package com.example.carlosje.mia_4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_salir;
    private Button bt_ok;
    private EditText et_password;
    private RelativeLayout layout_pass, layout_home;
    private int valor;
    private ImageButton btn_ini;
    Context mContext=MainActivity.this;
    public SharedPreferences appPreferences;
    boolean isAppInstalled = false;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);







        String[] archivos = fileList();

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        //////instalar icono en la pantalla pricipal




        setContentView(R.layout.activity_main);appPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        isAppInstalled = appPreferences.getBoolean("isAppInstalled",false);
        if(isAppInstalled==false){

            //  create short code

            Intent shortcutIntent = new Intent(getApplicationContext(),MainActivity.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Mia Ithaty");
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource
                    .fromContext(getApplicationContext(), R.drawable.icon));
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(intent);

            //Make preference true

            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
        }
















        bt_ok =(Button)findViewById(R.id.bt_ok);
        et_password=(EditText)findViewById(R.id.et_password);
        layout_pass=(RelativeLayout)findViewById(R.id.layout_pass);
        layout_home=(RelativeLayout)findViewById(R.id.layout_home);
        btn_ini=(ImageButton)findViewById(R.id.btn_ini);
















        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("v2.txt")));

            String texto = fin.readLine();
            fin.close();
            Toast.makeText(getApplicationContext(), "Te invitamos !!", Toast.LENGTH_SHORT).show();
            //Intent home = new Intent(getApplicationContext(), home.class);
            //valor=2;

            layout_pass.setVisibility(View.INVISIBLE);
            layout_home.setVisibility(View.VISIBLE);

            //startActivity(home);

        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }




        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et_password.getText().toString();
                String pass = "jazmin" ;
                if(password.equals(pass)){




                    //guasrdar file

                    String str = "yes";

                    try
                    {
                        OutputStreamWriter fout=
                                new OutputStreamWriter(
                                        openFileOutput("v2.txt", Context.MODE_PRIVATE));

                        fout.write("yes");
                        fout.close();
                    }
                    catch (Exception ex)
                    {
                        Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                    }


                    //guasrdar file


                    Toast.makeText(getApplicationContext(), "Te invitamos !!", Toast.LENGTH_SHORT).show();
                    //Intent home = new Intent(getApplicationContext(), home.class);
                    //startActivity(home);


                    //////////////////acción

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_password.getWindowToken(), 0);


                    layout_pass.setVisibility(View.INVISIBLE);
                    layout_home.setVisibility(View.VISIBLE);








                }
                else{
                    Toast.makeText(getApplicationContext(), "Password Incorrecto!!", Toast.LENGTH_SHORT).show();}
            }
        });





        btn_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Principal = new Intent(getApplicationContext(), Principal.class);
                startActivity(Principal);




            }
        });

    }

















}
