package com.timemohk.timemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.timemohk.timemo.model.Memo;
import com.timemohk.timemo.plugin.HttpClientHelper;
import com.timemohk.timemo.plugin.ScaleImageView;
import com.timemohk.timemo.string.MyString;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by walter.lam on 28/4/2015.
 */
public class MemoAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Memo> memos;
    private ListView list;
    private static LayoutInflater inflater=null;
    private HashMap<String, String> main_deal_map;
    static public ArrayList<HashMap<String, String>> eList;
    static public int myPos;
    static public String thread_img;

    private static final int DELETE_FAVOURITE = 19;
    private static final int ADD_FAVOURITE = 18;

    private static final int FAV_ACTION = 222;
    private static final int FAV_ID = 223;

    private Handler mThreadHandler;
    private HandlerThread mThread;
    ImageLoader imageLoader = ImageLoader.getInstance();
    int car_counter = 0;

    public MemoAdapter(Activity a, ArrayList<Memo> memos, ListView list) {
        activity = a;
        this.memos=memos;
        this.list = list;
        imageLoader = ImageLoader.getInstance();
        if(activity!=null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        //car_counter = cars.size();

        /*ListView setting*/
        boolean pauseOnScroll = false; // or true
        boolean pauseOnFling = true; // or false
        PauseOnScrollListener listener = new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling);
        list.setOnScrollListener(listener);
    }

    public int getCount() {
        return memos.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public LinearLayout info_layout;
        public int position;
        public TextView title_tv;
        public TextView transmission_tv;
        public TextView price_tv;
        public TextView description_tv;
        public TextView sold_out;
        public TextView status_tv;
        public ScaleImageView imageView;
        public ProgressBar imgProgress;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
		/*ViewHolder holder = null;
		if (convertView == null){
	        convertView = inflater.inflate(R.layout.list_row_car, null);
	        holder = new ViewHolder();
	        holder.info_layout = (LinearLayout) convertView.findViewById(R.id.info_layout);
	        holder.title_tv = (TextView) convertView.findViewById(R.id.title);
            holder.transmission_tv = (TextView) convertView.findViewById(R.id.transmission);
	        holder.description_tv = (TextView) convertView.findViewById(R.id.description);
	        holder.status_tv = (TextView) convertView.findViewById(R.id.status);
	        holder.price_tv = (TextView) convertView.findViewById(R.id.price);
	        holder.sold_out = (TextView) convertView.findViewById(R.id.sold_out);
	        holder.imageView = (ScaleImageView) convertView.findViewById(R.id.main_deal_img);
	        holder.imgProgress = (ProgressBar) convertView.findViewById(R.id.imgProgress);
	        holder.position = position;
	        convertView.setTag(holder);
		}else{
	        holder = (ViewHolder) convertView.getTag();
	    }*/

        View vi=convertView;
        //vi = inflater.inflate(R.layout.list_row_car_in_ratio, null);

        Memo memo = memos.get(position);
        String name = memo.getName();

        /*try{
            String manufacturer = memo.manufacturer;
            JSONObject jsonData = new JSONObject(manufacturer);
            String name_eng = jsonData.getString("name_eng");
            String name_cht = jsonData.getString("name_cht");
            manufacturer_name = name_eng + " " + name_cht;
        }catch(JSONException e){
            e.printStackTrace();
        }*/

        //imageLoader.displayImage(image, imageView, Utils.setMyImageLoadingListener(imgProgress));

        /*holder.price_tv.setText(formatter.format(price));
        imageLoader.displayImage(image, holder.imageView, Utils.setMyImageLoadingListener(holder.imgProgress));*/

        TranslateAnimation animation = null;
        if (position > list.getLastVisiblePosition()) {
            animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.1f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            animation.setDuration(500);
            //convertView.startAnimation(animation);
            vi.startAnimation(animation);
            //mLastPosition = position;
        }else if(position < list.getFirstVisiblePosition()){
            animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -0.1f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            animation.setDuration(500);
            //convertView.startAnimation(animation);
            vi.startAnimation(animation);
        }

        return vi;
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        String method;
        List<NameValuePair> nameValuePairs;
        int type;

        public MyAsyncTask(String method, List<NameValuePair> nameValuePairs, int type) {
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
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                result = EntityUtils.toString(response.getEntity());
            }catch(Exception e){
                e.printStackTrace();
                Log.e("Loading Error", e.toString());
            }

            Log.e("AA","result"+result);
            return result;
        }

        protected void onPostExecute(String response) {
            if(!response.equals("") || response != null){
                JSONObject jsonData;
                JSONArray jsonArray;
                switch(type) {
                    case ADD_FAVOURITE:
                        try {
                            jsonData = new JSONObject(response);
                            if (jsonData.getString("error").equals("false")) {
                                Log.e("AA","Added favourite");
                            }
                        }catch(Exception e){

                        }
                        break;
                    case DELETE_FAVOURITE:
                        try {
                            jsonData = new JSONObject(response);
                            if (jsonData.getString("error").equals("false")) {
                                Log.e("AA","Deleted favourite");
                            }
                        }catch(Exception e){

                        }
                        break;
                }
            }

        }
    }
}
