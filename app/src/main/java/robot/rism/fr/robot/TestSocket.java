package robot.rism.fr.robot;

/**
 * Created by lyamsi on 15/12/15.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Map;

public class TestSocket extends Activity {//implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {




















 /* final static String RTSP_URL = "http://192.168.43.155:8081";

  private MediaPlayer _mediaPlayer;
  private SurfaceHolder _surfaceHolder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Set up a full-screen black window.
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    Window window = getWindow();
    window.setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    window.setBackgroundDrawableResource(android.R.color.black);

    setContentView(R.layout.videoview);

    // Configure the view that renders live video.
    SurfaceView surfaceView = (SurfaceView) findViewById(R.id.VideoView);
    _surfaceHolder = surfaceView.getHolder();
    _surfaceHolder.addCallback(this);
    _surfaceHolder.setFixedSize(320, 240);
  }


@Override
public void surfaceChanged(SurfaceHolder sh, int f, int w, int h) {}

@Override
public void surfaceCreated(SurfaceHolder sh) {
  _mediaPlayer = new MediaPlayer();
  _mediaPlayer.setDisplay(_surfaceHolder);

  Context context = getApplicationContext();
  Uri source = Uri.parse(RTSP_URL);

  try {
    // Specify the IP camera's URL and auth headers.
    _mediaPlayer.setDataSource(context, source);

    // Begin the process of setting up a video stream.
    _mediaPlayer.setOnPreparedListener(this);
    _mediaPlayer.prepareAsync();
  }
  catch (Exception e) {}
}

@Override
public void surfaceDestroyed(SurfaceHolder sh) {
  _mediaPlayer.release();
}

@Override
public void onPrepared(MediaPlayer mp) {
  _mediaPlayer.start();
}*/






    /*private String VideoURL = "http://192.168.43.155:8081";


    private WebView mWebView;

    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        final Activity lActivity = this;
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView pView, int pErrorCode, String pDescription, String pFailingUrl) {
                Toast.makeText(lActivity, pDescription, Toast.LENGTH_SHORT).show();
            }
        });
        mWebView.loadUrl(VideoURL);
        setContentView(mWebView);
    }*/
}














