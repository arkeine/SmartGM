package ch.arkeine.smartgm.view.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.view.UniverseEditionActivity;

/**
 * This class is just a fun welcome screen
 */
public class WelcomeActivity extends Activity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setAnimation();
        setTransition();

        //Automatically skip the welcome screen after n seconds
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(WelcomeActivity.skip){
                    switchToNextActivity();
                }
            }
        }, Constants.WELCOME_DEFAULT_TIMEOUT);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void setTransition() {
        //Add te next view event

        layout = (RelativeLayout) findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip = true;
                switchToNextActivity();
            }
        });
    }

    private void setAnimation() {
        //Start the text animation

        txtInfo = (TextView) findViewById(R.id.info);
        animationFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in_slow);
        animationFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out_slow);
        animationFadeIn.setAnimationListener(new AnimationAdapter(){
            @Override
            public void onAnimationEnd(Animation animation) {
                txtInfo.startAnimation(animationFadeOut);
            }
        });
        animationFadeOut.setAnimationListener(new AnimationAdapter(){
            @Override
            public void onAnimationEnd(Animation animation) {
                txtInfo.startAnimation(animationFadeIn);
            }
        });
        txtInfo.startAnimation(animationFadeIn);
    }

    private void setFullScreen() {
        //Code to hide the action bar from the activity

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            ActionBar actionBar = getActionBar();
            if (actionBar != null) actionBar.hide();
        }
    }

    private void switchToNextActivity(){
        finish();
        Intent intent = new Intent(WelcomeActivity.this, HubActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out_slow);
        startActivity(intent);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private TextView txtInfo;
    private RelativeLayout layout;
    private Animation animationFadeOut;
    private Animation animationFadeIn;
    private Timer timer;

    private static boolean skip;

    /* ============================================ */
    // INTERNAL CLASS
    /* ============================================ */

    class AnimationAdapter implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {}

        @Override
        public void onAnimationEnd(Animation animation) {}

        @Override
        public void onAnimationRepeat(Animation animation) {}
    }
}
