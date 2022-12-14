package com.example.fitbitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    TextView textView;
    SwipeListener swipeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.relative_layout);
        textView = findViewById(R.id.text_view);

        //Initialized Swipe Listener
        swipeListener = new SwipeListener(relativeLayout);
    }

    private class SwipeListener implements View.OnTouchListener {
        int taps=0;
        GestureDetector gestureDetector;
        SwipeListener(View view){
            int threshold = 100;
            int velocity_threshold = 100;
            GestureDetector.SimpleOnGestureListener listener =
            new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    textView.setText("Double Tapped");
                    return true;
                }


                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();
                    try {
                        if(Math.abs(velocityX) == 0 && Math.abs(velocityY) == 0) {
                            textView.setText("Tap 1");
                        }
                        if(Math.abs(xDiff) > Math.abs(yDiff)) {
                            if(Math.abs(xDiff)>threshold && Math.abs(velocityX) > velocity_threshold) {
                                if(xDiff > 0) {
                                    textView.setText("Swiped Right");
                                }else {
                                    textView.setText("Swiped Left");
                                }
                                return true;
                            }
                        }else {
                            if(Math.abs(yDiff)>threshold && Math.abs(velocityY) > velocity_threshold) {
                                if(yDiff > 0) {
                                    textView.setText("Swiped Down");
                                }else {
                                    textView.setText("Swiped Up");
                                }
                                return true;
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}