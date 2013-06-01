package com.rhok.donatemyschoolstuff.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.rhok.donatemyschoolstuff.R;
import com.rhok.donatemyschoolstuff.adapters.LayoutAdapter;
import com.rhok.donatemyschoolstuff.adapters.PhoneLayoutAdapter;
import com.rhok.donatemyschoolstuff.adapters.TabletLayoutAdapter;

public class MainActivity extends Activity {

	LayoutAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int mask = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);
		switch (mask) {

		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

			Fragment searchFrag = getFragmentManager().findFragmentById(
					R.id.searchFragment);

			adapter = new PhoneLayoutAdapter(this);

			break;
		default:
			super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

			searchFrag = getFragmentManager().findFragmentById(
					R.id.searchFragment);
			Fragment loginFrag = getFragmentManager().findFragmentById(
					R.id.loginFragment);

			adapter = new TabletLayoutAdapter(this);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		adapter.onClick(v);
	}

}
