package com.timemohk.timemo.fragment;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.timemohk.timemo.R;
import com.timemohk.timemo.model.Memo;
import com.timemohk.timemo.plugin.HttpClientHelper;
import com.timemohk.timemo.string.MyString;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by walter.lam on 6/2/2015.
 */
public class TimemoListFragment extends GeneralFragment {
    private static final int GET_MEMO = 1;
    ProgressDialog mydialog = null;
    private ArrayList<Memo> memos;
    //private FindCarsListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, rootView);

        ListView list = (ListView) rootView.findViewById(R.id.list);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("keyword", "timemo"));

//        SharedPreferences settings = MyApplication.getAppContext().getSharedPreferences (MyString.sharedpreferenceName, 0);
//        String token = settings.getString("login_id","");

        String method = MyString.get_memo;
        new MyAsyncTask(method, nameValuePairs, GET_MEMO).execute();
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

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        String method;
        List<NameValuePair> nameValuePairs;
        int type;

        public MyAsyncTask(String method, List<NameValuePair> nameValuePairs, int type) {
            mydialog = ProgressDialog.show(getActivity(), getString(R.string.loading), getString(R.string.please_wait), true);
            mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mydialog.setCancelable(true);

            this.method = method;
            this.nameValuePairs = nameValuePairs;
            this.type = type;
        }

        protected String doInBackground(String... params) {
            //String response = Connection.Get(method, nameValuePairs);
            //Log.i("AUTO","Response: " + response);
            String result = "";

            StringBuilder buf = null;
            buf = new StringBuilder(MyString.api);
            buf.append("?mod=");
            buf.append(method);

            String getURL = buf.toString();

            try {
                HttpClientHelper httphelper = new HttpClientHelper(); HttpClient httpclient = httphelper.getHttpClient();
                HttpPost httppost = new HttpPost(getURL);
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
                HttpResponse response = httpclient.execute(httppost);
                result = EntityUtils.toString(response.getEntity());
            }catch(Exception e){
                e.printStackTrace();
                Log.e("Upload Error", e.toString());
            }

            result = "";    //prototype
            return result;
        }

        protected void onPostExecute(String response) {
            if(!response.equals("") && response != null){
                JSONObject jsonData;
                JSONArray jsonArray;
                Log.e("AA","type:"+type);
                try{
                    jsonData = new JSONObject(response);
                    jsonData = new JSONObject(jsonData.getString("return_data"));
                    String num_of_records = jsonData.getString("num_of_record");
                }catch (Exception e){e.printStackTrace();}
                switch(type) {
                    case GET_MEMO:
                        try {
                            for (int i = 0; i < nameValuePairs.size(); i++) {
                                Log.e("AA", nameValuePairs.get(i).getName() + " " + nameValuePairs.get(i).getValue());
                            }

                            jsonData = new JSONObject(response);
                            if (jsonData.getString("error").equals("false")) {
                                jsonData = new JSONObject(jsonData.getString("return_data"));
                                jsonArray = new JSONArray(jsonData.getString("data"));

                                memos = new ArrayList<Memo>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonData = jsonArray.getJSONObject(i);

                                    String id = jsonData.getString("id");
                                    Log.e("AA","id:"+id);
                                    String padded_id = jsonData.getString("padded_id");
                                    String object_id = jsonData.getString("object_id");
                                    String user_id = jsonData.getString("user_id");
                                    String manufacturer_id = jsonData.getString("manufacturer_id");
                                    String manufacturer_name = jsonData.getString("manufacturer_name");
                                    String model_id = jsonData.getString("model_id");
                                    String model_name = jsonData.getString("model_name");
                                    String seat = jsonData.getString("seat");
                                    String capacity = jsonData.getString("capacity");
                                    String transmission_id = jsonData.getString("transmission_id");
                                    String year = jsonData.getString("year");
                                    String colour_id = jsonData.getString("colour_id");
                                    String description = jsonData.getString("description");
                                    String offer = jsonData.getString("offer");
                                    String show_tel = jsonData.getString("show_tel");
                                    String pic_main = jsonData.getString("pic_main");
                                    String pic_side = jsonData.getString("pic_side");
                                    String pic_rear = jsonData.getString("pic_rear");
                                    String pic_seat_front = jsonData.getString("pic_seat_front");
                                    String pic_seat_rear = jsonData.getString("pic_seat_rear");
                                    String pic_panel = jsonData.getString("pic_panel");
                                    String is_verified = jsonData.getString("is_verified");
                                    String is_featured = jsonData.getString("is_featured");
                                    String status = jsonData.getString("status");
                                    String create_timestamp = jsonData.getString("create_timestamp");
                                    String update_timestamp = jsonData.getString("update_timestamp");
                                    String view = jsonData.getString("view");
                                    String manufacturer = jsonData.getString("manufacturer");
                                    String transmission = jsonData.getString("transmission");
                                    String colour = jsonData.getString("colour");
                                    String nickname = jsonData.getString("nickname");
                                    String tel = jsonData.getString("tel");
                                    String favourited = "0";
                                    if(jsonData.has("favourited"))
                                        favourited = jsonData.getString("favourited");

                                    Memo memo = new Memo("name");
                                    memos.add(memo);
                                }

                                if(jsonArray.length()==0){
                                    /*noResult.setVisibility(View.VISIBLE);
                                    list.setVisibility(View.GONE);
                                    list.setOnScrollListener(null);
                                    list.setAdapter(null);*/
                                }else{
                                    /*noResult.setVisibility(View.GONE);
                                    list.setVisibility(View.VISIBLE);
                                    list.setOnItemClickListener(new CarClickListener());
                                    adapter = new FindCarsListAdapter(getActivity(), cars, list);
                                    list.setAdapter(adapter);
                                    Log.e("AA", "get child1:" + list.getCount());
                                    list.setOnScrollListener(new EndlessScrollListener(list.getCount(), TYPE_GET_CAR));*/
                                }

                            } else {
                                Log.e("Timemo", jsonData.getString("error_msg"));
                                Toast.makeText(getActivity(), jsonData.getString("error_msg"), Toast.LENGTH_LONG).show();
                            }
                            if (mydialog != null)
                                if (mydialog.isShowing())
                                    mydialog.dismiss();
                        }catch(JSONException e){
                            e.printStackTrace();
                            Log.e("AA",e.toString());
                            if (mydialog != null)
                                if (mydialog.isShowing())
                                    mydialog.dismiss();
                        }
                        break;
                }
            }else{
                memos = new ArrayList<Memo>();

                /*adapter = new FindCarsListAdapter(getActivity(), cars, list);
                list.setAdapter(adapter);*/

                if(mydialog!=null)
                    if(mydialog.isShowing())
                        mydialog.dismiss();
            }

        }
    }
}
