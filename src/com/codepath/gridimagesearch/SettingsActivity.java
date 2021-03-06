package com.codepath.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;

public class SettingsActivity extends Activity {
	Spinner spSize;
	Spinner spColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		spSize = (Spinner) findViewById(R.id.spinner1);
		spColor = (Spinner) findViewById(R.id.spinner2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void SaveSettings(View v) {
		// TODO Auto-generated method stub
		String value = spSize.getSelectedItem().toString();
		Intent I = new Intent();
		I.putExtra("imageSize", value);
		I.putExtra("imageColor", spColor.getSelectedItem().toString());
		setResult(1, I);
		finish();
	}

}
