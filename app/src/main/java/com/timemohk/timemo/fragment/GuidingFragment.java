package com.timemohk.timemo.fragment;

/**
 * Created by walter.lam on 4/2/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timemohk.timemo.activity.LoginActivity;
import com.timemohk.timemo.R;

public final class GuidingFragment extends Fragment {
    private Context mCtx = getActivity();

    private static final int SPLASH = 1;
    private static final int INTRODUCTION = 2;
    private static final int GET_STARTED = 3;

    private static final String KEY_CONTENT = "GuidingFragment:Page";

    public static GuidingFragment newInstance(int page) {
        GuidingFragment fragment = new GuidingFragment();
        fragment.mPage = page;
        return fragment;
    }

    private int mPage = SPLASH;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mPage = savedInstanceState.getInt(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        switch(mPage){
            case SPLASH:
                rootView = inflater.inflate(R.layout.fragment_guiding_splash, container, false);
                break;
            case INTRODUCTION:
                rootView = inflater.inflate(R.layout.fragment_guiding_introduction, container, false);
                break;
            case GET_STARTED:
                rootView = inflater.inflate(R.layout.fragment_guiding_get_started, container, false);
                TextView next = (TextView) rootView.findViewById(R.id.next);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("launch",true);
                        startActivity(intent);
                        Log.e("Performance", "GuidingActivity Ends");
                        getActivity().finish();
                        getActivity().overridePendingTransition(0, 0);
                    }
                });
                break;
            default:
                rootView = inflater.inflate(R.layout.fragment_guiding_splash, container, false);
        }


        return rootView;
        /*TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mContent);
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);

        return layout;*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTENT, mPage);
    }
}