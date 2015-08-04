package com.HomeGym.Activity;

import com.example.homegym.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class AcheivementActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acheivement);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bottommenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent;		
		switch (item.getItemId()) {

			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this); 
				overridePendingTransition(0,0);
				return true;
				
			case R.id.action_blutooth:
				intent = new Intent(AcheivementActivity.this, PreviewActivity.class);
				startActivity(intent);
				overridePendingTransition(0,0);
				return true;
				
			case R.id.action_camera:
				intent = new Intent(AcheivementActivity.this, CameraActivity.class);
				startActivity(intent);
				overridePendingTransition(0,0);
				return true;
		
			case R.id.action_exercise:
				intent = new Intent(AcheivementActivity.this, PreviewActivity.class);
				startActivity(intent);
				overridePendingTransition(0,0);
				return true;
				
				
			case R.id.action_settings:
				intent = new Intent(AcheivementActivity.this, SettingActivity.class);
				startActivity(intent);
				overridePendingTransition(0,0);
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}