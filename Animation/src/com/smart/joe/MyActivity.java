package com.smart.joe;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import android.widget.TextView;
import android.widget.Toast;
import com.smart.joe.R;

public class MyActivity extends Activity implements Animation.AnimationListener {
    private TextView animationView3;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void startAnimation(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.myanimation);
                animation.setAnimationListener(this);
                TextView textView1 = (TextView) findViewById(R.id.rotatetext);
                textView1.startAnimation(animation);
                break;
            case R.id.button2:
                Paint paint = new Paint();
                TextView animationView2 = (TextView) findViewById(R.id.scrolltext);
                float  measureTextCenter = paint.measureText(animationView2.getText().toString());
                Animation animation2 = new TranslateAnimation(0f,-measureTextCenter,0.0f,0.0f);
                animation2.setDuration(1000);
                animation2.setInterpolator(new BounceInterpolator());
                animationView2.startAnimation(animation2);
                break;
            case R.id.button3:
                animationView3 = (TextView)findViewById(R.id.fadeout);
                float from = 1.0f;
                float to = 0.0f;
                if(animationView3.getVisibility() == View.INVISIBLE){
                    from = to;
                    to = 1.0f;
                }
                Animation animation3 = new AlphaAnimation(from,to);
                animation3.setDuration(5000);
                animation3.setAnimationListener(this);
                animationView3.startAnimation(animation3);
                break;
            case R.id.button4:
                ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
                Animation animation4 = new AlphaAnimation(0.0f,1.0f);
                animation4.setDuration(5000);
                LayoutAnimationController controller = new LayoutAnimationController(animation4,0);
                layout.setLayoutAnimation(controller);

                View button = findViewById(R.id.button3);
                if(button == null){
                    //TODO this statement could make a null point exception;
                    layout.addView(button);
                }else{
                    layout.removeView(button);
                }
                break;
            default:
                break ;
        }

    }

    public void onAnimationStart(Animation animation) {
        Toast.makeText(this, "Animation started", Toast.LENGTH_SHORT);
    }

    public void onAnimationEnd(Animation animation) {
        Toast.makeText(this, "Animation started", Toast.LENGTH_SHORT);
        if(animationView3.getVisibility() == View.VISIBLE){
            animationView3.setVisibility(View.INVISIBLE);
        }else{
            animationView3.setVisibility(View.VISIBLE);
        }
    }

    public void onAnimationRepeat(Animation animation) {
        Toast.makeText(this, "Animation started", Toast.LENGTH_SHORT);
    }
}
