package com.rhok.donatemyschoolstuff.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.rhok.donatemyschoolstuff.R;

public class SearchFragment extends Fragment {

	Spinner tagSpinner, schoolSpinner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.search_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		tagSpinner = (Spinner) getActivity().findViewById(R.id.tagSpinner);
		schoolSpinner = (Spinner) getActivity()
				.findViewById(R.id.schoolSpinner);

		// TODO spinners from server

	}

}
