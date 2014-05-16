package eu.ceccaldi.mqtt;

import android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class BarNotification extends NotificationHandler implements
		ActiveNotification {

	public BarNotification(String message, Activity context) {
		super(message, context);
	}

	@Override
	public void doAction() {
		NotificationManager mNotifyMgr = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotifyMgr.notify(001, buildNotif());
	}

	protected Notification buildNotif() {
		return new Notification.Builder(context)
				.setSmallIcon(R.drawable.star_on)
				.setContentTitle("MQTT Notification").setContentText(message)
				.build();
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

}