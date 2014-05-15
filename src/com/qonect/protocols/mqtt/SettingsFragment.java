package com.qonect.protocols.mqtt;

import org.apache.log4j.Logger;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment
							  implements OnSharedPreferenceChangeListener 
{
	private static final Logger LOG = Logger.getLogger(SettingsFragment.class);
	
    public static final String MQTT_BROKER_URL = "mqtt_url";
	public static final String MQTT_TOPICS = "mqtt_topics";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        
        sharedPref.registerOnSharedPreferenceChangeListener(this);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        
        Preference connectionPref = findPreference(MQTT_BROKER_URL); 
        connectionPref.setSummary(sharedPref.getString(MQTT_BROKER_URL, ""));
    }
	
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference connectionPref = findPreference(key);
        // Set summary to be the user-description for the selected value
        LOG.debug(sharedPreferences.getString(key, ""));
        connectionPref.setSummary(sharedPreferences.getString(key, ""));
        
        //Update the Service
        MqttServiceDelegate.onSharedPreferenceChanged(this.getActivity(), sharedPreferences, key);
    }
}