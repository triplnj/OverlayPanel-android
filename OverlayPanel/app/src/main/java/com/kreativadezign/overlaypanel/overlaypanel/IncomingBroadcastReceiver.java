package com.kreativadezign.overlaypanel.overlaypanel;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import java.util.Objects;

public class IncomingBroadcastReceiver extends BroadcastReceiver {



    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context mContext, Intent intent) {

        String state = Objects.requireNonNull(intent.getExtras()).getString(TelephonyManager.EXTRA_STATE);


        assert state != null;
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)
                || state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent i = new Intent(mContext, IncomingCallActivity.class);
            i.putExtras(intent);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(i);
        }else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Intent i1 = new Intent(mContext, MainActivity.class);
            mContext.startActivity(i1);
        }






        }


}












