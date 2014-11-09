package com.example.emailapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailActivity extends Activity {

	Button buttonSend;
	EditText textTo;
	EditText textSubject;
	EditText textMessage;
	Button buttonClear;
	EditText textCc;
	EditText textBcc;
	EditText textFrom;
	SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		preferences = SendEmailActivity.this.getSharedPreferences(
				"MyPreferences", Context.MODE_PRIVATE);
		identifyViews();
		setValues();
	}
	
	// Click listener for Clear and Send buttons
	View.OnClickListener buttonClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.buttonClear) {
				SharedPreferences.Editor editor = preferences.edit();

				editor.putString("from", "");
				editor.putString("to", "");
				editor.putString("cc", "");
				editor.putString("bcc", "");
				editor.putString("subject", "");
				editor.putString("message", "");
				editor.commit();

				setValues();
			} else {
				Intent readIntent = new Intent();
				readIntent.setClass(SendEmailActivity.this,
						ReadEmailActivity.class);

				Email email = new Email();
				email.setFrom(textFrom.getText().toString());
				email.setTo(textTo.getText().toString());
				email.setCc(textCc.getText().toString());
				email.setSubject(textSubject.getText().toString());
				email.setMessage(textMessage.getText().toString());
				readIntent.putExtra("email", email);

				startActivity(readIntent);

				/*
				 * Intent email = new Intent(Intent.ACTION_SEND);
				 * email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
				 * email.putExtra(Intent.EXTRA_CC, new String[]{ to});
				 * email.putExtra(Intent.EXTRA_BCC, new String[]{to});
				 * email.putExtra(Intent.EXTRA_SUBJECT, subject);
				 * email.putExtra(Intent.EXTRA_TEXT, message);
				 * 
				 * //need this to prompts email client only
				 * email.setType("message/rfc822");
				 * 
				 * startActivity(Intent.createChooser(email,
				 * "Choose an Email client :"));
				 */
			}
		}
	};
	
	// Identify controls from layout.
	private void identifyViews() {
		buttonSend = (Button) findViewById(R.id.buttonSend);
		buttonClear = (Button) findViewById(R.id.buttonClear);
		textTo = (EditText) findViewById(R.id.editTextTo);
		textSubject = (EditText) findViewById(R.id.editTextSubject);
		textMessage = (EditText) findViewById(R.id.editTextMessage);
		textFrom = (EditText) findViewById(R.id.editTextFrom);
		textCc = (EditText) findViewById(R.id.editTextCC);
		textBcc = (EditText) findViewById(R.id.editTextBcc);
		buttonSend.setOnClickListener(buttonClickListener);
		buttonClear.setOnClickListener(buttonClickListener);
	}

	// Set values in controls.
	private void setValues() {

		textFrom.setText(preferences.getString("from", ""));
		textTo.setText(preferences.getString("to", ""));
		textCc.setText(preferences.getString("cc", ""));
		textBcc.setText(preferences.getString("bcc", ""));
		textSubject.setText(preferences.getString("subject", ""));
		textMessage.setText(preferences.getString("message", ""));
	}

	// Activity function called when application goes in background or gets restarted.
	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences.Editor editor = preferences.edit();

		editor.putString("from", textFrom.getText().toString());
		editor.putString("to", textTo.getText().toString());
		editor.putString("cc", textCc.getText().toString());
		editor.putString("bcc", textBcc.getText().toString());
		editor.putString("subject", textSubject.getText().toString());
		editor.putString("message", textMessage.getText().toString());

		editor.commit();
	}

}
