package com.timemohk.timemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.timemohk.timemo.R;
import com.timemohk.timemo.fragment.HomeFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by walter.lam on 5/2/2015.
 */
public class HomeActivity extends FragmentActivity {
    final Context mCtx = this;

    @InjectView(R.id.frame_container)
    FrameLayout frame_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.inject(this);

        UI();
    }

    protected void UI(){
        Fragment fragment = null;
        String tag = "home";
        fragment = new HomeFragment();

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment, tag).addToBackStack(tag).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

}
