package it.android.schoolstuff.activities;

import it.android.schoolstuff.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

	SharedPreferences prefs;
	EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		edit = (EditText) findViewById(R.id.email);

		prefs = getPreferences(Context.MODE_PRIVATE);

		String logged_user = prefs.getString("user", "");

		if (logged_user != "") {
			edit.setText(logged_user);
			// do auto login
			doLogin(logged_user);
		}

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go:

			String user = edit.getText().toString();

			Editor editor = prefs.edit();
			editor.putString("user", user);
			editor.apply();

			// TODO check user se valido
			doLogin(user);

			break;

		default:
			break;
		}
	}

	private void doLogin(String user) {
		Intent intent = new Intent(this, ListResults.class);
		// TODO pass parameter
		startActivity(intent);
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
			break;

		case R.id.bar_settings:
			i = new Intent(this, Settings.class);
			this.startActivity(i);
			break;

		}
		return false;
	}
}
