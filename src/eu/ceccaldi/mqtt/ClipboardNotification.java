package eu.ceccaldi.mqtt;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardNotification extends NotificationHandler implements
		ActiveNotification {

	public ClipboardNotification(String message, Activity context) {
		super(message, context);
	}

	@Override
	public void doAction() {
		ClipboardManager clipboard = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("label", message);
		clipboard.setPrimaryClip(clip);
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
