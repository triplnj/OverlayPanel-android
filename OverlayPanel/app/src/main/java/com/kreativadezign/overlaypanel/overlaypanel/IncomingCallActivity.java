package com.kreativadezign.overlaypanel.overlaypanel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class IncomingCallActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);


        try {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);


            String number = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TextView text = findViewById(R.id.text);
            text.setText(number);

            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
            Cursor cursor = getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(0);
                cursor.close();
                TextView ime = findViewById(R.id.name);
                ime.setText(name);
            }


            Uri phoneUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
            @SuppressLint("Recycle") Cursor cursor1 = getContentResolver().query(phoneUri, new String[]{ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI}, null, null, null);

            if(cursor1 != null && cursor1.moveToFirst()){
                String image = cursor1.getString(0);
                ImageView iv = findViewById(R.id.imageView);

                try{
                    iv.setImageURI(Uri.parse(image));
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }




        } catch (Exception e) {

            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);

    }



}



