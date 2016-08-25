package hasancanzubaroglu.simpleapplocker;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



/**
 * TODO: document your custom view class.
 */
public class PassCodeTextView extends View
{
    private float circleRadius = 60;
    private int circleCount = 4;
    private float space = 50;
    Context context;

    public PassCodeTextView(Context context)
    {
        super(context);
        this.context = context;
        init(null, 0);
    }

    public PassCodeTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public PassCodeTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle)
    {

        // Load attributes


    }


    Paint circlePaint;
    Paint borderPaint;
    Paint linePaint;
    Paint textPaint;
    int widht;
    int height;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        borderPaint = new Paint();

        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(4);

        float totalWidht = (circleCount * circleRadius * 2) +  ((circleCount - 1) * space);

        float ek = 0;

        if (circleCount % 2 == 0)
        {
            ek = circleRadius;
        }

        float firstY = (totalWidht / 2) - (((circleCount / 2) * (circleRadius * 2)) +
                (circleCount / 2 - 1) * space);

        canvas.drawCircle(this.getHeight(), firstY, circleRadius, borderPaint);

    }


}