package wangdaye.com.geometricweather.ui.image;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;

public class SunDrawable extends Drawable {

    private Paint paint;

    @ColorInt private int coreColor;
    @Size(2) @ColorInt private int[] haloColors;

    private float alpha;
    private Rect bounds;

    private float radius;
    private float cx, cy;

    public SunDrawable() {
        this.paint = new Paint();
        paint.setAntiAlias(true);

        this.coreColor = Color.rgb(254, 214, 117);
        this.haloColors = new int[] {
                Color.rgb(249, 183, 93),
                Color.rgb(252, 198, 101),
        };

        this.alpha = 1;
        this.bounds = getBounds();

        ensurePosition(bounds);
    }

    private void ensurePosition(Rect bounds) {
        float boundSize = Math.min(bounds.width(), bounds.height());
        radius = (float) (Math.sin(Math.PI / 4) * boundSize / 2) - 2;
        cx = (float) (1.0 * bounds.width() / 2 + bounds.left);
        cy = (float) (1.0 * bounds.height() / 2 + bounds.top);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        this.bounds = bounds;
        ensurePosition(bounds);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        paint.setAlpha((int) (alpha * 255));

        paint.setColor(haloColors[0]);
        canvas.drawRect(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius,
                paint
        );

        paint.setColor(haloColors[0]);
        int restoreCount = canvas.save();
        canvas.rotate(45, cx, cy);
        canvas.drawRect(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius,
                paint
        );
        canvas.restoreToCount(restoreCount);

        paint.setColor(coreColor);
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicWidth() {
        return bounds.width();
    }

    @Override
    public int getIntrinsicHeight() {
        return bounds.height();
    }
}
