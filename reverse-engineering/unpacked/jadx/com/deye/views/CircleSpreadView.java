package com.deye.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.deye.entity.BluetoothSearthResultBean;
import com.mxchipapp.R;
import com.umeng.socialize.bean.HandlerRequestCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CircleSpreadView extends View {
    private List<Integer> alphas;
    private Paint centerPaint;
    private float centerX;
    private float centerY;
    private Map<String, float[][]> cliekedRecoredMap;
    private int delayMilliseconds;
    private int distance;
    private List<BluetoothSearthResultBean> mBluetoothSearthResultBeans;
    private IClickedItemListsner mIClickedItemListsner;
    private int maxRadius;
    private int maxWidth;
    private int radius;
    private int spreadColor;
    private Paint spreadPaint;
    private List<Integer> spreadRadius;

    public interface IClickedItemListsner {
        void onClickedItem(String str);
    }

    public void setData(List<BluetoothSearthResultBean> list) {
        this.mBluetoothSearthResultBeans.clear();
        this.mBluetoothSearthResultBeans.addAll(list);
    }

    public void setIClickedItemListsner(IClickedItemListsner iClickedItemListsner) {
        this.mIClickedItemListsner = iClickedItemListsner;
    }

    public CircleSpreadView(Context context) {
        this(context, null, 0);
    }

    public CircleSpreadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleSpreadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxWidth = 300;
        this.radius = HandlerRequestCode.TWITTER_REQUEST_AUTH_CODE;
        this.distance = 5;
        this.maxRadius = 80;
        this.delayMilliseconds = 33;
        this.spreadRadius = new ArrayList();
        this.alphas = new ArrayList();
        this.cliekedRecoredMap = new HashMap();
        this.mBluetoothSearthResultBeans = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleSpreadView, i, 0);
        this.radius = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.radius);
        this.maxRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.maxRadius);
        int color = typedArrayObtainStyledAttributes.getColor(1, ContextCompat.getColor(context, R.color.colorAccent));
        this.spreadColor = typedArrayObtainStyledAttributes.getColor(6, ContextCompat.getColor(context, R.color.colorAccent));
        int i2 = typedArrayObtainStyledAttributes.getInt(3, this.distance);
        this.distance = i2;
        this.delayMilliseconds = typedArrayObtainStyledAttributes.getInt(2, i2);
        this.maxWidth = typedArrayObtainStyledAttributes.getInt(0, this.maxWidth);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.centerPaint = paint;
        paint.setColor(color);
        this.centerPaint.setAntiAlias(true);
        this.alphas.add(255);
        this.spreadRadius.add(0);
        Paint paint2 = new Paint();
        this.spreadPaint = paint2;
        paint2.setAntiAlias(true);
        this.spreadPaint.setAlpha(255);
        this.spreadPaint.setColor(this.spreadColor);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.centerX = i / 2;
        this.centerY = i2 / 2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        while (true) {
            if (i >= this.spreadRadius.size()) {
                break;
            }
            int iIntValue = this.alphas.get(i).intValue();
            int iIntValue2 = this.spreadRadius.get(i).intValue();
            this.spreadPaint.setAlpha(255 - iIntValue);
            if (this.maxWidth <= iIntValue2) {
                this.spreadPaint.setAlpha(0);
            }
            canvas.drawCircle(this.centerX, this.centerY, this.radius + iIntValue2 + 5, this.spreadPaint);
            this.spreadPaint.setColor(getResources().getColor(R.color.color_white));
            canvas.drawCircle(this.centerX, this.centerY, this.radius + iIntValue2, this.spreadPaint);
            this.spreadPaint.setColor(this.spreadColor);
            this.spreadPaint.setAlpha(iIntValue);
            canvas.drawCircle(this.centerX, this.centerY, this.radius + iIntValue2, this.spreadPaint);
            if (iIntValue > 0 && iIntValue2 < this.maxWidth) {
                int i2 = this.distance;
                this.alphas.set(i, Integer.valueOf(iIntValue - i2 > 0 ? iIntValue - i2 : 1));
                this.spreadRadius.set(i, Integer.valueOf(iIntValue2 + this.distance));
            }
            i++;
        }
        List<Integer> list = this.spreadRadius;
        if (list.get(list.size() - 1).intValue() > this.maxRadius) {
            this.spreadRadius.add(0);
            this.alphas.add(255);
        }
        if (this.spreadRadius.size() >= 8) {
            this.alphas.remove(0);
            this.spreadRadius.remove(0);
        }
        canvas.drawCircle(this.centerX, this.centerY, this.radius, this.centerPaint);
        for (int i3 = 0; i3 < this.mBluetoothSearthResultBeans.size(); i3++) {
            if (i3 < 12 && this.mBluetoothSearthResultBeans.get(i3) != null && !TextUtils.isEmpty(this.mBluetoothSearthResultBeans.get(i3).getPname())) {
                drawItemView(canvas, this.mBluetoothSearthResultBeans.get(i3), i3);
            }
        }
        postInvalidateDelayed(this.delayMilliseconds);
    }

    private void drawImage(Canvas canvas, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = i3;
        rect.bottom = i4;
        rect2.left = i;
        rect2.top = i2;
        rect2.right = i + i3;
        rect2.bottom = i2 + i4;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, rect2, (Paint) null);
        }
    }

    private void drawItemView(Canvas canvas, BluetoothSearthResultBean bluetoothSearthResultBean, int i) {
        String pname = bluetoothSearthResultBean.getPname();
        Bitmap picture = bluetoothSearthResultBean.getPicture();
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.color_text_666666));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(getResources().getDimension(2131166095));
        float[][] textViewCoordinate = getTextViewCoordinate(paint, pname, i);
        float[] fArr = textViewCoordinate[0];
        canvas.drawText(pname, fArr[0], fArr[1], paint);
        if (picture != null) {
            drawImage(canvas, picture, ((int) textViewCoordinate[0][0]) + ((getTextWidth(paint, pname) / 2) - (((int) getResources().getDimension(2131166367)) / 2)), (int) ((textViewCoordinate[0][1] - getTextHeight(paint)) - getResources().getDimension(2131166367)), (int) getResources().getDimension(2131166367), (int) getResources().getDimension(2131166367));
        }
        this.cliekedRecoredMap.put(String.valueOf(i), new float[][]{new float[]{((int) textViewCoordinate[0][0]) + ((getTextWidth(paint, pname) / 2) - (((int) getResources().getDimension(2131166367)) / 2)), (int) ((textViewCoordinate[0][1] - getTextHeight(paint)) - getResources().getDimension(2131166367))}, new float[]{textViewCoordinate[0][0] + getTextWidth(paint, pname), textViewCoordinate[0][1] + getTextHeight(paint)}});
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        IClickedItemListsner iClickedItemListsner;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (String str : this.cliekedRecoredMap.keySet()) {
            float[][] fArr = this.cliekedRecoredMap.get(str);
            float[] fArr2 = fArr[0];
            if (x >= fArr2[0]) {
                float[] fArr3 = fArr[1];
                if (x <= fArr3[0] && y >= fArr2[1] && y <= fArr3[1] && (iClickedItemListsner = this.mIClickedItemListsner) != null) {
                    iClickedItemListsner.onClickedItem(str);
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private float[][] getTextViewCoordinate(Paint paint, String str, int i) {
        float[][] fArr = new float[0][];
        if (i == 0) {
            return new float[][]{new float[]{(this.centerX - (getTextWidth(paint, str) / 2)) - getResources().getDimension(2131166410), (this.centerY - getResources().getDimension(2131166466)) - (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 1) {
            return new float[][]{new float[]{(this.centerX + (getTextWidth(paint, str) / 2)) - getResources().getDimension(2131166410), this.centerY + getResources().getDimension(R.dimen.y18) + (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 2) {
            return new float[][]{new float[]{(this.centerX - (getTextWidth(paint, str) / 2)) - getResources().getDimension(2131166228), this.centerY + getResources().getDimension(2131166832) + (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 3) {
            return new float[][]{new float[]{(this.centerX - this.radius) - (getTextWidth(paint, str) / 2), (this.centerY - getResources().getDimension(2131166543)) - (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 4) {
            return new float[][]{new float[]{((this.centerX + this.radius) - (getTextWidth(paint, str) / 2)) + getResources().getDimension(2131166388), this.centerY - (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 5) {
            return new float[][]{new float[]{((this.centerX + this.radius) - (getTextWidth(paint, str) / 2)) - getResources().getDimension(2131166410), this.centerY + getResources().getDimension(2131166877) + (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 6) {
            return new float[][]{new float[]{(this.centerX + (this.radius / 2)) - (getTextWidth(paint, str) / 2), this.centerY + this.radius + getResources().getDimension(2131166366) + getTextHeight(paint)}};
        }
        if (i == 7) {
            return new float[][]{new float[]{(this.centerX - (this.radius / 2)) - (getTextWidth(paint, str) / 2), ((this.centerY + this.radius) + (getTextHeight(paint) * 2.0f)) - getResources().getDimension(2131166294)}};
        }
        if (i == 8) {
            return new float[][]{new float[]{((this.centerX + this.radius) - getTextWidth(paint, str)) + getResources().getDimension(2131166294), (this.centerY - this.radius) + getResources().getDimension(2131166710) + (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 9) {
            return new float[][]{new float[]{(this.centerX - (getTextWidth(paint, str) / 2)) + getResources().getDimension(2131166073), (this.centerY - this.radius) + getResources().getDimension(2131166477) + (getTextHeight(paint) / 2.0f)}};
        }
        if (i == 10) {
            return new float[][]{new float[]{(this.centerX - this.radius) + getResources().getDimension(2131166410), (this.centerY - this.radius) + getResources().getDimension(2131166677) + (getTextHeight(paint) / 2.0f)}};
        }
        return i == 11 ? new float[][]{new float[]{(this.centerX - this.radius) - (getTextWidth(paint, str) / 2), this.centerY + getResources().getDimension(2131166921) + getTextHeight(paint)}} : fArr;
    }

    private int getTextWidth(Paint paint, String str) {
        return (int) paint.measureText(str);
    }

    private float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.descent - fontMetrics.ascent);
    }
}
