package com.timemohk.timemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.timemohk.timemo.activity.HomeActivity;
import com.timemohk.timemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by walter.lam on 6/2/2015.
 */
public class LoginFragment extends GeneralFragment {
    @InjectView(R.id.name)
    EditText name;

    @OnClick(R.id.login) void login() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("launch",true);
        startActivity(intent);
        Log.e("Performance", "GuidingActivity Ends");
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
    }
}
