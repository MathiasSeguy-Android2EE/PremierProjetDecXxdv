package com.android2ee.formation.premiertp.paris.decembremmxv.mysmsreceiver;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.util.Log;

public class MySmsService extends Service {
    private static final String SMS_RECEIVE_INTENT_NAME= "android.provider.Telephony.SMS_RECEIVED";
    public MySmsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MySmsService", "MySmsService oh mais trop super j'ai reçu un sms :))");
        if(intent.getAction().equals(SMS_RECEIVE_INTENT_NAME)){
            //Retrieve the bundle that handles the Messages
            Bundle bundle=intent.getExtras();
            if(bundle!=null){
            	//Retrieve the data store in the SMS
                Object[] pdus=(Object[])bundle.get("pdus");
				//Declare the associated SMS Messages
                SmsMessage[] smsMessages=new SmsMessage[pdus.length];
				//Rebuild your SMS Messages
                for(int i=0;i<pdus.length;i++){
                    smsMessages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                }
				//Parse your SMS Message
                SmsMessage currentMessage;
                String body,from;
                long when;
                for(int i=0;i<smsMessages.length;i++){
                    currentMessage=smsMessages[i];
                    body=currentMessage.getDisplayMessageBody();
                    from=currentMessage.getDisplayOriginatingAddress();
                    when=currentMessage.getTimestampMillis();
                    Log.e("MySmsService", "MySmsService sms de "+from+" said "+body);
					displayNotif(body,from,when);
                }
            }
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }


    /**
	 * The pendingIntent sent by the system when the notification is clicked
	 */
	private PendingIntent pdIntent;
	/**
	 * The id of the notification
	 */
	int UniqueNotificationId = 4112008;

	/**
	 * Display the notification using the parameters below
	 *
	 * @param body
	 * @param from
	 * @param time
	 */
	private void displayNotif(String body, String from, long time) {
		String dude = getName(from);
		//pendingIntent instanciation
		Intent intentStartMainActivity=new Intent(this,MainActivity.class);
		Bundle data=new Bundle();
		data.putString("toto","un truc");
		intentStartMainActivity.putExtras(data);
		pdIntent=PendingIntent.getActivity(this,0,intentStartMainActivity,0);
		//adding a sound
		Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setAutoCancel(true)
				.setContentIntent(pdIntent)
				.setContentText(body)
				.setContentTitle(dude)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_big_notif))
				.setLights(0x99FF0000, 0, 1000)// don't work
				.setNumber(41108)
				.setSound(soundUri)//set the sound
				.addAction(R.drawable.ic_big_notif, "Love", pdIntent)//add an action
				.addAction(R.drawable.ic_big_notif, "You", pdIntent)//add an action
				.setOngoing(false)
				.setPriority(Integer.MIN_VALUE)
				.setSmallIcon(R.drawable.ic_big_notif)
				.setSubText("tel : " + from)
				.setTicker("You received a new SMS from " + from)
				.setVibrate(new long[] { 100, 200, 100, 200, 100 }) // don't work
				.setWhen(System.currentTimeMillis())
				.setStyle(new NotificationCompat.BigTextStyle()
					.bigText(body)
					.setSummaryText("*tel : " + from)
					.setBigContentTitle(":)"+dude)
				);

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
		notificationManager.notify(UniqueNotificationId, builder.build());
	}

	public String getName(String from) {
		String contact = "unknow";
		// Android 2.0 and later
		Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(from));
		// Query the filter URI
		String[] projection = new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME };
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
		while (cursor.moveToNext()) {
			contact = cursor.getString(nameFieldColumnIndex);
		}
		cursor.close();
		return contact;
	}

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
