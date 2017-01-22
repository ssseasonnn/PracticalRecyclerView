package zlc.season.practicalrecyclerview.decoration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2017/1/10
 * FIXME
 */
public class ShadowItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private final Rect mBounds = new Rect();

    private Context mContext;
    private ColorDrawable mShadowColor;
    private PositionStrategy mStrategy;
    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;

    /**
     * Creates a divider {@link android.support.v7.widget.RecyclerView.ItemDecoration} that can be
     * used with a
     * {@link android.support.v7.widget.LinearLayoutManager}.
     *
     * @param context     Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or
     *                    {@link #VERTICAL}.
     */
    public ShadowItemDecoration(Context context, int orientation, @ColorRes int color,
                                PositionStrategy strategy) {
        setOrientation(orientation);
        mShadowColor = new ColorDrawable(ContextCompat.getColor(context, color));
        mContext = context;
        this.mStrategy = strategy;
    }

    /**
     * Set the shadow color
     *
     * @param color color res
     */
    public void setShadowColor(@ColorRes int color) {
        this.mShadowColor = new ColorDrawable(ContextCompat.getColor(mContext, color));
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * {@link android.support.v7.widget.RecyclerView.LayoutManager} changes orientation.
     *
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    public void setStrategy(PositionStrategy mStrategy) {
        this.mStrategy = mStrategy;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        AbstractAdapter adapter = (AbstractAdapter) parent.getAdapter();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(child);
            final int headerSize = adapter.getHeaderSize();

            if (adapter.isData(position)) {
                if (mStrategy.strategy(position - headerSize)) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds);
                    final int bottom = child.getBottom();
                    final int top = child.getTop();
                    mShadowColor.setBounds(left, top, right, bottom);
                    mShadowColor.draw(canvas);
                }
            }
        }
        canvas.restore();
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        AbstractAdapter adapter = (AbstractAdapter) parent.getAdapter();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(child);
            final int headerSize = adapter.getHeaderSize();

            if (adapter.isData(position)) {
                if (mStrategy.strategy(position - headerSize)) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds);
                    final int right = mBounds.right;
                    final int left = mBounds.left;
                    mShadowColor.setBounds(left, top, right, bottom);
                    mShadowColor.draw(canvas);
                }
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.setEmpty();
    }

    /**
     * Decide which position should be draw shadow.
     */
    public interface PositionStrategy {
        boolean strategy(int position);
    }
}