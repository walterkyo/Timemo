package com.timemohk.timemo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.timemohk.timemo.R;
import com.timemohk.timemo.adapter.GuidingFragmentAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends FragmentActivity {

    /*private static final ButterKnife.Action<View> ALPHA_FADE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setStartOffset(index * 100);
            view.startAnimation(alphaAnimation);
        }
    };*/

    final Context mCtx = this;

    @InjectView(R.id.indicator) CirclePageIndicator mIndicator;
    @InjectView(R.id.pager) ViewPager mPager;

    @OnClick(R.id.enter) void signup() {
        Intent intent = new Intent(mCtx, TimemoListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("launch",true);
        startActivity(intent);
        Log.e("Performance", "GuidingActivity Ends");
        finish();
        overridePendingTransition(0, 0);
    }
    /*
    @OnClick(R.id.hello) void sayHello() {
    Toast.makeText(this, "Hello, views!", LENGTH_SHORT).show();
    ButterKnife.apply(headerViews, ALPHA_FADE);
  }

  @OnLongClick(R.id.hello) boolean sayGetOffMe() {
    Toast.makeText(this, "Let go of me!", LENGTH_SHORT).show();
    return true;
  }

  @OnItemClick(R.id.list_of_things) void onItemClick(int position) {
    Toast.makeText(this, "You clicked: " + adapter.getItem(position), LENGTH_SHORT).show();
  }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        GuidingFragmentAdapter mAdapter = new GuidingFragmentAdapter(getSupportFragmentManager());

        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
        mIndicator.setFillColor(Color.parseColor("#FFFFFF"));
        mIndicator.setStrokeColor(Color.parseColor("#FFFFFF"));

        signup();   //Skip the homepage
    }

}
