package com.bxs.photoquiz.application.utilities;

import java.util.ArrayList;



import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bxs.photoquiz.application.models.LevelModel;

public class CommonUtilities {
	
	
	public static void playMusic(int sound, Context context)
	{
		SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

		int iTmp = sp.load(context, sound, 1); // in 2nd param u have to pass your desire ringtone

		sp.play(iTmp, 1, 1, 0, 0, 1);
		MediaPlayer mPlayer = MediaPlayer.create(context, sound); // in 2nd param u have to pass your desire ringtone
		//mPlayer.prepare();
		mPlayer.start();

	}
	
	
	public static final String filePath = "https://googledrive.com/host/0B9IinKwyaFBcQ1haZTJnbHdOd2c/photoQuiz.json";
	public static String PREFS_CONFIGURATION = "prefs_configuration";
	public static String PREFS_FILE_PATH = "prefs_file_path";
	
	public static ArrayList<LevelModel> sLevelList = new ArrayList<LevelModel>();
	
	public static String MESSAGE_NO_INTERNET_CONNECTION  = "No internet connection";
	
	public static boolean isNetworkAvailable(Context context)
	{
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        // return active network info
	    return activeNetworkInfo != null;
	}
			

}
