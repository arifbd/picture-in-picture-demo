package xyz.arifz.pip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class PIPActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    MaterialButton btnPip;
    MaterialButton btnBack;
    ConstraintLayout clRoot;
    private PictureInPictureParams.Builder pipParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);
        btnPip = findViewById(R.id.mb_pip);
        btnBack = findViewById(R.id.mb_back);
        clRoot = findViewById(R.id.cl_root);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pipParams = new PictureInPictureParams.Builder();
        }

        btnPip.setOnClickListener(v -> pipMode());
        btnBack.setOnClickListener(v -> finish());
    }

    private void pipMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "pipMode: supported");

            // setup height and width of PIP window
            Rational aspectRation = new Rational(clRoot.getWidth(), clRoot.getHeight());
            pipParams.setAspectRatio(aspectRation).build();
            enterPictureInPictureMode(pipParams.build());
        } else {
            Log.d(TAG, "pipMode: not supported");
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        // called when user touch on home button
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "onUserLeaveHint: was not in pip");
            pipMode();
        } else {
            Log.d(TAG, "onUserLeaveHint: already in pip");
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode) {
            Log.d(TAG, "onPictureInPictureModeChanged: entered pip");
            btnPip.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
        } else {
            btnPip.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }
}