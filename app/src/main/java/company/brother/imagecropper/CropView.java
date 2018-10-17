package company.brother.imagecropper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by PL_itto on 18,October,2018
 */
public class CropView extends View {
    private Path mPath;
    private Paint mPaint;
    private boolean mDone = false;
    private CropModel mStart, mEnd;

    public CropView(Context context) {
        super(context);
    }

    public CropView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CropView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint != null && mPath != null) {
            canvas.drawPath(mPath, mPaint);
        }
        if (mDone) {
            Path enPath = new Path();
            enPath.moveTo(mStart.getX(), mStart.getY());
            enPath.lineTo(mEnd.getX(), mEnd.getY());
            canvas.drawPath(enPath, mPaint);
        }

    }

    public void draw(Path path, Paint paint) {
        mPaint = paint;
        mPath = path;
        invalidate();
    }

    public void concatDraw(CropModel first, CropModel last, Paint paint) {
        mStart = first;
        mEnd = last;
        mDone = true;
        mPaint = paint;
        invalidate();
    }
}
