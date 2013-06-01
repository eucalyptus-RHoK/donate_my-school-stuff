package com.rhok.donatemyschoolstuff.adapters;

import android.content.Context;
import android.view.View;

public abstract class LayoutAdapter {

	Context ctx;

	public LayoutAdapter(Context ctx) {
		this.ctx = ctx;
	}

	public abstract void onClick(View v);

}
