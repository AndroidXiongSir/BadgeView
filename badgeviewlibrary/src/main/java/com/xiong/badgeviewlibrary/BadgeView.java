package com.xiong.badgeviewlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by xjf on 2018/3/14.
 */

public class BadgeView extends View  {

    /**
     * 数字paint
     */
    private Paint numberPaint;
    /**
     * 背景paint
     */
    private Paint backgroundPaint;

    public static final int SHAPE_CIRCLE = 1;
    public static final int SHAPE_RETANGLE = 2;
    public static final int SHAPE_OVAL = 3;
    public static final int SHAPE_ROUND_RECTANGLE = 4;
    public static final int SHAPE_SQUARE = 5;

    private int currentShape = SHAPE_CIRCLE;
    private int defaultTextColor = Color.WHITE;
    private int defaultTextSize ;
    private int defaultBackgroundColor = Color.RED;
    private static String showText = "";
    private int badgeGravity = Gravity.RIGHT|Gravity.TOP;
    private int leftMargin = 0;
    private int topMargin = 0;
    private int bottomMargin = 0;
    private int rightMargin = 0;
    private boolean hasBind = false;
    private int horizontalSpace = 0;
    private int verticalSpace = 0;



    public BadgeView(Context context) {
        this(context,null);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        defaultTextSize =dip2px(context,1);
        numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setColor(defaultTextColor);
        numberPaint.setStyle(Paint.Style.FILL);
        numberPaint.setTextSize(defaultTextSize);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(defaultBackgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);
        FrameLayout.LayoutParams params =  new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = badgeGravity;
        setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();
        float textH = fontMetrics.descent - fontMetrics.ascent;
        switch (currentShape){
            case SHAPE_CIRCLE:
                canvas.drawCircle(getMeasuredWidth()/2f,getMeasuredHeight()/2f,getMeasuredWidth()/2,backgroundPaint);
                canvas.drawText(showText,getMeasuredWidth()/2f,getMeasuredHeight()/2f+(textH/2f-fontMetrics.descent),numberPaint);
                break;

            case SHAPE_OVAL:
                canvas.drawOval(rectF,backgroundPaint);
                canvas.drawText(showText,getMeasuredWidth()/2f,getMeasuredHeight()/2f+(textH/2f-fontMetrics.descent),numberPaint);
                break;

            case SHAPE_RETANGLE:
                canvas.drawRect(rectF, backgroundPaint);
                canvas.drawText(showText, getMeasuredWidth() / 2f, getMeasuredHeight() / 2f + (textH / 2f - fontMetrics.descent), numberPaint);
                break;
            case SHAPE_ROUND_RECTANGLE:
                canvas.drawRoundRect(rectF, dip2px(getContext(), 5), dip2px(getContext(), 5), backgroundPaint);
                canvas.drawText(showText, getMeasuredWidth() / 2f, getMeasuredHeight() / 2f + (textH / 2f - fontMetrics.descent), numberPaint);
                break;
            case SHAPE_SQUARE:
                /**
                 * 正方形取宽高之间较小的值
                 */
                int sideLength = Math.min(getMeasuredHeight(),getMeasuredWidth());
                RectF squareF = new RectF(0,0,sideLength,sideLength);
                canvas.drawRect(squareF, backgroundPaint);
                canvas.drawText(showText, sideLength / 2f, sideLength / 2f + (textH / 2f - fontMetrics.descent), numberPaint);
                break;

        }
    }

