package it.android.schoolstuff.activities;

import it.android.schoolstuff.R;
import it.android.schoolstuff.gcm.ServerUtilities;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Publish extends Activity {
	ImageView imgV;
	Bitmap bitmap;

	TextView name;

	TextView description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_post);

		imgV = (ImageView) findViewById(R.id.take_image);

		name = (TextView) findViewById(R.id.name);
		description = (TextView) findViewById(R.id.description);

	}

	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.take_image:
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(takePictureIntent, 0);
			break;

		case R.id.b_post:

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); // bm is the
																	// bitmap
																	// object
			byte[] b = baos.toByteArray();

			String base64Pic = Base64.encodeToString(b, 0);

			final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					2);

			nameValuePairs.add(new BasicNameValuePair("image", base64Pic));
			nameValuePairs.add(new BasicNameValuePair("image_ext", "png"));

			nameValuePairs.add(new BasicNameValuePair("object_name", name
					.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("description",
					description.getText().toString()));

			final String url = this.getString(R.string.HOST)
					+ "/interface/publish/";

			new Thread(new Runnable() {

				@Override
				public void run() {
					ServerUtilities.postData(url, nameValuePairs);

				}
			}).start();

			finish();

			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Bundle extras = data.getExtras();
		bitmap = (Bitmap) extras.get("data");
		imgV.setImageBitmap(bitmap);

	}

}
