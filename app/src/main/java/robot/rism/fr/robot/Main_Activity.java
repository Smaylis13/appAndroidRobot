package robot.rism.fr.robot;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.LogRecord;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.net.wifi.WifiInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Main_Activity extends Activity {

    public static Socket sSocket;
    private static final int SERVERPORT = 54321;
    private static final String SERVER_IP = "192.168.43.155";
    private ImageView mIMVStart;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            mIMVStart.clearAnimation();
            mIMVStart.setImageResource(R.drawable.shutdown);

            if (msg.what == 1){
                Toast.makeText(Main_Activity.this, "Problème de connection...", Toast.LENGTH_SHORT).show();
            }else if(msg.what == 0){
                Intent lIntent = new Intent(getApplication(),Game_Activity.class);
                startActivity(lIntent);

            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        mIMVStart = (ImageView) findViewById(R.id.start);

     }


    class ClientThread extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                //Thread.sleep(3000);

                sSocket = new Socket(serverAddr, SERVERPORT);

            } catch (Exception e1) {
                e1.printStackTrace();
                mHandler.sendEmptyMessage(1);
                return null;
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (o != null)
                mHandler.sendEmptyMessage(0);// tout s'est bien passé
            super.onPostExecute(o);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void onStart(View v) {
        //try{
            //WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
            //WifiInfo wifiInfo = wifiManager.getConnectionInfo();
           // String lSSID = wifiInfo.getSSID();
           // if (lSSID.equals("CINKFIVE")) {
        mIMVStart.setImageResource(R.drawable.shutdownv);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Main_Activity.this, R.anim.start);
        mIMVStart.startAnimation(myFadeInAnimation);
        (new ClientThread()).execute();
           // }else
            //    Toast.makeText(getApplicationContext(), "Connectez-vous au serveur", Toast.LENGTH_LONG).show();

        // }catch(Exception e){
         //   Toast.makeText(getApplicationContext(), "Vérifier votre connection Wifi", Toast.LENGTH_LONG).show();
        //}
    }


}