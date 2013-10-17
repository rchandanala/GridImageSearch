package com.codepath.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etSearchTerm;
	Button btnSearch;
	GridView gvSearchResults;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageArrayAdapter adapter;
	String imageSize = "Small";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setViews();
	}
	
	private void setViews() {
		etSearchTerm = (EditText) findViewById(R.id.etSearchTerm);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvSearchResults = (GridView) findViewById(R.id.gvSearchResults);
		adapter = new ImageArrayAdapter(this, imageResults);
		gvSearchResults.setAdapter(adapter);
		gvSearchResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView av, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				ImageResult imageInfo = adapter.getItem(position);
				Intent I = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				I.putExtra("selectedImage", imageInfo);
				startActivity(I);
			}
			
		});
		
		/*gvSearchResults.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public void onSearch(View v){
		String query = etSearchTerm.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=0&v=1.0&q=" + Uri.encode(query)
				+ "&imgsz=" + imageSize.toLowerCase()
				, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				JSONArray jsonImages;
				
				try {
					
					
					jsonImages = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					adapter.addAll(ImageResult.fromJSONArray(jsonImages));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if ( requestCode == 1){
			String size = (String) data.getExtras().get("imageSize");
			imageSize = size;
		}
	}
	
	public void UpdateSettings(MenuItem m) {
		// TODO Auto-generated method stub
       Intent I = new Intent(this, SettingsActivity.class);
       startActivityForResult(I, 1);
	}

}
