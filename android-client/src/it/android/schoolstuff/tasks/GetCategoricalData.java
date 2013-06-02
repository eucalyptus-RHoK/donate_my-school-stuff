package it.android.schoolstuff.tasks;

import it.android.schoolstuff.R;
import it.android.schoolstuff.activities.MainActivity;
import it.android.schoolstuff.gcm.CommonUtilities;
import it.android.schoolstuff.gcm.ServerUtilities;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class GetCategoricalData extends AsyncTask<Boolean, Integer, Object> {

	static List<String> categories;
	static List<String> schools;

	static final String TAG = GetCategoricalData.class.getSimpleName();

	AsyncTask<Void, Void, Void> mRegisterTask;

	//

	final MainActivity act;

	public GetCategoricalData(MainActivity act) {
		this.act = act;
	}

	@Override
	protected Object doInBackground(Boolean... skip) {
		if (skip[0])
			return null;

		// ---------------------------- gcm -----------------------------------

		// checkNotNull(CommonUtilities.SERVER_URL, "SERVER_URL");
		// checkNotNull(CommonUtilities.SENDER_ID, "SENDER_ID");
		//
		// // db = new DBAdapter(this);
		//
		// // Make sure the device has the proper dependencies.
		// GCMRegistrar.checkDevice(act);
		// // Make sure the manifest was properly set - comment out this line
		// // while developing the app, then uncomment it when it's ready.
		// GCMRegistrar.checkManifest(act);
		// // setContentView(R.layout.activity_demo);
		// // <<<<<<<<<<<<<<<===================
		// // mDisplay = (TextView) findViewById(R.id.display); TODO
		// // b_start = (Button) findViewById(R.id.button_start);
		//
		// act.registerReceiver(mHandleMessageReceiver, new IntentFilter(
		// CommonUtilities.DISPLAY_MESSAGE_ACTION));
		// final String regId = GCMRegistrar.getRegistrationId(act);
		// if (regId.equals("")) {
		// Log.i(TAG, "DEVICE IS REGISTERING");
		// // Automatically registers application on startup.
		// GCMRegistrar.register(act, CommonUtilities.SENDER_ID);
		// Log.i(TAG, "DEVICE IS REGISTERed");
		// } else {
		// // Device is already registered on GCM, check server.
		// if (GCMRegistrar.isRegisteredOnServer(act)) {
		// // Skips registration.
		// // <<<<<<<<<<<<<<<=================== TODO
		// // mDisplay.append(getString(R.string.already_registered) +
		// // "\n");
		//
		// Log.i(TAG, "ALREADY REGISTERED");
		// } else {
		// // Try to register again, but not in the UI thread.
		// // It's also necessary to cancel the thread onDestroy(),
		// // hence the use of AsyncTask instead of a raw thread.
		// final Context context = act;
		// mRegisterTask = new AsyncTask<Void, Void, Void>() {
		//
		// @Override
		// protected Void doInBackground(Void... params) {
		// ServerUtilities.register(context, regId);
		// return null;
		// }
		//
		// @Override
		// protected void onPostExecute(Void result) {
		// mRegisterTask = null;
		// }
		//
		// };
		// mRegisterTask.execute(null, null, null);
		// }
		// }

		// ---------------------------------------------------------------------

		// stuff

		Log.d("ATAG", "GIRO1");

		try {
			String jstring = ServerUtilities.get(act.getString(R.string.HOST)
					+ "/interface/bootstrap/");

			try {
				JSONObject root = new JSONObject(jstring);

				categories = new LinkedList<String>();

				JSONArray jcat = root.getJSONArray("categories");
				for (int i = 0; i < jcat.length(); i++) {
					categories.add(jcat.getJSONObject(i).getString("value"));
				}

				schools = new LinkedList<String>();

				JSONArray jschool = root.getJSONArray("schools");
				for (int i = 0; i < jschool.length(); i++) {
					schools.add(jschool.getJSONObject(i).getString("value"));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getLocalizedMessage());

				// TODO HANDLE EXCEPTION
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			// TODO HANDLE EXCEPTION

		}

		return null;
	}

	@Override
	protected void onPostExecute(Object result) {

		act.setContentView(R.layout.search_layout);

		act.spinner_category = (Spinner) act.findViewById(R.id.tagSpinner);
		act.spinner_category.setAdapter(new ArrayAdapter<String>(act,
				android.R.layout.simple_spinner_item, categories));

		act.spinner_school = (Spinner) act.findViewById(R.id.schoolSpinner);
		act.spinner_school.setAdapter(new ArrayAdapter<String>(act,
				android.R.layout.simple_spinner_item, schools));

		act.name = (EditText) act.findViewById(R.id.name);

		((Button) act.findViewById(R.id.b_search))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						act.onClick(v);

					}
				});

		act.getActionBar().show();

	}

	//

	//

	public static List<String> getCategories() {
		return categories;
	}

	public static List<String> getSchools() {
		return schools;
	}

	private void checkNotNull(Object reference, String name) {
		if (reference == null) {
			throw new NullPointerException(act.getString(R.string.error_config,
					name));
		}
	}

	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(
					CommonUtilities.EXTRA_MESSAGE);
			Log.i(TAG, newMessage);
			// mDisplay.append(newMessage + "\n");
		}
	};
}
