package it.android.schoolstuff.tasks;

import it.android.schoolstuff.R;
import it.android.schoolstuff.activities.ListResults;
import it.android.schoolstuff.adapters.LsAdapter;
import it.android.schoolstuff.gcm.ServerUtilities;
import it.android.schoolstuff.model.SearchResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.ListView;

public class GetSearchResult extends
		AsyncTask<String, Integer, List<SearchResult>> {
	ListResults act;

	public GetSearchResult(ListResults act) {
		this.act = act;
	}

	@Override
	protected List<SearchResult> doInBackground(String... param) {

		String user_id = param[0];
		String name = param[1];
		String category = param[2];
		String school = param[3];

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		nameValuePairs.add(new BasicNameValuePair("searchstr", name));

		if (!category.equalsIgnoreCase("-1")) {
			nameValuePairs.add(new BasicNameValuePair("searchcat", category));
		}
		if (!school.equalsIgnoreCase("-1")) {
			nameValuePairs.add(new BasicNameValuePair("searchschool", school));
		}

		String json = ServerUtilities.postData(act.getString(R.string.HOST)
				+ "/interface/search/", nameValuePairs);

		try {
			JSONArray root = new JSONArray(json);

			System.out.println(json);

			List<SearchResult> ls = new LinkedList<SearchResult>();

			for (int i = 0; i < root.length(); i++) {
				JSONObject elem = root.getJSONObject(i);

				SearchResult res = new SearchResult();

				res.objectPK = elem.getInt("object_pk");
				res.objectName = elem.getString("object_name");
				res.tags = elem.getString("tags");
				res.school = elem.getString("school");
				res.category = elem.getString("category");
				res.description = elem.getString("description");
				res.picture = elem.getString("picture");
				res.owner = elem.getString("owner");

				ls.add(res);

			}
			return ls;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
	}

	@Override
	protected void onPostExecute(List<SearchResult> result) {

		act.setContentView(R.layout.search_result);

		act.listview = ((ListView) act.findViewById(R.id.list_search_result));

		act.listview.setAdapter(new LsAdapter(act, result));

		// TODO set adapter

		act.getActionBar().show();
	}
}
