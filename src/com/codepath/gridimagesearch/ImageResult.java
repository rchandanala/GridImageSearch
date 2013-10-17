package com.codepath.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	private String fullUrl;
	private String thumbUrl;
	private String Title;
	
	public ImageResult(JSONObject jsonImage) {
		// TODO Auto-generated constructor stub
		try {
			fullUrl = jsonImage.getString("url");
			thumbUrl = jsonImage.getString("tbUrl");
			Title = jsonImage.getString("title");
		} catch(JSONException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public String getTitle() {
		return Title;
	}
	
	public String getFullUrl() {
		return fullUrl;
	}
	
	public String getThumbUrl() {
		return thumbUrl;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fullUrl;
	}
	
	public static ArrayList<ImageResult> fromJSONArray(JSONArray jsonImages){
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int x =0; x< jsonImages.length(); x++) {
			try {
				results.add(new ImageResult(jsonImages.getJSONObject(x)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
		
	}
}
