package com.aracy.load.more.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Sun.bl
 * @version [1.0, 2016/8/25]
 */
public class ItemDivider extends RecyclerView.ItemDecoration {

    private Drawable mDrawable; //divider分割线

    private boolean mDrawBottom; //是否绘制底部分割线

    private int mDividerLeftPadding, mDividerRightPadding,
            mDividerTopPadding, mDividerBottomPadding; //分割线自身的padding


    public ItemDivider(Drawable drawable) {
        this.mDrawable = drawable;
    }

    public ItemDivider(Context context, int dividerRes) {
        this(ContextCompat.getDrawable(context, dividerRes));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


        final int left = parent.getPaddingLeft() + mDividerLeftPadding;
        final int right = parent.getWidth() - parent.getPaddingRight() - mDividerRightPadding;

        int childCount = parent.getChildCount();

        if (!mDrawBottom) {
            childCount = childCount - 1;
        }

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin + mDividerTopPadding;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }

        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {


        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        final int lastPosition = parent.getAdapter().getItemCount() - 1;
        if (!mDrawBottom && position == lastPosition) {
            outRect.set(0, 0, 0, 0);
        }
        outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight() + mDividerTopPadding + mDividerBottomPadding);
    }

    /**
     * 是否绘制底部分割线
     *
     * @param drawBottom
     */
    public void setDrawBottom(boolean drawBottom) {
        mDrawBottom = drawBottom;
    }

    /**
     * 为divider 设置Padding
     *
     * @param left   左面
     * @param top    上面
     * @param right  右面
     * @param bottom 下面
     */
    public void setDividerPadding(int left, int top, int right, int bottom) {
        mDividerBottomPadding = bottom;
        mDividerLeftPadding = left;
        mDividerRightPadding = right;
        mDividerTopPadding = top;
    }

    /***
     * 为divider 设置Padding
     *
     * @param left  左padding
     * @param right 右padding
     */
    public void setDividerPadding(int left, int right) {
        setDividerPadding(left, 0, right, 0);
    }


}
