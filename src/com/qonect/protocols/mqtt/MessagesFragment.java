package com.qonect.protocols.mqtt;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MessagesFragment extends ListFragment {

	public ArrayAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapter = new LimitedArrayAdapter(getActivity(),
				new ArrayList<String>());
		setListAdapter(adapter);
	}

	// called from the activity but could/should be attached to the service
	// directly
	public void updateAdapter(String message) {
		((LimitedArrayAdapter) adapter).prepend(message);

	}

	public class LimitedArrayAdapter extends ArrayAdapter {
		private final Context context;
		private final ArrayList values;
		private static final int MAX_SIZE = 100;

		public LimitedArrayAdapter(Context context, ArrayList values) {
			super(context, android.R.layout.simple_list_item_1, values);
			this.context = context;
			this.values = values;
		}

		public void prepend(String message) {
			// add new message at the beginning
			values.add(0, message);
			// limit the size of the list
			if (values.size() == MAX_SIZE) {
				values.remove(MAX_SIZE - 1);
			}
			notifyDataSetChanged();
		}
	}
}
