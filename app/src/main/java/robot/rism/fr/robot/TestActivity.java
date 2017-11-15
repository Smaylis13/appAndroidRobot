package robot.rism.fr.robot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lyamsi on 10/01/16.
 */
public class TestActivity extends Activity {
    private ImageView mAvancer;
    private static boolean boolUP = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mAvancer = (ImageView) findViewById(R.id.imageView12);

        mAvancer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        boolUP = true;
                        mAvancer.setImageResource(R.drawable.car2);
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while(boolUP){
                                    Log.i("TEST", "AVANT " );
                                }
                            }
                        });
                        t.start();

                        break;
                    case MotionEvent.ACTION_DOWN:
                        boolUP = false;
                        mAvancer.setImageResource(R.drawable.car1);


                        break;
                }
                return true;
            }
        });

    }
}
