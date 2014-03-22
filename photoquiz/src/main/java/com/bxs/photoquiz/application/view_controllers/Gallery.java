package com.bxs.photoquiz.application.view_controllers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.bxs.photoQuiz.models.LevelModel;
import com.bxs.photoquiz.R;
import com.bxs.photoquiz.application.utilities.CommonUtilities;

public class Gallery extends Activity {
	
	private String levelId = "";
	private GridView gvGallery;
	private GalleryAdapter adapter;
	private LevelModel level;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		
		init();
		if(!levelId.equals(""))
		{
			Log.i("levelId", levelId +"");
			level = CommonUtilities.sLevelList.get(Integer.parseInt(levelId)-1);
			if(level.questionList.size()>0)
			{
				adapter = new GalleryAdapter(getApplicationContext(), level);
				gvGallery.setAdapter(adapter);
				
			}
		}
		
		
		
	}
	
	public void init()
	{
		try {
			levelId = getIntent().getStringExtra("levelId");
		} catch (Exception e) {
			// TODO: handle exception
			levelId = "";
		}
		
		gvGallery = (GridView) findViewById(R.id.gridView1);
		
		
	}

	

}
