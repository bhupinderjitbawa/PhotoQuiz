package com.bxs.photoquiz.application.view_controllers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.bxs.photoquiz.R;
import com.bxs.photoquiz.application.utilities.CommonUtilities;

public class Home extends Activity{
	
	private ListView lvLevelList;
	private Button btnSound;
	private Button btnBuyCoins;
	private Button btnShare;
	private Button btnRate;
	
	
	private ArrayList<String> alLevelList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		
		lvLevelList = (ListView) findViewById(R.id.listView1);
		
		btnSound = (Button) findViewById(R.id.button1);
		btnBuyCoins = (Button) findViewById(R.id.Button01);
		btnShare = (Button) findViewById(R.id.Button05);
		btnRate = (Button) findViewById(R.id.Button07);
	
		alLevelList = new ArrayList<String>();
		for(int i=0;i< CommonUtilities.sLevelList.size();i++)
			{
				alLevelList.add("Level"+CommonUtilities.sLevelList.get(i).levelId);
			}
		
		lvLevelList.setAdapter(new HomeAdapter(getApplicationContext(),alLevelList));
		
		try {
			CommonUtilities.playMusic(R.raw.game_start, getApplicationContext());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		lvLevelList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent openQuizGallery = new Intent(Home.this, Gallery.class);
				openQuizGallery.putExtra("levelId", CommonUtilities.sLevelList.get(arg2).levelId + "");
				startActivity(openQuizGallery);
				
			}
		});
		
		
		
	}
	

}
