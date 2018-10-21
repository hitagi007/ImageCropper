package company.brother.imagecropper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by PL_itto on 22,October,2018
 */
public class TouchImageView extends AppCompatImageView {

    private ScaleGestureDetector mScaleDetector;
    private float mMinScale = 0.25f;
    private float mMaxScale = 20.0f;
    private State mState;

    public TouchImageView(Context context) {
        this(context, null);
    }

    public TouchImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        setScaleType(ScaleType.MATRIX);
        setState(State.NONE);
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    private enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private ScaleListener() {

        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }
    }
}
