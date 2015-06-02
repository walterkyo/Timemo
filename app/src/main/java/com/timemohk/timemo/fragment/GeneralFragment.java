package com.timemohk.timemo.fragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;

import com.timemohk.timemo.MyApplication;

import java.util.Locale;

/**
 * Created by walter.lam on 6/2/2015.
 */
public class GeneralFragment extends Fragment {

    public void MyLocale() {
        Locale locale = new Locale("zh");
        Locale.setDefault(locale);
        Configuration appConfig = new Configuration();
        appConfig.locale = locale;
        MyApplication.getAppContext().getResources().updateConfiguration(appConfig, null);
    }
}
