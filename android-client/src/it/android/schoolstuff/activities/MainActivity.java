package it.android.schoolstuff.activities;

import it.android.schoolstuff.R;
import it.android.schoolstuff.tasks.GetCategoricalData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	public EditText name;
	public Spinner spinner_category, spinner_school;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		System.out.println();

		new GetCategoricalData(this).execute(getIntent().getExtras() != null);

		getActionBar().hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.bar_search:
			break;

		case R.id.bar_my_list:
			Intent i = new Intent(this, Login.class);
			this.startActivity(i);
			break;

		case R.id.bar_settings:
			i = new Intent(this, Settings.class);
			this.startActivity(i);
			break;

		}
		return false;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_search:
			Intent intent = new Intent(this, ListResults.class);
			String arr[] = { name.getText().toString(),
					(String) spinner_category.getSelectedItem(),
					(String) spinner_school.getSelectedItem() };
			intent.putExtra("params", arr);
			startActivity(intent);

		default:
			break;
		}

	}
}
