package com.bxs.photoquiz.application.view_controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.bxs.photoquiz.R;
import com.bxs.photoquiz.application.utilities.CommonUtilities;

public class Splash extends Activity {

	private String filePath = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		
		
		SharedPreferences prefs = getSharedPreferences(CommonUtilities.PREFS_CONFIGURATION, MODE_PRIVATE);
		filePath = prefs.getString(CommonUtilities.PREFS_FILE_PATH, ""); 
		if(filePath.equals(""))
		{
			if(CommonUtilities.isNetworkAvailable(getApplicationContext()))
				new DownloadFileTask().execute();
			else
				Toast.makeText(getApplicationContext(), CommonUtilities.MESSAGE_NO_INTERNET_CONNECTION, Toast.LENGTH_LONG).show();
		}
		else
		{
			new ParseDataTask().execute();
		}
	}

	class DownloadFileTask extends AsyncTask<Void, Void, Void>
	{
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			// check and download file
			filePath = Download(CommonUtilities.filePath, "photoQuiz");
			
			if(!filePath.equals(""))
			{
				SharedPreferences prefs = getSharedPreferences(CommonUtilities.PREFS_CONFIGURATION, MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putString(CommonUtilities.PREFS_FILE_PATH, filePath);
				editor.commit();
			}
			return null;
		}
		
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			new ParseDataTask().execute();
		}
		
		public String Download(String Url,String saveFileName)
	    {
	     String filepath=null;
	     
	     try 
	     {
	         //set the download URL, a url that points to a file on the internet
	         //this is the file to be downloaded
	        
	         URL url = new URL(Url);
	         //create the new connection
	         HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	         
	         //set up some things on the connection
	         urlConnection.setRequestMethod("GET");
	         //urlConnection.setDoOutput(true); 
	         //and connect!
	         urlConnection.connect();
	         //set the path where we want to save the file
	         //in this case, going to save it on the root directory of the
	         //sd card.
	         File SDCardRoot = Environment.getExternalStorageDirectory();
	         //create a new file, specifying the path, and the filename
	         //which we want to save the file as.
	      
	         String filename= saveFileName;   // you can download to any type of file ex:.jpeg (image) ,.txt(text file),.mp3 (audio file)
	         Log.i("Local filename:",""+filename);
	         File file = new File(SDCardRoot,filename);
	         
	         if(file.createNewFile())
	         {
	             file.createNewFile();
	         }
	      
	         //this will be used to write the downloaded data into the file we created
	         FileOutputStream fileOutput = new FileOutputStream(file);

	         //this will be used in reading the data from the internet
	         InputStream inputStream = urlConnection.getInputStream();

	         //this is the total size of the file
	         int totalSize = urlConnection.getContentLength();
	         Log.i("Getlength", totalSize+"");
	         //variable to store total downloaded bytes
	         int downloadedSize = 0;

	         //create a buffer...
	         byte[] buffer = new byte[1024];
	         int bufferLength = 0; //used to store a temporary size of the buffer

	         //now, read through the input buffer and write the contents to the file
	         while ( (bufferLength = inputStream.read(buffer)) > 0 )
	         {
	             //add the data in the buffer to the file in the file output stream (the file on the sd card
	             fileOutput.write(buffer, 0, bufferLength);
	             //add up the size so we know how much is downloaded
	             downloadedSize += bufferLength;
	             //this is where you would do something to report the prgress, like this maybe
	             Log.i("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;

	         }
	         //close the output stream when done
	         fileOutput.close();
	         if(downloadedSize==totalSize)   filepath=file.getPath();
	      
	         //catch some possible errors...
	     } catch (MalformedURLException e) {
	      e.printStackTrace();
	     } catch (IOException e) {
	      filepath=null;
	      e.printStackTrace();
	     }
	     Log.i("filepath:"," "+filepath) ;

	     return filepath;

	    }
		
		
	}
	
	class ParseDataTask extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			ParseJSON obj = new ParseJSON();
			CommonUtilities.sLevelList = obj.getLevelList(readFromFile(filePath)); 
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Intent i=new Intent(Splash.this, Home.class);
             startActivity(i);
             finish();
		}
		
		
		 private String readFromFile(String filename) {

		        String ret = "";

		        try {
		            InputStream inputStream = new FileInputStream (new File(filename));

		            if ( inputStream != null ) {
		                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		                String receiveString = "";
		                StringBuilder stringBuilder = new StringBuilder();

		                while ( (receiveString = bufferedReader.readLine()) != null ) {
		                    stringBuilder.append(receiveString);
		                }

		                inputStream.close();
		                ret = stringBuilder.toString();
		            }
		        }
		        catch (FileNotFoundException e) {
		            Log.e("login activity", "File not found: " + e.toString());
		        } catch (IOException e) {
		            Log.e("login activity", "Can not read file: " + e.toString());
		        }

		        return ret;
		    }
		
	}
	

}
