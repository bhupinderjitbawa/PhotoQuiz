package com.bxs.photoquiz.application.view_controllers;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.bxs.photoquiz.R;
import com.bxs.photoquiz.application.models.LevelModel;

public class GalleryAdapter extends BaseAdapter{

	private Context mContext;
	private LevelModel level;
	private ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
	
	public GalleryAdapter(Context context, LevelModel level) {
		// TODO Auto-generated constructor stub
		mContext = context;
		this.level = level;
		new DownloadImageTask().execute();
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return level.questionList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return level.questionList.get(position);
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
		convertView = inflater.inflate(R.layout.gallery_adapter, parent,false);
		
		ImageView iv = (ImageView) convertView.findViewById(R.id.gallery_adapter_iv);
		if(bitmapList.size()>position)
		{
			if(bitmapList.get(position)!=null)
				iv.setImageBitmap(bitmapList.get(position));
		}
		
		convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent displayQuestion = new Intent(mContext, DisplayQuestion.class);
				displayQuestion.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(displayQuestion);
			}
		});
		
		return convertView;
	}
	
	class DownloadImageTask extends AsyncTask<Void, Void, Void>
	{

		private ImageDownloader downloader  = new ImageDownloader();
		
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for(int i=0;i<level.questionList.size();i++)
				{
					bitmapList.add(downloader.download(level.folderPath+level.questionList.get(i).imageUrl, mContext));
					Log.i("image", i+"downloaded");
				}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(bitmapList.size()>0)
				notifyDataSetChanged();
		}
		
	}
	

}
