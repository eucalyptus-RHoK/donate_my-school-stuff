package it.android.schoolstuff.activities;

import it.android.schoolstuff.R;
import it.android.schoolstuff.tasks.GetSearchResult;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ListResults extends Activity {

	public ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		getActionBar().hide();

		//

		Bundle params = getIntent().getExtras();

		new GetSearchResult(this).execute(params.getStringArray("params"));

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
			i.putExtra("dontLoad", true);
			this.startActivity(i);
			break;

		case R.id.bar_my_list:
			i = new Intent(this, Publish.class);
			this.startActivity(i);
			break;

		case R.id.bar_settings:
			i = new Intent(this, Settings.class);
			this.startActivity(i);
			break;

		}
		return false;
	}
}
