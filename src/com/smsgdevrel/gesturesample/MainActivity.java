package com.example.gesturesample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.gesture.*;
import com.samsung.android.sdk.gesture.SgestureHand.Info;
import com.smsgdevrel.gesturesample.R;

public class MainActivity extends Activity {
	
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initGestures();
	}

	private void initGestures() {
		// TODO Auto-generated method stub
		Sgesture sg = new Sgesture();
		tv = (TextView) findViewById(R.id.txt);
		try {
		sg.initialize(getBaseContext());
		}
		catch(SsdkUnsupportedException se) {
			Toast.makeText(getBaseContext(), "Gestures not supported", Toast.LENGTH_SHORT).show();
			return;
		}
		if ( ! sg.isFeatureEnabled(Sgesture.TYPE_HAND_PRIMITIVE)) {
			Toast.makeText(getBaseContext(), "Gestures not enabled", Toast.LENGTH_SHORT).show();
			return;
		}
		
		SgestureHand sghand = new SgestureHand(getMainLooper(), sg);
		tv.setText("Starting ");
		sghand.start(Sgesture.TYPE_HAND_PRIMITIVE, new SgestureHand.ChangeListener() {

			@Override
			public void onChanged(Info arg0) {
				// TODO Auto-generated method stub
				tv.setText("Changed " + arg0.getAngle());
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
