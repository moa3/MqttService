package eu.ceccaldi.mqtt;

import android.app.Activity;

public class NotificationDispatcher {
	
	public static ActiveNotification getNotifier(String topic, String message, Activity context) {
		if(topic.matches(".+/url$")) {
			return new URLNotification(message, context);
		}
		else if(topic.matches(".+/bar$")) {
			return new BarNotification(message, context);
		}
		else if(topic.matches(".+/copy$")) {
			return new ClipboardNotification(message, context);
		}
		else {
			return new BaseNotification(message, context);
		}
	}

}
