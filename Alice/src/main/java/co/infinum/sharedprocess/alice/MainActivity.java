package co.infinum.sharedprocess.alice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * An Activity that demonstrates starting Activity from another application sharing the same
 * sharedUserId and passing data to it.
 * <p>
 * A prerequisite for this is that sharedUserId must be set in AndroidManifests of both applications
 * to the same value, and both applications must be signed with the same keystore.
 */
public class MainActivity extends ActionBarActivity {

    /**
     * Fully qualified name of Bob's activity that will be started.
     */
    private static final String BOBS_MAIN_ACTIIVTY = "co.infinum.sharedprocess.bob.MainActivity";

    /**
     * Package identifier of Bob's application.
     */
    private static final String BOBS_PACKAGE = "co.infinum.sharedprocess.bob";

    /**
     * Key of the extra that will be passed to Bob's activity.
     */
    private static final String EXTRA_SECRET_MESSAGE = "message";

    /**
     * Edit text for entering message to send.
     */
    private EditText messageEditText;

    /**
     * Send button.
     */
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText messageEditText = (EditText) findViewById(R.id.editTextMessage);
        Button sendButton = (Button) findViewById(R.id.buttonSend);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellSecretToBob(messageEditText.getText().toString());
            }
        });
    }

    /**
     * Returns a resource identifier for a given resource name and type.
     *
     * @param resourceName name of the resource
     * @param resourceType type of the resource (e.g. drawable, string, layout...)
     * @return resource identifier or 0 if there is no such resource
     */
    public int getBobsResource(String resourceName, String resourceType) {
        PackageManager pm = getPackageManager();

        Resources resources = null;
        if (pm != null) {
            try {
                resources = pm.getResourcesForApplication(BOBS_PACKAGE);

                int res = resources.getIdentifier(resourceName, resourceType, BOBS_PACKAGE);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    /**
     * Starts Bob's activity defined with fully qualified class name in BOBS_MAIN_ACTIVITY constant.
     * Bob's application package name is defined in BOBS_PACKAGE. A String extra with the key
     * EXTRA_SECRET_MESSAGE is passed, containing the message given in argument.
     *
     * @param message message to pass to Bob's activity
     */
    public void tellSecretToBob(String message) {
        Intent intent = new Intent();

        intent.setComponent(new ComponentName(BOBS_PACKAGE, BOBS_MAIN_ACTIIVTY));
        intent.putExtra(EXTRA_SECRET_MESSAGE, message);
        startActivity(intent);
    }


}
