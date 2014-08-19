package com.asaltech.haseb.activities.adapter;

import java.util.ArrayList;
import java.util.List;

import com.asaltech.haseb.activities.R;
import com.example.bean.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SearchAdapter extends ArrayAdapter<String>{

	private List<User> objects;
	private Context context;
	
	public SearchAdapter(Context context, int resource, List<User> objects) {
		super(context, resource);
		this.objects = objects;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = null;
		if(objects.size() != 1) {
			if(objects.get(position) == null) {
				view = inflator.inflate(R.layout.row_fake_item, parent, false);
				
			} else {
				view = inflator.inflate(R.layout.row_search_item, parent, false);
				TextView tv = (TextView) view.findViewById(R.id.userName);
				tv.setText(objects.get(position).toString());
			}
		} else {
			view = inflator.inflate(R.layout.row_search_empty_item, parent, false);
		}
		
		
		return view;
	}
	
	@Override
	public int getCount() {
		return objects.size();
	}

}//I want to teach you dear :) 
