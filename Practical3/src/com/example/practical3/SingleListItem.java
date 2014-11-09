package com.example.practical3;

import com.example.practical3.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class SingleListItem extends Activity {

	TextView name, credit, description;
	LinearLayout mainLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.single_list_item_view);
		identifyViews();
		setValues();
	}

	// Function called when control comes back to this screen.
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences preferences = this.getSharedPreferences(
				"MyPreferences", Context.MODE_PRIVATE);

		mainLayout.setBackgroundColor(Color.parseColor(preferences.getString(
				"detailsBgColor", "black").toLowerCase()));
	}

	// Identify views from layout file.
	private void identifyViews() {
		name = (TextView) findViewById(R.id.module_name);
		credit = (TextView) findViewById(R.id.module_credit);
		;
		description = (TextView) findViewById(R.id.module_description);
		mainLayout = (LinearLayout) findViewById(R.id.detailsLayout);
	}

	// Set values in views.
	private void setValues() {
		// Set values from saved data in preferences.
		SharedPreferences preferences = this.getSharedPreferences(
				"MyPreferences", Context.MODE_PRIVATE);

		mainLayout.setBackgroundColor(Color.parseColor(preferences.getString(
				"detailsBgColor".toLowerCase(), "black")));

		Module module = (Module) getIntent().getExtras().getSerializable(
				"Module");
		name.setText(module.getName());
		credit.setText(module.getCreditInfo());
		description.setText(module.getDescription());
	}
}
