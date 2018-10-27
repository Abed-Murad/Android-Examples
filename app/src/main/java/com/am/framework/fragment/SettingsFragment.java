package com.am.framework.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.am.framework.R;


/**
 * Never Forget to Add <item name="preferenceTheme">@style/PreferenceThemeOverlay</item> to styles.xml
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
        OnPreferenceChangeListener,
        OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference p = preferenceScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
//                String preferenceValue = sharedPreferences.getString(p.getKey(), "");
//                setPreferenceSummary(p, preferenceValue);
            }
        }
        Preference preference = findPreference(getString(R.string.pref_city_name_key));
        preference.setOnPreferenceChangeListener(this);
    }

    /**
     * @param preference : on PreferenceScreen
     * @param newValue : form the user
     * @return Need to return true to activity onSharedPreferenceChanged()
     * for the EditTextPreference
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }

    /**
     * Called after onPreferenceChange() returns true in case of EditTextPreference
     * @param sharedPreferences
     * @param key
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference != null) {
            if (!(preference instanceof CheckBoxPreference)) {
//                String value = sharedPreferences.getString(preference.getKey(), "");
//                setPreferenceSummary(preference, value);
            }
        }
    }




    public void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (!preference.equals("")) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else if (preference instanceof EditTextPreference) {
            preference.setSummary(value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
