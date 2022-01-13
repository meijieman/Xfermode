package com.baidu.xfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * TODO
 *
 * @author meijie05
 * @since 2021/6/17 4:25 下午
 */

public class CustomView extends View {

    private static final String TAG = "CustomView";
    private Context mContext;

    private Paint mPaint;
    private Xfermode mXfermode;
    int W = 300;
    int H = 300;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mPaint = new Paint();

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //背景色
        canvas.drawARGB(255, 255, 156, 161);
        // saveLayer 新建一个图层，新建的layer 放置在 canvas 默认 layer 上面
        int sc = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        Log.i(TAG, "onDraw: sc " + sc);
        //先绘制的是dst，后绘制的是src
        drawDst(canvas, mPaint);
        //设置xfermode
        // PorterDuffXfermode支持以下这几种模式，分别为：CLEAR、SRC、DST、SRC_OVER、DST_OVER、SRC_IN、DST_IN、SRC_OUT、DST_OUT、SRC_ATOP
        // 、DST_ATOP、XOR、DARKEN、LIGHTEN、MULTIPLY、SCREEN。
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        drawSrc(canvas, mPaint);
        //还原
        mPaint.setXfermode(null);
        // 将 layer 绘制到 canvas 上
        canvas.restoreToCount(sc);
    }


    private void drawDst(Canvas canvas, Paint p) {
        //画黄色圆形
        p.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0, 0, W * 3 / 4, H * 3 / 4), p);
    }

    private void drawSrc(Canvas canvas, Paint p) {
        //画蓝色矩形
        p.setColor(0xFF66AAFF);
        canvas.drawRect(W / 3, H / 3, W * 19 / 20, H * 19 / 20, p);
    }

}
