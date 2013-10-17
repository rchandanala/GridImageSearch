package com.codepath.gridimagesearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageArrayAdapter(Context context,
			List<ImageResult> objects) {
		super(context, R.layout.smart_image, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if (convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflater.inflate(R.layout.smart_image, parent, false);
		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;
		
	}

}
