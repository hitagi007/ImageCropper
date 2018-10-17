package company.brother.imagecropper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PL_itto." + MainActivity.class.getSimpleName();

    private ImageView mImageCrop;
    private Path mClipPath;
    private CropView mCropView;
    private Bitmap mBitmap, mAlterBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private float mDownX = 0;
    private float mDownY = 0;
    private float mTDownX = 0;
    private float mTDownY = 0;
    private float mUpX = 0;
    private float mUpY = 0;
    private long mLastTouchDown = 0;
    private static final int CLICK_ACTION_THRESHOLD = 100;
    private Display mDisplay;
    private Point mSize;
    private int mScreenWidth, mScreenHeight;
    private Button mBtnOK;
    private ArrayList<CropModel> mCropModelArrayList;
    private float mSmallX, mSmallY, mLargeX, mlargeY;
    private Paint mCPaint;
    private Bitmap mTemporaryBitmap;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageCrop = findViewById(R.id.im_crop_image_view);
        mCropView = findViewById(R.id.crop_view);
        mCropView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mCropView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                init();
            }
        });
    }

    private void init() {
        mProgressDialog = new ProgressDialog(this);
        mImageCrop = findViewById(R.id.im_crop_image_view);
        mCropModelArrayList = new ArrayList<>();
        mBtnOK = findViewById(R.id.btn_ok);
        mBtnOK.setOnClickListener(this);
        mDisplay = getWindowManager().getDefaultDisplay();
        mSize = new Point();
        mDisplay.getSize(mSize);
        mScreenWidth = mSize.x;
        mScreenHeight = mSize.y;
        initCanvas();
    }

    private void initCanvas() {
        Drawable d = getResources().getDrawable(R.drawable.sample);
        mBitmap = ((BitmapDrawable) d).getBitmap();
        mAlterBitmap = Bitmap.createBitmap(mScreenWidth, mScreenHeight, mBitmap.getConfig());
        CropTouchListener listener = new CropTouchListener(mBitmap, mCropView);
        mImageCrop.setOnTouchListener(listener);
//        mImageCrop.setImageBitmap(mBitmap);
        mCanvas = new Canvas(mAlterBitmap);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 15.0f}, 0));
    }


    @Override
    public void onClick(View view) {

    }


}
