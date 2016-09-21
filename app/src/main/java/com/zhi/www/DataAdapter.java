package com.zhi.www;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhi.www.entry.DataInfo;
import com.zhi.www.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class DataAdapter extends BaseAdapter{

    private Context context;
    private List<DataInfo> dataInfoList;
    private RelativeLayout layout;

    public DataAdapter(Context context, List<DataInfo> dataInfoList){
        this.context = context;
        this.dataInfoList = dataInfoList;
    }


    @Override
    public int getCount() {
        return dataInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            int type = dataInfoList.get(position).getType();
            if(DataInfo.DATA_RECEIVED == type){
                layout = (RelativeLayout) inflater.inflate(R.layout.item_data_left, null);
            }else if(DataInfo.DATA_SEND == type){
                layout = (RelativeLayout) inflater.inflate(R.layout.item_data_right, null);
            }
        TextView text = (TextView) layout.findViewById(R.id.tv);
        TextView time = (TextView) layout.findViewById(R.id.tv_time);
        text.setText(dataInfoList.get(position).getText());
        time.setText(dataInfoList.get(position).getDate());
        return layout;
    }
}
