package edu.glut.tiny.data.setting;

import androidx.preference.PreferenceDataStore;


public class SettingDataStore extends PreferenceDataStore {

    @Override
    public void putBoolean(String key, boolean value) {
        super.putBoolean(key, value);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return super.getBoolean(key, defValue);
    }
}
