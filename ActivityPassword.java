package hasancanzubaroglu.simpleapplocker;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import hasancanzubaroglu.simpleapplocker.customviews.FontTextView;
import hasancanzubaroglu.simpleapplocker.customviews.IndicatorDots;
import hasancanzubaroglu.simpleapplocker.customviews.PinLockListener;
import hasancanzubaroglu.simpleapplocker.customviews.PinLockView;

public class ActivityPassword extends AppCompatActivity
{

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private ImageView ivAppIcon;
    private FontTextView tvAppName;


    private PinLockListener pinLockListener;

    @Override
    protected void onCreate(Bundle svavedInstanceState)
    {
        super.onCreate(svavedInstanceState);
        setContentView(R.layout.activity_password);

        initViews();
        initListeners();

    }


    private void initViews()
    {
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        ivAppIcon = (ImageView) findViewById(R.id.ivAppIcon);
        tvAppName = (FontTextView) findViewById(R.id.tvAppName);

        ivAppIcon.setImageDrawable(getAppIconDrawable("com.hasancanzubaroglu.hasancanzubaroglu"));
        tvAppName.setText(getAppName("com.hasancanzubaroglu.hasancanzubaroglu"));

        mPinLockView.attachIndicatorDots(mIndicatorDots);

    }

    private void initListeners()
    {
        pinLockListener = new PinLockListener()
        {
            @Override
            public void onComplete(String pin)
            {
                if (checkPin(pin))
                {
                    finish();
                    overridePendingTransition(0, 0);
                }
                else
                {
                    mPinLockView.resetPinLockView();
                }
            }

            @Override
            public void onEmpty()
            {

            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin)
            {

            }
        };

        mPinLockView.setPinLockListener(pinLockListener);

    }

    private boolean checkPin(String pin)
    {
        return pin.trim().equals("5636");
    }

    public Drawable getAppIconDrawable(String packageName)
    {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Drawable drawableAppIcon = null;
        try
        {
            drawableAppIcon = packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e)
        {

        }

        return drawableAppIcon;

    }

    private String getAppName(String s)
    {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        try
        {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(s, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e)
        {
            return null;
        }

    }


}
