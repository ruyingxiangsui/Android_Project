package lee.task;

import java.util.ArrayList;
import java.util.Random;

import lee.activity.MainActivity;
import lee.engine.MsgEngine;
import lee.message.R;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import bean.Msg;

public class SendMsgAsyncTask extends
		AsyncTask<ArrayList<Msg>, Integer, String> {

	private MainActivity context;
	private volatile int sendedSuccessCount = 0;
	private int totleCount = 0;

	private BroadcastReceiver sendMessage = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 判断短信是否发送成功
			switch (getResultCode()) {
			case Activity.RESULT_OK:

				sendedSuccessCount++;
				break;
			default:
				break;
			}
			/*Log.d("send",
					intent.getStringExtra(MsgEngine.SENT_SMS_ACTION_EXTRA_KEY));*/
			if (sendedSuccessCount == totleCount) {
				context.unregisterReceiver(this);
				NotificationManager nm = (NotificationManager) context
						.getSystemService(Context.NOTIFICATION_SERVICE);
				/*Notification note = new Notification.Builder(context)
						.setContentTitle(
								context.getString(R.string.send_done_notification_title))
						.setContentText(
								String.format(
										context.getString(R.string.send_done_notification_content),
										totleCount, sendedSuccessCount))
						.setSmallIcon(R.drawable.sms)
						.setLights(Color.rgb(0, 255, 0), 5000, 5000)
						.setDefaults(Notification.DEFAULT_SOUND).build();*/
				Builder note = new Notification.Builder(context)
				.setContentTitle(
						context.getString(R.string.send_done_notification_title))
				.setContentText(
						String.format(
								context.getString(R.string.send_done_notification_content),
								totleCount, sendedSuccessCount))
				.setSmallIcon(R.drawable.messages)
				.setLights(Color.rgb(0, 255, 0), 5000, 5000)
				.setDefaults(Notification.DEFAULT_SOUND);
				//第一个参数设置不同值时会显示多个notification
				nm.notify(new Random().nextInt(MsgEngine.RANDOM_NUMBER), note.getNotification());
			}

		}
	};

	public SendMsgAsyncTask(MainActivity context) {
		this.context = context;

	}

	@Override
	protected String doInBackground(ArrayList<Msg>... params) {
		SmsManager smsManager = SmsManager.getDefault();
		ArrayList<Msg> msgs = params[0];
		// 注册广播 发送消息
		context.registerReceiver(sendMessage, new IntentFilter(
				MsgEngine.SENT_SMS_ACTION));
		totleCount = msgs.size();
		for (Msg m : msgs) {

			ArrayList<String> divideContents = smsManager.divideMessage(m
					.getToWhom() + "," + m.getMsgContent());
			for (String text : divideContents) {
				Intent intent = new Intent(MsgEngine.SENT_SMS_ACTION);

				intent.putExtra(MsgEngine.SENT_SMS_ACTION_EXTRA_KEY,
						m.getPhoneNumber());

				PendingIntent pendIntent = PendingIntent
						.getBroadcast(context, m.hashCode(), intent,
								PendingIntent.FLAG_UPDATE_CURRENT);
				smsManager.sendTextMessage(m.getPhoneNumber(), null, text,
						pendIntent, null);

			}
		}

		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
