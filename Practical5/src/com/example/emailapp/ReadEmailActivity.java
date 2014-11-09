package com.example.emailapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ReadEmailActivity extends Activity {

	TextView from, to, cc, subject, message;
	Button back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_email);
		identifyViews();
		setValues();
	}

	// Identify controls from layout.
	private void identifyViews()
	{
		from = (TextView)findViewById(R.id.textViewFromValue);
		to = (TextView)findViewById(R.id.textViewToValue);
		cc = (TextView)findViewById(R.id.textViewCCValue);
		subject = (TextView)findViewById(R.id.textViewSubjectValue);
		message = (TextView)findViewById(R.id.textViewMessageValue);
		back = (Button)findViewById(R.id.buttonBack);
		back.setOnClickListener(buttonClickListener);
	}
	
	// Set values in controls.
	private void setValues()
	{
		Email email = (Email) getIntent().getExtras().getSerializable(
				"email");
		
			from.setText(email.getFrom());
			to.setText(email.getTo());
			cc.setText(email.getCc());
			subject.setText(email.getSubject());
			message.setText(email.getMessage());
	}
	
	// Back button click listener.
	View.OnClickListener buttonClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			onBackPressed();
		}
	};
	
}
