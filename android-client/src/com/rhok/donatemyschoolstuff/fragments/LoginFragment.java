package com.rhok.donatemyschoolstuff.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rhok.donatemyschoolstuff.R;

public class LoginFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.login_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		SharedPreferences prefs = getActivity().getPreferences(
				Context.MODE_PRIVATE);

		String logged_user = prefs.getString("user", "");

		if (logged_user != "") {

			// do auto login

		}

	}

}
