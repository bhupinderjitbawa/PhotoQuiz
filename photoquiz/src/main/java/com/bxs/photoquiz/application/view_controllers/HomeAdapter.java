package com.bxs.photoquiz.application.view_controllers;

import java.util.ArrayList;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bxs.photoquiz.R;

public class HomeAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<String> levelList;
	
	public HomeAdapter(Context context, ArrayList<String> levelList)
	{
		mContext = context;
		this.levelList = levelList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return levelList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return levelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.home_adapter, parent, false);
		
		TextView tv = (TextView) convertView.findViewById(R.id.home_adapter_tv);
		tv.setText(levelList.get(position));
		return convertView;
	}
	
	

}
