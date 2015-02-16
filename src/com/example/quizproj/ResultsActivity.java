package com.example.quizproj;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		//retrieve lists that were sent from the intent in MainActivity's launchResultsActivity
		ArrayList<String> questions = getIntent().getExtras().getStringArrayList("QUESTIONS");
		ArrayList<String> answerKeys =  getIntent().getExtras().getStringArrayList("CORRECT_ANSWERS");
		ArrayList<String> userAnswerKeys =  getIntent().getExtras().getStringArrayList("RESULTS");	
	
		TableLayout layout = (TableLayout) findViewById(R.id.parentTable);

       for(int i =0; i<(questions.size());i++){
    	   layout.addView(buildRow(questions.get(i),answerKeys.get(i),userAnswerKeys.get(i),layout.getLayoutParams().width));	   
       }
	}
	//builds report for particular question/answer set
	public TableRow buildRow(String question, String correctAnswer, String userAnswer, int width){
		TableRow row = new TableRow(this);
		
        if(correctAnswer.equals(userAnswer))
		row.setBackgroundColor(Color.GREEN);
        else row.setBackgroundColor(Color.RED);
        
		row.setLayoutParams(rowParams());
		TextView view = new TextView(this);
		view.setTextSize(15F);
        view.setText("Q:"+question+"\nANS:"+correctAnswer+"\nYOUR ANS:"+userAnswer);
		 
		 
		row.addView(view);

		
		return row;
		
		
	}
	
	/// basically using this to space between blocks.
	
	public TableLayout.LayoutParams rowParams(){
		TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 0);
        return layoutParams;
		
	}
	
	public void reset(View view){
		//closes this activity, resumes MainActivity
		finish();
	}

}
