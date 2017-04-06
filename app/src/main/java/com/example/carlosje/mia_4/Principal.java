package com.example.carlosje.mia_4;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.R.color.black;


public class Principal extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ImageView im_fotos,im_gracias,im_ceremonia,im_celebra,im_mi_bautizo;
    private Button bt_mapa, bt_play, bt_pausa;
    MediaPlayer mediaPlayer;
    private  int flag, flagIm, f ;
    int posicion = 0;



    private TextView countDown;
    private CountDownTimer countDownTimer;
    private Date eventDate, presentDate;
    private Calendar calendar;
    private long initialTime;
    private String resta_dias;
    private String resta_horas;
    private long flag_dias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);







        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        im_fotos=(ImageView)findViewById(R.id.im_fotos);
        im_gracias=(ImageView)findViewById(R.id.im_gracias);
        im_ceremonia=(ImageView)findViewById(R.id.im_ceremonia);
        im_celebra=(ImageView)findViewById(R.id.im_celebra);
        bt_mapa=(Button)findViewById(R.id.bt_mapa);
        im_mi_bautizo=(ImageView)findViewById(R.id.im_mi_bautizo);
        bt_pausa=(Button)findViewById(R.id.bt_pausa);
        bt_play=(Button)findViewById(R.id.bt_play);









        im_fotos.setVisibility(View.VISIBLE);
        im_gracias.setVisibility(View.INVISIBLE);
        im_celebra.setVisibility(View.INVISIBLE);
        im_ceremonia.setVisibility(View.INVISIBLE);
        im_mi_bautizo.setVisibility(View.INVISIBLE);
        bt_mapa.setVisibility(View.INVISIBLE);





        mediaPlayer = MediaPlayer.create(this,R.raw.musica);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
        bt_pausa.setVisibility(View.VISIBLE);
        bt_play.setVisibility(View.INVISIBLE);



        ///fechas


        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);


        eventDate = calendar.getTime();
        presentDate = new Date();



        long diff = eventDate.getTime() - presentDate.getTime();
        Log.d("dateEvent", String.valueOf(eventDate));
        Log.d("dateActual", String.valueOf(presentDate));
        Log.d("diff", String.valueOf(diff));

        long seconds =(diff/1000)%60;
        long minutes=(diff/(1000*60))%60;
        long hours=(diff/(1000*60*60))%24;
        long days=(diff/(1000*60*60*24))%365;

        initialTime = diff;

        resta_dias = String.valueOf(days);
        resta_horas = String.valueOf(hours);

        flag_dias = days;

        if (flag_dias==0){
            Toast.makeText(getApplicationContext(), "Hoy es el gran día !!!!!!!!! " , Toast.LENGTH_LONG).show();

        }

        if (flag_dias> 0){
            Toast.makeText(getApplicationContext(), "Faltan " +resta_dias+" dias !!!! ", Toast.LENGTH_LONG).show();

        }


        if (flag_dias< 0){
            Toast.makeText(getApplicationContext(), "Estuvo Genial !!!!!!!!! " , Toast.LENGTH_LONG).show();

        }




        flag=1;



        //im_fotos.setImageResource(R.drawable.im_principal_1);


        flagIm =1;

        f=0;





        new CountDownTimer(3000000, 1500) {

            public void onTick(long millisUntilFinished) {


                if (flagIm ==1){
                    im_fotos.setImageResource(R.drawable.im_principal_1);
                }

                if (flagIm ==2){
                    im_fotos.setImageResource(R.drawable.im_principal_2);
                }

                if (flagIm ==3){
                    im_fotos.setImageResource(R.drawable.im_principal_3);

                }


                flagIm = flagIm +1;

                if(flagIm ==4){
                    flagIm =1;
                }





            }

            public void onFinish() {

            }
        }.start();





        bt_pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posicion = mediaPlayer.getCurrentPosition();

                mediaPlayer.pause();
                bt_play.setVisibility(View.VISIBLE);
                bt_pausa.setVisibility(View.INVISIBLE);

            }
        });


        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.seekTo(posicion);
                mediaPlayer.start();
                bt_play.setVisibility(View.INVISIBLE);
                bt_pausa.setVisibility(View.VISIBLE);
            }
        });







    }





    @Override
    public void onBackPressed() {

        if (flag==1) {


            Toast.makeText(getApplicationContext(), "Adios !!" , Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onStop();

        }

        if (flag==0) {
            flag=1;
            im_mi_bautizo.setVisibility(View.INVISIBLE);
            im_fotos.setVisibility(View.VISIBLE);
            im_gracias.setVisibility(View.INVISIBLE);
            im_ceremonia.setVisibility(View.INVISIBLE);
            bt_mapa.setVisibility(View.INVISIBLE);
            im_celebra.setVisibility(View.INVISIBLE);




        }


    }



//////////////////////////////////// al precionar Boton HOME

    @Override
    protected void onPause() {


        posicion = mediaPlayer.getCurrentPosition();

        mediaPlayer.stop();
        bt_play.setVisibility(View.VISIBLE);
        bt_pausa.setVisibility(View.INVISIBLE);

        Toast.makeText(getApplicationContext(), "Adios !!" , Toast.LENGTH_SHORT).show();
        finish();
       //Intent intent = new Intent(Intent.ACTION_MAIN);
       //intent.addCategory(Intent.CATEGORY_HOME);
       //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       //startActivity(intent);

        //Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(MainActivity);

        super.onPause();


    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {






        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        setOverflowButtonColor(this, getResources().getColor(R.color.Letras));







        return true;





    }


    ////////////////////////////cambia color de puntitos

    public static void setOverflowButtonColor(final Activity activity, final int color) {
        final String overflowDescription = activity.getString(R.string.abc_action_menu_overflow_description);
        final ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ArrayList<View> outViews = new ArrayList<View>();
                decorView.findViewsWithText(outViews, overflowDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
                if (outViews.isEmpty()) {
                    return;
                }
                AppCompatImageView overflow = (AppCompatImageView) outViews.get(0);
                overflow.setColorFilter(color);

            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.it_gracias) {

            mViewPager.setBackgroundResource(R.drawable.fondo_angel);

            im_fotos.setVisibility(View.INVISIBLE);
            im_ceremonia.setVisibility(View.INVISIBLE);
            im_gracias.setVisibility(View.VISIBLE);
            bt_mapa.setVisibility(View.INVISIBLE);
            im_celebra.setVisibility(View.INVISIBLE);
            im_mi_bautizo.setVisibility(View.INVISIBLE);
            flag=0;

            return true;







        }


        if (id == R.id.it_bautizo) {
            mViewPager.setBackgroundResource(R.drawable.fondo_angel);
            im_mi_bautizo.setVisibility(View.VISIBLE);
            im_fotos.setVisibility(View.INVISIBLE);
            im_gracias.setVisibility(View.INVISIBLE);
            im_ceremonia.setVisibility(View.INVISIBLE);
            bt_mapa.setVisibility(View.INVISIBLE);
            im_celebra.setVisibility(View.INVISIBLE);
            flag=0;

            return true;



        }

        if (id == R.id.it_mia) {
            mViewPager.setBackgroundResource(R.drawable.fondo);
            im_mi_bautizo.setVisibility(View.INVISIBLE);
            im_fotos.setVisibility(View.VISIBLE);
            im_gracias.setVisibility(View.INVISIBLE);
            im_ceremonia.setVisibility(View.INVISIBLE);
            bt_mapa.setVisibility(View.INVISIBLE);
            im_celebra.setVisibility(View.INVISIBLE);

            flag=1;

            return true;

        }


        if (id == R.id.it_cereonia) {
            mViewPager.setBackgroundResource(R.drawable.fondo_angel);

            im_fotos.setVisibility(View.INVISIBLE);
            im_mi_bautizo.setVisibility(View.INVISIBLE);
            im_gracias.setVisibility(View.INVISIBLE);
            im_ceremonia.setVisibility(View.VISIBLE);
            bt_mapa.setVisibility(View.VISIBLE);
            im_celebra.setVisibility(View.INVISIBLE);
            flag=0;
            String direc="https://www.google.com.mx/maps/place/Misi%C3%B3n+Pastoral+Sagrado+Coraz%C3%B3n+de+Jes%C3%BAs/@19.6900606,-99.207423,17z/data=!3m1!4b1!4m5!3m4!1s0x85d21fe9e97b8ae9:0xd7464ce0dbbcecf!8m2!3d19.6900606!4d-99.2052343";
            bt_mapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    posicion = mediaPlayer.getCurrentPosition();
                    bt_pausa.setVisibility(View.INVISIBLE);
                    bt_play.setVisibility(View.VISIBLE);
                    mediaPlayer.pause();



                    Uri uri = Uri.parse("https://www.google.com.mx/maps/place/Misi%C3%B3n+Pastoral+Sagrado+Coraz%C3%B3n+de+Jes%C3%BAs/@19.6900606,-99.207423,17z/data=!3m1!4b1!4m5!3m4!1s0x85d21fe9e97b8ae9:0xd7464ce0dbbcecf!8m2!3d19.6900606!4d-99.2052343");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);


                }
            });




            return true;



        }


        if (id == R.id.it_celebracion) {
            mViewPager.setBackgroundResource(R.drawable.fondo_angel);

            im_fotos.setVisibility(View.INVISIBLE);
            im_mi_bautizo.setVisibility(View.INVISIBLE);
            im_gracias.setVisibility(View.INVISIBLE);
            im_ceremonia.setVisibility(View.INVISIBLE);
            bt_mapa.setVisibility(View.VISIBLE);
            im_celebra.setVisibility(View.VISIBLE);
            flag=0;
            String direc="https://www.google.com.mx/maps/dir//Fuentes+de+Abetos,+Ejido+de+San+Francisco+Tepojaco,+54745+Cuautitl%C3%A1n+Izcalli,+M%C3%A9x./@19.6558027,-99.2528528,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x85d21ee6fa0935e3:0x9ec880956265e387!2m2!1d-99.2529168!2d19.6546076";
            bt_mapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    posicion = mediaPlayer.getCurrentPosition();
                    bt_pausa.setVisibility(View.INVISIBLE);
                    bt_play.setVisibility(View.VISIBLE);
                    mediaPlayer.pause();


                    Uri uri = Uri.parse("https://www.google.com.mx/maps/dir//Fuentes+de+Abetos,+Ejido+de+San+Francisco+Tepojaco,+54745+Cuautitl%C3%A1n+Izcalli,+M%C3%A9x./@19.6558027,-99.2528528,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x85d21ee6fa0935e3:0x9ec880956265e387!2m2!1d-99.2529168!2d19.6546076");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);


                }
            });




            return true;



        }



        return super.onOptionsItemSelected(item);





    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }






}
