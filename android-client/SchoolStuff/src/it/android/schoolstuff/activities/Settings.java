package it.android.schoolstuff.activities;

import it.android.schoolstuff.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Settings extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
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
			Intent i = new Intent(this, MainActivity.class);
			i.putExtra("dontLoad", false);
			this.startActivity(i);
			break;

		case R.id.bar_my_list:
			i = new Intent(this, Publish.class);
			this.startActivity(i);
			break;

		case R.id.bar_settings:
			break;

		}
		return false;
	}
}
