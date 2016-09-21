package com.zhi.www.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.zhi.www.DataAdapter;
import com.zhi.www.entry.DataInfo;
import com.zhi.www.info.GetHttpListener;
import com.zhi.www.utils.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements GetHttpListener , View.OnClickListener{
    ListView mLv;
    EditText mEtSend;
    Button mBtnSend;

    HttpHelper httpHelper;
    List<DataInfo> mDataInfoList = new ArrayList<DataInfo>();
    DataAdapter mAdapter;
    String[] welcomeTips;
    long currentTime_old = 0;
    long currentTime_new = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        mLv = (ListView) findViewById(R.id.lv);
        mEtSend = (EditText) findViewById(R.id.et_send);
        mBtnSend = (Button) findViewById(R.id.btn_send);

        mBtnSend.setOnClickListener(this);
        mAdapter = new DataAdapter(this, mDataInfoList);
        mLv.setAdapter(mAdapter);

        mDataInfoList.add(new DataInfo(getWelcome(), DataInfo.DATA_RECEIVED, getTime()));
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void gethttpData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String text = jsonObject.optString("text");
            mDataInfoList.add(new DataInfo(text, DataInfo.DATA_RECEIVED, getTime()));
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String  getWelcome(){
        String welcome;
        welcomeTips = this.getResources().getStringArray(R.array.welcome_tips);
        int index = (int) (Math.random()*(welcomeTips.length-1));
        welcome = welcomeTips[index];
        return welcome;
    }

    public String getTime(){
        long  currentTime_new = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        if(currentTime_new - currentTime_old > 40*1*1000){
            currentTime_old = currentTime_new;
            return str;
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        String content = mEtSend.getText().toString();
        if(null == content || (null !=content && "".equals(content.trim()))){
            Toast.makeText(this,"请至少输入一个字符", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mDataInfoList.size() > 30){
            for(int i=0;i<10;i++){
                mDataInfoList.remove(i);
            }
        }
        String drop_k = content.replace(" ", "");
        String drop_e = drop_k.replace("\n", "");
        mDataInfoList.add(new DataInfo(content, DataInfo.DATA_SEND, getTime()));
        mAdapter.notifyDataSetChanged();

        String url = "http://www.tuling123.com/openapi/api?key=c8a835e872534e6d80cc92f3e55ba91f&info="+drop_e;
        httpHelper = (HttpHelper) new HttpHelper(url, this).execute();

        mEtSend.setText("");
    }
}