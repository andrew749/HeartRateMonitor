package com.andrew749.heart_rate_monitor;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.andrew749.heart_rate_monitor.Activities.HeartRateMonitor;


/**
 * This class extends the View class and is designed draw the heartbeat image.
 *
 */
public class HeartbeatView extends View {

    private static final Matrix matrix = new Matrix();
    private static final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    private static int parentWidth = 0;
    private static int parentHeight = 0;

    public HeartbeatView(Context context, AttributeSet attr) {
        super(context, attr);


    }

    public HeartbeatView(Context context) {
        super(context);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth, parentHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas == null) throw new NullPointerException();
        if (HeartRateMonitor.getCurrent() == HeartRateMonitor.TYPE.GREEN) paint.setColor(Color.GREEN);
        else paint.setColor(Color.RED);

        int parentcenterx=canvas.getWidth()/2;
        int radius=canvas.getHeight()/2;
        canvas.drawCircle(parentcenterx-radius,canvas.getHeight()-radius,radius,paint);
    }
}
