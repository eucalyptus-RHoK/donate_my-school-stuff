package com.rhok.donatemyschoolstuff.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rhok.donatemyschoolstuff.R;

public class LoginFragment extends Fragment {

	SharedPreferences prefs;

	EditText edit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.login_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		edit = (EditText) this.getView().findViewById(R.id.email);

		prefs = getActivity().getPreferences(Context.MODE_PRIVATE);

		String logged_user = prefs.getString("user", "");

		if (logged_user != "") {
			edit.setText(logged_user);
			// do auto login

		}

		((Button) getView().findViewById(R.id.go))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(v.getContext(), "LOOGIN",
								Toast.LENGTH_LONG).show();

						Editor editor = prefs.edit();
						editor.putString("user", edit.getText().toString());
						editor.apply();
					}
				});

	}

}
