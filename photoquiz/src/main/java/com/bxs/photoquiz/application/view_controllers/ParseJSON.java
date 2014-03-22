package com.bxs.photoquiz.application.view_controllers;

import java.util.ArrayList;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import android.util.Log;

import com.bxs.photoQuiz.models.LevelModel;
import com.bxs.photoQuiz.models.OptionModel;
import com.bxs.photoQuiz.models.QuestionModel;

public class ParseJSON {

	 public ArrayList<LevelModel> getLevelList(String jsonData)
	    {

	        
	        ArrayList<LevelModel> levelList = new ArrayList<LevelModel>();
	        jsonData = jsonData.trim();
	        //parse data from JSON
	        
	        try {
				
			
            JsonFactory jfactory = new JsonFactory();
            JsonParser jParser = jfactory.createJsonParser(jsonData);
            
            while (jParser.nextToken() != JsonToken.END_OBJECT)
            {
                
                if("photoQuiz".equals(jParser.getText()))
                { 
                      jParser.nextToken();
                      while (jParser.nextToken() != JsonToken.END_ARRAY)
                      {
                          LevelModel levelObj = new LevelModel();
                          
                          while(jParser.nextToken() != JsonToken.END_OBJECT)
                          {
                              
	                                  Log.i("textentered", jParser.getText()+"");
	                                  if("levelId".equals(jParser.getText()))
	                                  { 
	                                      jParser.nextToken();
	                                      levelObj.levelId = jParser.getText();
	                                  }
	                                  else if("folderPath".equals(jParser.getText()))
	                                  { 
	                                          jParser.nextToken();
	                                          levelObj.folderPath = jParser.getText();
	                                  }
	                                  else if("questions".equals(jParser.getText()))
	                                  { 
	                                   
	                                	  jParser.nextToken();
	                                      while (jParser.nextToken() != JsonToken.END_ARRAY)
	                                      {
	                                          QuestionModel quesObj = new QuestionModel();
	                                          
	                                          while(jParser.nextToken() != JsonToken.END_OBJECT)
	                                          {
	                                        	 
	                                        	  if("quizId".equals(jParser.getText()))
	        	                                  { 
	        	                                      jParser.nextToken();
	        	                                      quesObj.questionId = jParser.getText();
	        	                                  }
	                                        	  else if("imageUrl".equals(jParser.getText()))
	        	                                  { 
	        	                                      jParser.nextToken();
	        	                                      quesObj.imageUrl = jParser.getText();
	        	                                  }
	                                        	  else if("quizText".equals(jParser.getText()))
	        	                                  { 
	        	                                      jParser.nextToken();
	        	                                      quesObj.questionText = jParser.getText();
	        	                                  }
	                                        	  else if("options".equals(jParser.getText()))
	        	                                  { 
	                                        		  jParser.nextToken();
	        	                                      while (jParser.nextToken() != JsonToken.END_ARRAY)
	        	                                      {
	        	                                          OptionModel optionObj = new OptionModel();
	        	                                          
	        	                                          while(jParser.nextToken() != JsonToken.END_OBJECT)
	        	                                          {
	        	                                        	  
	        	                                        	  if("optionId".equals(jParser.getText()))
	        	        	                                  { 
	        	        	                                      jParser.nextToken();
	        	        	                                      optionObj.optionId = jParser.getText();
	        	        	                                  }
	        	                                        	  else if("optionText".equals(jParser.getText()))
	        	        	                                  { 
	        	        	                                      jParser.nextToken();
	        	        	                                      optionObj.optionText = jParser.getText();
	        	        	                                  }
	        	                                        	  else if("correct".equals(jParser.getText()))
	        	        	                                  { 
	        	        	                                      jParser.nextToken();
	        	        	                                      optionObj.optionCorrectness = jParser.getText();
	        	        	                                      quesObj.optionList.add(optionObj);
	        	        	                                  }
	        	                                        	  
	        	                                        	  
	        	                                          }
	        	                                      
	        	                                      }
	                                        		  
	        	                                      // options ends here
	        	                                      levelObj.questionList.add(quesObj);
	                                        		  
	                                        		  
	                                        		  
	        	                                  }
	                                        	  
	                                        	  
	                                        	  
	                                          }
	                                          
	                                          
	                                      }
	                                	  
	                                	  
	                                	 // level ends here
	                                     levelList.add(levelObj);
	                                	  
	                                  }
	                                 
	                       }
	                   }
	              }
	                              
            }
	        } catch (Exception e) {
				// TODO: handle exception
			}
	  
            return levelList;
	  }
	         
	    
}
