package com.example.quizproj;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private ArrayList<String> questionsList;
	
	private ArrayList<String[]> optionsList;	
	
	private ArrayList<Integer>inputList,answersList;
	
	private int currentKey;
	
	private Button button0,button1,button2;
	
	private TextView question;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button0 = (Button)findViewById(R.id.button0);
      
        button1 = (Button)findViewById(R.id.button1);        

        button2 = (Button)findViewById(R.id.button2);      
        
        question = (TextView)findViewById(R.id.question);
                
    }
    
    //Using onResume instead of onCreate for TRY AGAIN button.
    
    @Override
    protected void onResume(){
    	super.onResume();
    	
    	currentKey = 0;
    	
        setLists();
        
        moveToNextQuestion();
    }
    
    public void setLists(){
    	
    	questionsList = new ArrayList<String>();
    	
    	optionsList = new ArrayList<String[]>();
    	
    	answersList = new ArrayList<Integer>();
    	
    	
    	
    	//Question
    	questionsList.add("How many half days are in one day?");
    	//Options (0,1,2)
    	optionsList.add(new String[]{"2.1","1.2","2"}); 
    	//Answer key (2)
    	answersList.add(2);
    	
    	//Question
    	questionsList.add("How many eggs are in a half dozen?");
    	//Options (0,1,2)
    	optionsList.add(new String[]{"8","6","24"}); 
    	//Answer key (1)
    	answersList.add(1);   	
    	
    	
    	
    	
    }
    
    
    public void submitAnswer(View view){
    	
    	//if inputList is not initialized IE is null, create new ArrayList. Else change nothing, IE (inputList = inputList) 
    	inputList = (inputList == null) ? new ArrayList<Integer>(questionsList.size()) : inputList;
    	String tag = (String)view.getTag();
    	int answerKey = (tag.equals("option0")) ? 0 : (tag.equals("option1")? 1 : (tag.equals("option2")) ? 2 : -1);
    	
    	inputList.add(currentKey, answerKey);
    	//increment
    	currentKey++;
    	
    	 
     //contrasts size. size() negates 1 because arrays always start @ 0 and size() starts at 1.	 
    if((questionsList.size()-1)>=currentKey)    	
    	moveToNextQuestion();
    else
    	launchResultsActivity();
    	
    }
    
    public void moveToNextQuestion(){
    	
    	//change textview and button(s) text to reflect current question/options scheme.
    	
    	question.setText(questionsList.get(currentKey));
    	
    	button0.setText(optionsList.get(currentKey)[0]);
    	
    	button1.setText(optionsList.get(currentKey)[1]);
    	
    	button2.setText(optionsList.get(currentKey)[2]);
    	
    	
    }
    
    
    @SuppressWarnings("unchecked")
	public ArrayList<String>[] buildLiteralAnswers(){
    	 ArrayList<String>correctAnswers = new ArrayList<String>();
    	 ArrayList<String>userAnswers = new ArrayList<String>();
    	 
    	 
    	 for(int i = 0;i<questionsList.size();i++){
    		 //add from optionsList that corresponds to particular question. answersList.get(i) corresponds with the correct answer key to the question
    		 
    		 correctAnswers.add(optionsList.get(i)[answersList.get(i)]);
    		 userAnswers.add(optionsList.get(i)[inputList.get(i)]);    		 
    	 }
    	 
    	 ///static array
    	 return new ArrayList[]{correctAnswers,userAnswers};
    }
    
    
    public void launchResultsActivity(){
    	//Create intent to build new activity
    	Intent i = new Intent(this, ResultsActivity.class);
    	ArrayList<String>[] list = buildLiteralAnswers();
    	i.putExtra("QUESTIONS", questionsList);
    	i.putExtra("CORRECT_ANSWERS", list[0]);    	
    	i.putExtra("RESULTS", list[1]);
    	startActivity(i);  	
    }


}
