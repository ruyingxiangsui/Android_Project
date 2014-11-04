package lee.activity;

import lee.message.R;
import lee.util.ContactsUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				ContactsUtil.getContacts(StartActivity.this);
				Intent intent = new Intent(StartActivity.this,
						MainActivity.class);
				startActivity(intent);
				StartActivity.this.finish();
				overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			}
		}, 200);
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
