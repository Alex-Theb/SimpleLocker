package hasancanzubaroglu.simpleapplocker;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityDetectingService extends AccessibilityService
{

@Override
protected void onServiceConnected() {
    super.onServiceConnected();

    //Configure these here for compatibility with API 13 and below.

    AccessibilityServiceInfo config = new AccessibilityServiceInfo();
    config.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
    config.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;

    if (Build.VERSION.SDK_INT >= 16)
        //Just in case this helps
        config.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;

    setServiceInfo(config);
}

@Override
public void onAccessibilityEvent(final AccessibilityEvent event) {
        if (event == null ) {
            return;
        } else if(event.getPackageName() == null && event.getClassName() == null){
            return;
        }

        }



private ActivityInfo tryGetActivity(ComponentName componentName) {
    try {
        return getPackageManager().getActivityInfo(componentName, 0);
    } catch (PackageManager.NameNotFoundException e) {
        return null;
    }
}
@Override
public void onInterrupt() {
}                
}
//`enter code here`uses-permission android:name="android.permission.BIND_ACCESSIBILITY_SERVICE" />
