package robot.rism.fr.robot;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.capricorn.RayMenu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lyamsi on 28/12/15.
 */
public class Game_Activity extends Activity {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ImageView mAvancer;
    private ImageView mStop;
    private ImageView mBoiteV;
    private static boolean mBackOrForward = true;
    private static Socket mSocket;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);

        RayMenu rayMenu = (RayMenu) findViewById(R.id.ray_menu);

        /*--------------------------------Left cam----------------------*/
        ImageView lItemL = new ImageView(this);
        lItemL.setImageResource(R.drawable.left);

        rayMenu.addItem(lItemL, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Gauche....",Toast.LENGTH_SHORT).show();
                sendToserver("MvGauche");
            }
        });

        /*--------------------------------Right cam----------------------*/
        ImageView lItemR = new ImageView(this);
        lItemR.setImageResource(R.drawable.right);
        rayMenu.addItem(lItemR, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Droite....",Toast.LENGTH_SHORT).show();

                sendToserver("MvDroite");

            }
        });




        /*-------------------Accelerometre------------------------*/
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        mSocket = Main_Activity.sSocket;
        mAvancer = (ImageView) findViewById(R.id.car);
        mStop = (ImageView) findViewById(R.id.stop);
        mBoiteV = (ImageView) findViewById(R.id.boite);
        mAvancer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        mAvancer.setImageResource(R.drawable.car2);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mAvancer.setImageResource(R.drawable.car1);

                        if(mBackOrForward) {
                            Log.i("TEST", "AVANT");
                            sendToserver("AvancerToutDroit");
                        }else{
                            sendToserver("AvancerMarcheArriere");
                        }
                            break;
                }
                return true;
            }
        });
        mStop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        mStop.setImageResource(R.drawable.breaks);
                        sendToserver("Arreter");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mStop.setImageResource(R.drawable.breaks3);
                        break;
                }
                return true;
            }
        });
        mBoiteV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBoiteV.setImageResource(!mBackOrForward ? R.drawable.toggle1 : R.drawable.toggle2);
                mBackOrForward = !mBackOrForward;
            }
        });
    }



    @Override
    protected void onDestroy() {
        sendToserver("CloseConnection");
        super.onDestroy();
    }
    /*--------------------------------------ACCEL-------------------------------------*/
    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(gyroListener, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(gyroListener,mSensor);
        super.onPause();
    }

    private SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float lY = event.values[1];
            if (lY > 4){
                if(mBackOrForward) {
                    Log.i("TEST", "AVANT Droite");
                    sendToserver("AvancerDroit");
                }else{
                    sendToserver("ReculerDroit");
                    Log.i("TEST", "Arrière Droite");

                }

            }else if (lY < -4){
                if(mBackOrForward) {
                    sendToserver("AvancerGauche");
                    Log.i("TEST", "AvancerGauche");
                }else{
                    sendToserver("ReculerGauche");
                }
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };



    /*--------------------------------------------------SERVER -----------------------------*/
    private void sendToserver(String str){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream())), true);
            out.println(str);
        } catch (IOException |NullPointerException e) {
            e.printStackTrace();
        }
    }

}
