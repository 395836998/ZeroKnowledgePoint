package com.zhanglian2010.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ScrollViewDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll_view_demo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scroll_view_demo, menu);
		return true;
	}

}
