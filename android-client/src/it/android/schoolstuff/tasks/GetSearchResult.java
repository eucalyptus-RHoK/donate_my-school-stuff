package it.android.schoolstuff.tasks;

import it.android.schoolstuff.R;
import it.android.schoolstuff.tasks.GetSearchResult.QueryResult;
import android.app.Activity;
import android.os.AsyncTask;

public class GetSearchResult extends AsyncTask<String, Integer, QueryResult> {
	Activity act;

	public GetSearchResult(Activity act) {
		this.act = act;
	}

	@Override
	protected QueryResult doInBackground(String... param) {

		// stuff

		for (int i = 0; i < 99999999; i++) {

		}
		for (int i = 0; i < 99999999; i++) {

		}

		return null;
	}

	@Override
	protected void onPostExecute(QueryResult result) {

		act.setContentView(R.layout.search_result);

		// TODO set adapter

		act.getActionBar().show();
	}

	// GSON class

	public static class QueryResult {

	}

}
