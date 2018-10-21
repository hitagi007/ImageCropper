package company.brother.imagecropper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by PL_itto on 18,October,2018
 */
public class CropTouchListener implements View.OnTouchListener {
    private static final String TAG = "PL_Itto";
    private Bitmap mOriginBitmap;
    private Bitmap mAlterBitmap;
    private float mStartX;
    private CropView mView;
    private float mStartY;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private static final int TOUCH_THRESHOLD = 100;
    private long mTouchDownLong = 0;

    public CropTouchListener() {
        this(null, null);
    }

    public CropTouchListener(Bitmap originBitmap, CropView view) {
        mOriginBitmap = originBitmap;
        mView = view;
        initCanvas();
        mPath = new Path();
    }

    private void initCanvas() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new CornerPathEffect(30));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Log.d(TAG, "onTouch: " + event.getAction());
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTouchDownLong = System.currentTimeMillis();
                mPath = new Path();
                mStartX = event.getX();
                mStartY = event.getY();
                mPath.moveTo(mStartX, mStartY);
                break;
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();
//                mPath.moveTo(mStartX, mStartY);
                mPath.lineTo(x, y);
                mView.draw(mPath, mPaint);
                mView.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float x = event.getX();
                float y = event.getY();
                mView.concatDraw(new CropModel(mStartX, mStartY), new CropModel(event.getX(), event.getY()), mPaint);
            }

        }
        return true;
    }
}
