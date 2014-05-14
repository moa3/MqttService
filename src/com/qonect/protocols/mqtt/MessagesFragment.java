package com.qonect.protocols.mqtt;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MessagesFragment extends ListFragment {
	
	public ArrayAdapter<String> adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        int layout = android.R.layout.simple_list_item_1;
 
        adapter = new ArrayAdapter<String>(getActivity(), layout);
        setListAdapter(adapter);
    }
    
    public void updateAdapter(String message) {
    	adapter.insert(message, 0);
    }
}
