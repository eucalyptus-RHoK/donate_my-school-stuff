package com.rhok.donatemyschoolstuff.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rhok.donatemyschoolstuff.R;

public class MainActivity extends Activity {

	Spinner tagSpinner, schoolSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tagSpinner = (Spinner) findViewById(R.id.tagSpinner);
		schoolSpinner = (Spinner) findViewById(R.id.schoolSpinner);

		String categories[] = getCategories();
		String school[] = getSchool();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);
		tagSpinner.setAdapter(adapter);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, school);
		schoolSpinner.setAdapter(adapter);

		int mask = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);
		switch (mask) {

		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		default:
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_search:
			Intent intent = new Intent(this, SearchResult.class);
			startActivity(intent);
			// TODO call server to get answer
			break;

		default:
			break;
		}
	}

	public String[] getCategories() {
		String categories[] = { "Categoria" };
		return categories;
	}

	public String[] getSchool() {
		String school[] = { "Scuola" };
		return school;
	}

}
