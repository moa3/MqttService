package com.qonect.protocols.mqtt;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.qonect.protocols.mqtt.MqttServiceDelegate.MessageHandler;
import com.qonect.protocols.mqtt.MqttServiceDelegate.MessageReceiver;
import com.qonect.protocols.mqtt.MqttServiceDelegate.StatusHandler;
import com.qonect.protocols.mqtt.MqttServiceDelegate.StatusReceiver;
import com.qonect.protocols.mqtt.service.MqttService;
import com.qonect.protocols.mqtt.service.MqttService.ConnectionStatus;

public class MqttTestActivity extends Activity implements MessageHandler, StatusHandler
{	
	private static final Logger LOG = Logger.getLogger(MqttTestActivity.class);
	
	private MessageReceiver msgReceiver;
	private StatusReceiver statusReceiver;
	public ArrayAdapter<String> adapter;
	public List<String> aList = new ArrayList<String>();
	private MessagesFragment listFragment;
	
	public void onBackPressed() {
		FragmentManager fragmentManager = getFragmentManager();
		if(fragmentManager.findFragmentByTag("settingsTag") == null) {
			finish();
		} else {
			fragmentManager.beginTransaction()
			.replace(R.id.fragment_container, listFragment).commit();
		}
	}

	@Override  
	public void onCreate(Bundle savedInstanceState)   
	{  
		LOG.debug("onCreate");
		super.onCreate(savedInstanceState);
        
		//Init UI
		setContentView(R.layout.main_test);
		
		//Init Receivers
		bindStatusReceiver();
		bindMessageReceiver();

		//Start service if not started
		MqttServiceDelegate.startService(this);
		if (findViewById(R.id.fragment_container) != null) {

			if (savedInstanceState != null) {
				return;
			}

			// Create an instance of ExampleFragment
			listFragment = new MessagesFragment();

			getFragmentManager().beginTransaction()
					.add(R.id.fragment_container, listFragment).commit();
		}
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.settings, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	protected void openSettings()
	{
		getFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, new SettingsFragment(), "settingsTag")
        .commit();
	}

	@Override  
	protected void onDestroy()   
	{ 
		LOG.debug("onDestroy");
		
		MqttServiceDelegate.stopService(this);
		
		unbindMessageReceiver();
		unbindStatusReceiver();
		
	    super.onDestroy(); 
	}
	
	private void bindMessageReceiver(){
		msgReceiver = new MessageReceiver();
		msgReceiver.registerHandler(this);
		registerReceiver(msgReceiver, 
			new IntentFilter(MqttService.MQTT_MSG_RECEIVED_INTENT));
	}
	
	private void unbindMessageReceiver(){
		if(msgReceiver != null){
			msgReceiver.unregisterHandler(this);
			unregisterReceiver(msgReceiver);
			msgReceiver = null;
		}
	}
	
	private void bindStatusReceiver(){
		statusReceiver = new StatusReceiver();
		statusReceiver.registerHandler(this);
		registerReceiver(statusReceiver, 
			new IntentFilter(MqttService.MQTT_STATUS_INTENT));
	}
	
	private void unbindStatusReceiver(){
		if(statusReceiver != null){
			statusReceiver.unregisterHandler(this);
			unregisterReceiver(statusReceiver);
			statusReceiver = null;
		}
	}
	
	private String getCurrentTimestamp(){
		return new Timestamp(new Date().getTime()).toString();  
	}

	@Override
	public void handleMessage(String topic, byte[] payload) {
		String message = new String(payload);
		
		LOG.debug("handleMessage: topic="+topic+", message="+message);
		listFragment.updateAdapter(message);
				
//		if(timestampView != null)timestampView.setText("When: "+getCurrentTimestamp());
//		if(topicView != null)topicView.setText("Topic: "+topic);
//		if(messageView != null)messageView.setText("Message: "+message);
	}	

	@Override
	public void handleStatus(ConnectionStatus status, String reason) {
		LOG.debug("handleStatus: status="+status+", reason="+reason);
//		if(statusView != null)statusView.setText("Status: "+status.toString()+" ("+reason+")");
	}
}
