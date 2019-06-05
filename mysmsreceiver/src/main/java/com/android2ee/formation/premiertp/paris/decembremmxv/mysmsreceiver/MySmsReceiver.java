package com.android2ee.formation.premiertp.paris.decembremmxv.mysmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MySmsReceiver extends BroadcastReceiver {
    public MySmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MySmsReceiver","MySmsReceiver has received a sms ! yep :)");
        Intent startServiceSMS=new Intent(context,MySmsService.class);
        startServiceSMS.putExtras(intent);
        startServiceSMS.setAction(intent.getAction());
        context.startService(startServiceSMS);
    }
}
