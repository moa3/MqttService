package com.qonect.protocols.mqtt;

import java.util.ArrayList;

import eu.ceccaldi.mqtt.MessagesAdapter;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MessagesFragment extends ListFragment {

	public ArrayAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapter = new MessagesAdapter(getActivity(),
				new ArrayList<String>());
		setListAdapter(adapter);
	}

	// called from the activity but could/should be attached to the service
	// directly
	public void updateAdapter(String message) {
		((MessagesAdapter) adapter).prepend(message);

	}
}
