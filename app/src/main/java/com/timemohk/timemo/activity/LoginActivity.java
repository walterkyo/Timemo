package com.timemohk.timemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.timemohk.timemo.R;
import com.timemohk.timemo.fragment.LoginFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by walter.lam on 5/2/2015.
 */
public class LoginActivity extends FragmentActivity {
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
        String tag = "login";
        fragment = new LoginFragment();

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment, tag).addToBackStack(tag).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(mCtx, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
