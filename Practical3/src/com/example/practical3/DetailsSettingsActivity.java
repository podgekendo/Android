package com.example.practical3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DetailsSettingsActivity extends Activity {

	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_settings);
		identifyViews();
		setRadioButtonChecked();
	}

	// Save button click
	View.OnClickListener buttonClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			int selectedId = radioGroup.getCheckedRadioButtonId();

			radioButton = (RadioButton) findViewById(selectedId);

			// Save background color so that it is preserved when application is
			// closed.
			SharedPreferences preferences = DetailsSettingsActivity.this
					.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();

			editor.putString("detailsBgColor", radioButton.getText().toString());
			editor.commit();

			Toast.makeText(DetailsSettingsActivity.this,
					"Your new Background colour is now set !!", Toast.LENGTH_SHORT).show();

			// Forcefully go to previous screen.
			onBackPressed();
		}
	};

	// Identify views from layout file.
	private void identifyViews() {
		radioGroup = (RadioGroup) findViewById(R.id.radioGroupBgColor);
		btnSave = (Button) findViewById(R.id.btn_save_bg_color);
		btnSave.setOnClickListener(buttonClickListener);
	}

	// Set checked radio button as per saved value.
	private void setRadioButtonChecked() {
		SharedPreferences preferences = this.getSharedPreferences(
				"MyPreferences", Context.MODE_PRIVATE);
		if (preferences.getString("detailsBgColor", "").equals("Black"))
			radioGroup.check(R.id.radioBlack);
		else if (preferences.getString("detailsBgColor", "").equals("Blue"))
			radioGroup.check(R.id.radioBlue);
		else
			radioGroup.check(R.id.radioGreen);
	}
}
