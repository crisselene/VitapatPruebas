package com.example.a21732599.pruebas;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView myTitle;
    TextView mySlogan;
    ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //letra titulo
        Typeface faceTitulo=Typeface.createFromAsset(getAssets(),"Lobster-Regular.ttf");
        Typeface faceFuente=Typeface.createFromAsset(getAssets(),"Rubik-Regular.ttf");

        myTitle = (TextView)findViewById(R.id.tvVitapat);
        mySlogan = (TextView)findViewById(R.id.tvSlogan);
        myTitle.setTypeface(faceTitulo);
        mySlogan.setTypeface(faceFuente);
        myImage = findViewById(R.id.logo);


        final Animation myanim = AnimationUtils.loadAnimation(this, R.anim.beat);
        final Animation myanim1 = AnimationUtils.loadAnimation(this, R.anim.fadein);
      //  myTitle.startAnimation(myanim1);
       // myImage.startAnimation(myanim1);
        mySlogan.startAnimation(myanim1);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                myImage.startAnimation(myanim);
            }
        }, 1000);

        openApp(true);




    }

    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity
                        .this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
