package com.example.it_store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import static com.example.it_store.IntroSlider.layouts;

public class MyViewPagerAdapter extends PagerAdapter {
    Context cx;
    LayoutInflater layoutInflater;
    @Override
    public int getCount() {
        return layouts.length;
    }

    public MyViewPagerAdapter(Context context){
        cx=context;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)cx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(layouts[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view=(View) object;
        container.removeView(view);
    }
}
