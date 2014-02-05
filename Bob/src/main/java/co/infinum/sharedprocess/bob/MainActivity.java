package co.infinum.sharedprocess.bob;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * An activity that receives a message over intent.
 * <p/>
 * Message is read from a string extra passed in intent with the key defined in
 * EXTRA_SECRET_MESSAGE_CONSTNT.
 */
public class MainActivity extends ActionBarActivity {

    /**
     * Key of the extra that will be passed to Bob's activity.
     */
    private static final String EXTRA_SECRET_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        displayMessage(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        displayMessage(intent);
    }

    /**
     * Displays the received message in a dialog.
     */
    private void displayMessage(Intent intent) {
        Bundle extras = intent.getExtras();

        if (extras != null && extras.containsKey(EXTRA_SECRET_MESSAGE)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Message from Alice");
            builder.setMessage(extras.getString(EXTRA_SECRET_MESSAGE));
            builder.setPositiveButton("Ok", null);
            builder.show();
        }
    }


}
