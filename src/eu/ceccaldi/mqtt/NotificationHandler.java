package eu.ceccaldi.mqtt;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

public class NotificationHandler {
	protected final String message;
	protected final Activity context;
	protected JSONObject jsonMessage;

	public NotificationHandler(String message, Activity context) {
		this.message = message;
		this.context = context;
	}

	protected void parseJsonMessage() throws JSONException {
		jsonMessage = new JSONObject(message);
	}
}