package eu.ceccaldi.mqtt;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class URLNotification extends NotificationHandler implements
		ActiveNotification {
	
	private boolean validURL = false;

	public URLNotification(String message, Activity context) {
		super(message, context);
		try {
			parseJsonMessage();
			validURL = true;
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public void doAction() {
		if(!isValid()) return;
		String url;
		try {
			url = jsonMessage.getString("url");
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				url = "http://" + url;
			}
			Intent browserIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse(url));
			context.startActivity(browserIntent);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isValid() {
		return validURL;
	}

}
