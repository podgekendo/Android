package com.example.practical3;

import com.example.practical3.R;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AndroidListViewActivity extends Activity {

	ListView listview;
	Button btnPreferences;
	HashMap<String, String> moduleHash;

	@Override
	//every activity started through a sequence of methods calls.
	//First method is onCreate().
	public void onCreate(Bundle savedInstanceState) {
	 //you tell vm run your code aswell as the existing code in onCreate() parent class.
		//if you leave out this line only your own code is ran.
		super.onCreate(savedInstanceState);
		//set the content layout to following
		setContentView(R.layout.activity_android_list_view);
		identifyViews();

		// storing string resources into Array , from values->list_data.xml
		String[] modules = getResources().getStringArray(R.array.modules);
		setModulesData();
		
		// Binding resources Array to ListAdapter
		// The ListAdapter which is responsible for maintaining the data backing this list
		//and for producing a view to represent an item in that data set.
		
		
		//Define a new adaptor and assign it to the listview
		//first parameter (this) context.
		//Second parameter - layout for the row (list_view)
		//Third Parameter - ID of the TextView to which the data is written (id.label)
		//Forth - The array of data (modules)
		listview.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.label, modules));

	}

	// Preference button click listener.
	View.OnClickListener buttonClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent settingsIntent = new Intent();
			settingsIntent.setClass(AndroidListViewActivity.this,
					DetailsSettingsActivity.class);
			startActivity(settingsIntent);
		}
	};

	// Listview item click listener.
	AdapterView.OnItemClickListener itemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			// selected item
			String module = ((TextView) view).getText().toString();

			Module model = new Module();
			model.setName(moduleHash.get(module).split("\\|")[0]);
			model.setCreditInfo(moduleHash.get(module).split("\\|")[1]
					.split(" ")[0]);
			if (moduleHash.get(module).split("\\|").length > 2)
				model.setDescription(moduleHash.get(module).split("\\|")[2]);
			else
				model.setDescription("");
			// Launching new Activity on selecting single List Item
			Intent i = new Intent(getApplicationContext(), SingleListItem.class);
			// sending data to new activity
			i.putExtra("Module", model);
			startActivity(i);

		}
	};

	// Identify layout views.
	private void identifyViews()
	{
		listview = (ListView) findViewById(R.id.listView);
		// listening to single list item on click
		listview.setOnItemClickListener(itemClickListener);
		btnPreferences = (Button) findViewById(R.id.btn_Preferences);
		btnPreferences.setOnClickListener(buttonClickListener);
	}
	
	// Set modules in collection.
	private void setModulesData() {
		moduleHash = new HashMap<String, String>();
		moduleHash
				.put("Comp 30440",
						"Comp 30440 Software Engineering Project|20-credit module|Is part of your(Conversion) Programme.Deals with the creation of a twitter sentiment analysis application");
		moduleHash
				.put("Comp 30570",
						"Comp 30570 OS Unix|10-credit module|Architecture of Unix. Unix kernel. Booting Unix.");
		moduleHash
				.put("Comp 30580",
						"Comp 30580 Introduction to Java Programming|10-credit module|Java is a programming language developed by Sun Microsystems. This tool scans the Java source file and produces a documentation summary in HTML format.");
		moduleHash
				.put("Comp 47290",
						"Comp 47290 Green, Sustainable Data Centre Management|10-credit module| Data and relationships are represented in a flat, two-dimensional table that preserves relational structuring.");
		moduleHash.put("Comp 47330",
				"Comp 47330 Practical Android Programming|10-credit module|  through the study of a range of Data Structures and Algoriithms.");
		moduleHash
				.put("Comp 47160",
						"Comp 47160 Advanced Java, Data Structures and Algorithms|10-credit module|This module is aimed further enhancing the students knowledge of Java");
		moduleHash
				.put("Comp 40725",
						"Comp 40725 Introduction to Relational Databases and SQL Programming|10-credit module|A relational database is a database that stores information about both the data and how it is related. ");
		moduleHash
		.put("Comp 20040",
				"Comp 20040 Data Structures and Algorithms II|10-credit module|Investigate a number of more advanced data structures, including: Trees, Graphs, and Maps.");
		moduleHash
				.put("Comp 20080",
						"Comp 20080 Computer Science Engineering|10-credit module|Review of Computer Components; Introduction to C++; Modularisation and Data Abstraction");
	}
}