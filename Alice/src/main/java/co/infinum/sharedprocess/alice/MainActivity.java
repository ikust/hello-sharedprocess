package co.infinum.sharedprocess.alice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

    private static final String BOBS_MAIN_ACTIIVTY = "co.infinum.sharedprocess.bob.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        tellSecretToBob("Shhh this is a secret");
    }

    public int getBobsResource(String resourceName, String resourceType) {
        PackageManager pm = getPackageManager();

        Resources resources = null;
        if (pm != null) {
            try {
                resources = pm.getResourcesForApplication("co.infinum.sharedprocess.bob");

                int res = resources.getIdentifier(resourceName, resourceType, "co.infinum.sharedprocess.bob");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public void tellSecretToBob(String message) {
        Intent intent = new Intent();

        intent.setComponent(new ComponentName("co.infinum.sharedprocess.bob", "co.infinum.sharedprocess.bob.MainActivity"));
        intent.putExtra("secret", "secret message");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
