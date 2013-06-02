package it.android.schoolstuff.adapters;

import it.android.schoolstuff.R;
import it.android.schoolstuff.model.SearchResult;

import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class LsAdapter implements ListAdapter {

	Context ctx;

	int selected_index = -1;

	View views[];
	View selected;
	List<SearchResult> ls;

	public LsAdapter(Context ctx, List<SearchResult> ls) {
		this.ctx = ctx;
		this.ls = ls;

		views = new View[ls.size()];

	}

	@Override
	public int getCount() {
		return ls.size();
	}

	@Override
	public Object getItem(int position) {
		return ls.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return position;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return ls.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	public void selectRow(int position, View v) {

		if (selected != null && selected_index != position)
			unselectRow();

		selected_index = position;
		selected = views[selected_index];

	}

	public void unselectRow() {
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (views[position] == null) {

			LayoutInflater lf = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = convertView;
			if (v == null)
				v = lf.inflate(R.layout.row_view, null);

			SearchResult item = ls.get(position);

			TextView txt = (TextView) v.findViewById(R.id.description);
			txt.setText(item.description);

			if (item.picture != null && item.picture != "") {

				new DownloadImageTask((ImageView) v.findViewById(R.id.img))
						.execute(ctx.getString(R.string.HOST) + item.picture);

				views[position] = v;
			}

		}
		return views[position];
	}

	private static final class DownloadImageTask extends
			AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

}