    private int dip2px(Context context, int dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 设置形状
     * @param shape
     * @return
     */
    public BadgeView setShape(int shape){
        currentShape = shape;
        invalidate();
        return this;
    }

    /**
     * 设置小圆点的宽高
     * @param w
     * @param h
     * @return
     */
    public BadgeView setWidthAndHeight(int w,int h){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.width = dip2px(getContext(),w);
        params.height = dip2px(getContext(),h);
        setLayoutParams(params);
        return this;
    }

    /**
     * 设置宽
     * @param w
     * @return
     */
    public BadgeView setWidth(int w){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.width = w;
        setLayoutParams(params);
        return this;
    }

    /**
     * 设置高
     * @param h
     * @return
     */
    public BadgeView setHeight(int h){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.height = h;
        setLayoutParams(params);
        return this;
    }

    /**
     * 单位是dp
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public BadgeView setMargin(int left,int top,int right,int bottom){
        leftMargin = dip2px(getContext(),left);
        topMargin = dip2px(getContext(),top);
        rightMargin = dip2px(getContext(),right);
        bottomMargin = dip2px(getContext(),bottom);
        invalidate();
        return this;
    }

    /**
     * 单位是sp
     * @param sp
     * @return
     */
    public BadgeView setTextSize(int sp){
        defaultTextSize = sp2px(getContext(),sp);
        numberPaint.setTextSize(defaultTextSize);
        invalidate();
        return this;
    }

    /**
     * 单位是dp
     * @param horizontal
     * @param vertical
     * @return
     */
    public BadgeView setSpace(int horizontal,int vertical){
        horizontalSpace = dip2px(getContext(),horizontal);
        verticalSpace = dip2px(getContext(),vertical);
        invalidate();
        return this;
    }

    /**
     * 单位是dp
     * @param color
     * @return
     */
    public BadgeView setTextColor(int color){
        defaultTextColor = color;
        numberPaint.setColor(color);
        invalidate();
        return this;
    }

    /**
     * 设置颜色
     * @param color
     * @return
     */
    public BadgeView setBadgeBackground(int color){
        defaultBackgroundColor = color;
        backgroundPaint.setColor(color);
        invalidate();
        return this;
    }

    /**
     * 消息提示数目
     * @param count
     * @return
     */
    public BadgeView setTipsNum(int count){
        showText = String.valueOf(count);
        invalidate();
        return this;
    }

    /**
     * 获取当前数目
     * @return
     */
    public static String getTipsNum(){
        return showText;
    }

    /**
     * 设置圆点位置，注意必须先绑定要显示的view
     * @param gravity
     * @return
     */
    public BadgeView setBadgeGravity(int gravity){
        badgeGravity = gravity;
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
        return this;
    }


    public BadgeView bind(View view){
        if (getParent()!=null){
            ((ViewGroup)getParent()).removeView(this);
        }
        if (view==null){
            return this;
        }
        if (((view.getParent()) instanceof FrameLayout)&&hasBind==true){
            ((FrameLayout)view.getParent()).addView(this);
            return this;
        }else if (view.getParent() instanceof ViewGroup){

            ViewGroup parent = (ViewGroup) view.getParent();
            int viewIndex = parent.indexOfChild(view);
            parent.removeView(view);
            FrameLayout container = new FrameLayout(getContext());
            ViewGroup.LayoutParams containerParams  = view.getLayoutParams();
            int origionHeight = containerParams .height;
            int origionWidth = containerParams .width;
            FrameLayout.LayoutParams viewLayoutParams = new FrameLayout.LayoutParams(origionWidth,origionHeight);
            if (origionHeight == ViewGroup.LayoutParams.WRAP_CONTENT){
                containerParams .height = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewLayoutParams.topMargin = topMargin;
                viewLayoutParams.bottomMargin = bottomMargin;
            }else{
                containerParams .height = origionHeight+topMargin+bottomMargin+verticalSpace;
            }
            if (origionWidth == ViewGroup.LayoutParams.WRAP_CONTENT){
                containerParams .width = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewLayoutParams.leftMargin = leftMargin;
                viewLayoutParams.rightMargin = rightMargin;
            }else{
                containerParams .width = origionWidth+leftMargin+rightMargin+horizontalSpace;
            }
            container.setLayoutParams(containerParams );

            //setGravity
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
            if (params.gravity==(Gravity.RIGHT|Gravity.TOP)||params.gravity==Gravity.RIGHT||params.gravity==Gravity.TOP){
                view.setPadding(0,verticalSpace,horizontalSpace,0);
                viewLayoutParams.gravity = Gravity.LEFT|Gravity.BOTTOM;
            }else if(params.gravity==(Gravity.LEFT|Gravity.TOP)||params.gravity==Gravity.LEFT||params.gravity==Gravity.TOP){
                view.setPadding(horizontalSpace,verticalSpace,0,0);
                viewLayoutParams.gravity=Gravity.RIGHT|Gravity.BOTTOM;
            }else if(params.gravity==(Gravity.LEFT|Gravity.BOTTOM)){
                view.setPadding(horizontalSpace,0,0,verticalSpace);
                viewLayoutParams.gravity=Gravity.RIGHT|Gravity.TOP;
            }else if(params.gravity==(Gravity.RIGHT|Gravity.BOTTOM)){
                view.setPadding(0,0,horizontalSpace,verticalSpace);
                viewLayoutParams.gravity=Gravity.LEFT|Gravity.TOP;
            }else{
                view.setPadding(0,verticalSpace,horizontalSpace,0);
                viewLayoutParams.gravity=Gravity.LEFT|Gravity.BOTTOM;
            }

            view.setLayoutParams(viewLayoutParams);
            container.setId(view.getId());
            container.addView(view);
            container.addView(this);
            parent.addView(container,viewIndex);
            hasBind = true;
        }else if (view.getParent()==null){

            try {
                throw new Exception("View must have a parent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public boolean unbind(){
        if (getParent()!=null){
            ((ViewGroup)getParent()).removeView(this);
            return true;
        }
        return false;
    }

}
