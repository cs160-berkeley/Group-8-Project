package com.cs160.team8.ally;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class PressHelp extends Activity {

    private TextView mTextView;
    TextView help_text;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final String ON_THE_WAY = "Help is on the way!";
    private static final String HOLD_FOR_HELP = "Hold for help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_help);
        Typeface main_type = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Bold.ttf");
        help_text = (TextView) findViewById(R.id.title);
        help_text.setTypeface(main_type);
        final RelativeLayout press = (RelativeLayout) findViewById(R.id.press);
        final View circ_one = (View) findViewById(R.id.circone);
        final View circ_two = (View) findViewById(R.id.circtwo);
        help_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("ACTION DETECTED");
                    CountDownTimer count = new CountDownTimer(4000, 1000) {
                        public void onTick(final long millisUntilFinished) {
                            final int j = (int) millisUntilFinished;
                            System.out.println("TIME LEFT:" + millisUntilFinished);

//                            help_text.setText(""+(int)millisUntilFinished/1000);
//                            help_text.setGravity(Gravity.CENTER_HORIZONTAL);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println(""+millisUntilFinished);
                                    long secondsLeft = millisUntilFinished/1000;

                                    if(!help_text.equals(HOLD_FOR_HELP) && !help_text.equals(ON_THE_WAY)) {
//                                        press.removeAllViews();
                                        help_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 54);
                                        press.setBackgroundColor(Color.parseColor("#C4EF95"));
                                        circ_one.setVisibility(View.VISIBLE);
                                        circ_two.setVisibility(View.VISIBLE);
                                        help_text.setText("   "+ (secondsLeft) );



                                    }
                                }
                            });
                        }

                        public void onFinish() {
                            //  Send message to the phone asking for help.
//                            help_text.setGravity(Gravity.LEFT);
                            help_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                            press.setBackgroundColor(Color.parseColor("#9DCB6B"));
                            circ_one.setVisibility(View.GONE);
                            circ_two.setVisibility(View.GONE);
                            help_text.setText("Help is on the way!");
                        }
                    };
                    count.start();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!help_text.equals(ON_THE_WAY) ){
                        help_text.setText("Hold for help");
                    }
                }
                return true;
            }
        });


//        press.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(PressHelp.this, WaitThree.class);
//                startActivity(intent);
//            }
//        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "PressHelp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.team8.ally/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "PressHelp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.team8.ally/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
