package com.am.framework.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.am.framework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends AppCompatActivity {

    private static final String TAG = MapsActivity.class.getSimpleName();

    @BindView(R.id.btn_maps_intent)
    Button mIntentMapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        mIntentMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = getString(R.string.pref_location_default);
                Uri geoLocation = Uri.parse("geo:0,0?q=" + address);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d(TAG, "Couldn't call " + geoLocation.toString() + ", no receiving apps installed!");
                }
            }
        });
    }
}
