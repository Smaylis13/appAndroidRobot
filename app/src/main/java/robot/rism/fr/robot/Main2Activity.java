package robot.rism.fr.robot;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private String mIP = "";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mIP = getIntent().getStringExtra("IP");
        Log.i("IPOPO", "==> " + mIP);
        final ImageButton avant = (ImageButton) findViewById(R.id.avant);
        final ImageButton arriere = (ImageButton) findViewById(R.id.arriere);
        final ImageButton gauche = (ImageButton) findViewById(R.id.gauche);
        final ImageButton droite = (ImageButton) findViewById(R.id.droite);


        /*--------------------------------------------------*/
        avant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) { //changement de couleur image
                    avant.setImageResource(R.drawable.avant2);
                    Log.i("TEST", "1 : ");
                    MyTask myTask = new MyTask();
                    myTask.execute("http://192.168.43.155/AvancerToutDroit.php");
                    Log.i("TEST", "2 : ");

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    avant.setImageResource(R.drawable.avant);

                }
                return true;
            }
        });

                /*--------------------------------------------------*/
        arriere.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    arriere.setImageResource(R.drawable.arriere2);
                    MyTask myTask = new MyTask();

                    myTask.execute("http://192.168.43.155/AvancerMarcheArriere.php");

                    //connection(mIP + "/AvancerToutDroit.php");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    arriere.setImageResource(R.drawable.arriere);

                }
                return true;
            }
        });

                /*--------------------------------------------------*/
        gauche.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gauche.setImageResource(R.drawable.backward2);
                    MyTask myTask = new MyTask();
                    myTask.execute("http://192.168.43.155/AvancerGauche.php");

                    //connection(mIP + "/AvancerToutDroit.php");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    gauche.setImageResource(R.drawable.backward);

                }
                return true;
            }
        });

                /*--------------------------------------------------*/
        droite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    droite.setImageResource(R.drawable.forward2);
                    MyTask myTask = new MyTask();
                    myTask.execute("http://192.168.43.155/AvancerDroit.php");
                    //connection(mIP + "/AvancerToutDroit.php");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    droite.setImageResource(R.drawable.forward);

                }
                return true;
            }
        });


    }

    /*void connection(String pUrl) {
        URL url = null;
        try {
            url = new URL("192.168.43.155/AvancerToutDroit.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.i("TEST","Av ----- 1");
            InputStream in = urlConnection.getInputStream();

            Log.i("TEST","test : Av -----2 ");


        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("TEST", "Erreur : ");

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TEST", "Erreur 2: ");

        }
    }*/
    private class MyTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(Main2Activity.this, "", "Veuillez patienter...");

        }
        @Override
        protected Object doInBackground(Object[] params) {
            String lUrl = (String) params[0];
            Log.i("TEST","URL : "+lUrl);
            //URL url = null;
           /*         url = new URL("http://192.168.43.155/AvancerToutDroit.php");
                    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                    InputStream in = httpConn.getInputStream();
                // wrap the urlconnection in a bufferedreader
                                      Log.i("TEST","Avant dec");

                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                                      Log.i("TEST","Apres dec");

                    String line;
                  // read from the urlconnection via the bufferedreader
                  while ((line = bufferedReader.readLine()) != null)
                  {
                      Log.i("TEST","Line : "+ line);
                  }
                  bufferedReader.close();
                Log.i("TEST"," buffer ferme : ");
*/
                //...
            /*URL url = null;
            try {
                url = new URL("http://192.168.43.155/AvancerToutDroit.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection conn = null;
            try {
                conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    Log.i("TEST","Line : " +line);

                    break;
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("TEST","Erreur : " +e.toString());
            }*/
            try {
                //"http://192.168.43.155/AvancerToutDroit.php"
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(lUrl);
                HttpResponse response = client.execute(request);

                String html = "";
                InputStream in = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder str = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
                in.close();
                html = str.toString();


                Log.i("TEST", "hTML : " + html);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.i("TEST", "eRREUR : "+e.toString());

            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TEST", "eRREUR : " + e.toString());

            }

            return 0;
        }
            @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            dialog.dismiss();
        }
    }
    @Override
    protected void onDestroy(){
        //mDb.close();
        super.onDestroy();
    }
}