Shared user ID application example
===================

Example of two Android applications signed with the same keystore and using the same *sharedUserId*.

The project contains two applications: Alice and Bob.
Alice can start an activity from Bob and send a mesage.

##Setting up sharedUserId applications

The first step is to add the same android:sharedUserId property in the root element in AndroidManifest.xml of both applications: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="co.infinum.sharedprocess.alice"
    android:sharedUserId="co.infinum.sharedprocess">
```

Second step is to sign both applications with the same keystore.

##Starting an Activity from other Application

In order to start an Activity from other Application with the same shartedUserId use the following code: 

```java
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
    
public void tellSecretToBob(String message) {
  Intent intent = new Intent();
  intent.setComponent(new ComponentName(BOBS_PACKAGE, BOBS_MAIN_ACTIIVTY));
  intent.putExtra(EXTRA_SECRET_MESSAGE, message);
  startActivity(intent);
}
```

##Fetching resources from other Application

To fetch a resource from another application use the following method: 

```java
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
```
