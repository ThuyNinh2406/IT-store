package com.example.it_store;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class IntroSlider extends AppCompatActivity {


    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    public static int[] layouts;
    private Button btnSkip, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_intro_slider);


        if(Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        }

        chnageBarColour();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int current=viewPager.getCurrentItem()+1;
                if(current<layouts.length){
                    viewPager.setCurrentItem(current);
                }
                else {

                    Intent intent=new Intent(IntroSlider.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroSlider.this,MainActivity.class);
                startActivity(intent);
            }
        });
        layouts=new int[]{
                R.layout.screen1,R.layout.screen2,R.layout.screen3,R.layout.screen4
        };


        addbtn(0);
        myViewPagerAdapter=new MyViewPagerAdapter(IntroSlider.this);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                addbtn(position);
                if(position==layouts.length-1){
                    btnNext.setText("Start");
                    btnSkip.setVisibility(View.GONE);

                }else {

                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void chnageBarColour() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void addbtn(int currentPage){
        dots= new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[currentPage].setTextColor(colorsActive[currentPage]);
        }

    }
}
