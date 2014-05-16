package eu.ceccaldi.mqtt;

import android.app.Activity;

public class BaseNotification extends NotificationHandler implements
		ActiveNotification {

	public BaseNotification(String message, Activity context) {
		super(message, context);
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid() {
		return true;
	}

}
