package com.mokoversity.example.wearable0;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}

		zeroY();
	}

	private void zero() {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
			.setContentTitle("Zero")
			.setContentText("You're at step zero.")
			.setSmallIcon(R.drawable.bg_eliza);
		
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		mNotificationManager.notify(0x07, builder.build());
	}

    // set background on wearbale device
	private void one() {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
			.setContentTitle("Onw")
			.setContentText("You're at step one.")
			.setSmallIcon(R.drawable.bg_eliza)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg_eliza));
		
		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);		
		notificationManager.notify(0x08, builder.build());
	}

    // Add second page
    private void two() {
        // main notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Two")
                .setContentText("You're at step two.")
                .setSmallIcon(R.drawable.bg_eliza)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg_eliza));

        // second page
        NotificationCompat.Builder secondPageNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Second Page")
                .setContentText("This is second page");

        // Add second page with wearable extender and extend the main notification
        Notification notification = new WearableExtender()
                .addPage(secondPageNotification.build())
                .extend(builder)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0x09, notification);
    }

    // Add action
	private void three() {
		// Build an intent for an action
		Intent actionIntent = new Intent(Intent.ACTION_VIEW);
		PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, 0);

        // Notification with action
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
			.setContentTitle("Three")
			.setContentText("You're at step three.")
			.setSmallIcon(R.drawable.bg_eliza)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg_eliza))
			.addAction(R.drawable.ic_full_reply, "Click me", actionPendingIntent);

        Notification notification = new WearableExtender()
            .extend(builder)
            .build();
		
		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);		
		notificationManager.notify(0x01, notification);
	}

    private void four() {
        // Build an intent for an action to make a phone call
        Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
        Uri phoneUri = Uri.parse("tel:119");
        phoneCallIntent.setData(phoneUri);
        PendingIntent phoneCallPendingIntent = PendingIntent.getActivity(this, 0, phoneCallIntent, 0);

        // Notification with action
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Four")
                .setContentText("You're at step four.")
                .setSmallIcon(R.drawable.bg_eliza)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg_eliza))
                .addAction(R.drawable.ic_full_reply, "Call me", phoneCallPendingIntent);

        Notification notification = new WearableExtender()
                .extend(builder)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0x01, notification);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
