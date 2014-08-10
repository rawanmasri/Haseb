package com.asaltech.haseb.activities.adapter;

import com.asaltech.haseb.activities.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SearchAdapter extends ArrayAdapter<String>{

	private String [] objects;
	private Context context;
	
	public SearchAdapter(Context context, int resource, String[] objects) {
		super(context, resource);
		this.objects = new String [objects.length + 1];
		for(int index = 0; index <  objects.length ; index++) {
			this.objects[index] = objects[index];
		}
		
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = null;
		if(objects.length != 1) {
			if(objects[position] == null) {
				view = inflator.inflate(R.layout.row_fake_item, parent, false);
				
			} else {
				view = inflator.inflate(R.layout.row_search_item, parent, false);
				TextView tv = (TextView) view.findViewById(R.id.userName);
				tv.setText(objects[position]);
			}
		} else {
			view = inflator.inflate(R.layout.row_search_empty_item, parent, false);
		}
		
		
		return view;
	}
	
	@Override
	public int getCount() {
		return objects.length;
	}

}//I want to teach you dear :) 
