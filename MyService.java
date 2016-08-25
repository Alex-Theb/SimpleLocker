package hasancanzubaroglu.simpleapplocker;

import android.app.Service;
import android.app.usage.UsageStats;
import
        android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyService extends Service
{
    private static Timer timer = new Timer();
    UsageStatsManager mUsageStatsManager;

    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        lockedApps.add("com.hasancanzubaroglu.hasancanzubaroglu");
        startService();
    }

    private void startService()
    {
        timer.scheduleAtFixedRate(new mainTask(), 0, 300);
    }

    private class mainTask extends TimerTask
    {
        public void run()
        {
            toastHandler.sendEmptyMessage(0);
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped ...", Toast.LENGTH_SHORT).show();
    }


    ArrayList<String> lastApps = new ArrayList<>();
    ArrayList<String> lockedApps = new ArrayList<>();


    int index = 0;
    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            String topPackageName = "bos";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                long currentTime = System.currentTimeMillis();
                List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 1000 * 10, currentTime);
                if (stats != null)
                {
                    long lastUsedAppTime = 0;
                    for (UsageStats usageStats : stats)
                    {
                        if (usageStats.getLastTimeUsed() > lastUsedAppTime)
                        {
                            topPackageName = usageStats.getPackageName();
                            lastUsedAppTime = usageStats.getLastTimeUsed();
                        }
                    }
                    if (!topPackageName.contains("simpleapplocker"))
                    Log.d("Package Name", topPackageName);

                }

            }

            if (lastApps.size() == 0)
                lastApps.add("");
            if (!lastApps.get(lastApps.size() - 1).equals(topPackageName))
            {
                if (!topPackageName.contains("simpleapplocker"))
                    lastApps.add(topPackageName);
                if (lockedApps.contains(topPackageName))
                    lock();
            }



        }
    };

    private int countInList(ArrayList list, Object o)
    {
        int count = 0;

        for (Object ob: list)
        {
            if (ob.equals(o))
                count++;
        }
        return count;
    }
    private void lock()
    {
        Intent intent = new Intent(getApplicationContext(), ActivityPassword.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION|
                Intent.FLAG_ACTIVITY_NO_HISTORY|Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS

        );
        startActivity(intent);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

}




