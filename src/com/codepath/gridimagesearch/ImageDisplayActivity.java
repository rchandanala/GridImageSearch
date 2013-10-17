package com.codepath.gridimagesearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.loopj.android.image.SmartImageView;

public class ImageDisplayActivity extends Activity {
	SmartImageView ivSelectedImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		ivSelectedImage = (SmartImageView) findViewById(R.id.ivSelectedImage);
		ImageResult selectedImage = (ImageResult) getIntent().getExtras().get("selectedImage");
		ivSelectedImage.setImageUrl(selectedImage.getFullUrl());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

}
