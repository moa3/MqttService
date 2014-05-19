package eu.ceccaldi.mqtt;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MessagesAdapter extends ArrayAdapter {
	private final Context context;
	private final ArrayList values;
	private static final int MAX_SIZE = 100;

	public MessagesAdapter(Context context, ArrayList values) {
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
