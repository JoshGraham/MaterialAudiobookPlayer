package de.ph1b.audiobook.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import de.ph1b.audiobook.R;
import de.ph1b.audiobook.dialog.SeekAmountPreference;


public class PreferencesFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SeekAmountPreference seekAmountPreference = (SeekAmountPreference) findPreference(getString(R.string.change_forward_amount));
        seekAmountPreference.setSummary(getSeekTime());
    }

    private String getSeekTime() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int value = sp.getInt(getString(R.string.pref_change_amount), 20);
        return String.valueOf(value) + " " + getString(R.string.seconds);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            SeekAmountPreference seekAmountPreference = (SeekAmountPreference) findPreference(getString(R.string.change_forward_amount));
            seekAmountPreference.setSummary(getSeekTime());
        }
    };
}
