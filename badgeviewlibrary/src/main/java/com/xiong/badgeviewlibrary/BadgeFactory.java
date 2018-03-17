package com.xiong.badgeviewlibrary;

import android.content.Context;
import android.view.Gravity;

/**
 * Created by xjf on 2018/3/17.
 */

public class BadgeFactory {

    public static BadgeView createDot(Context context){
       return new BadgeView(context).
                setWidthAndHeight(10,10)
                .setTextSize(10).
                setBadgeGravity(Gravity.LEFT|Gravity.RIGHT)
                .setShape(BadgeView.SHAPE_CIRCLE);
    }

    public static BadgeView createCircle(Context context){
        return  new BadgeView(context)
                .setWidthAndHeight(20,20)
                .setTextSize(12)
                .setBadgeGravity(Gravity.RIGHT| Gravity.TOP)
                .setShape(BadgeView.SHAPE_CIRCLE);
    }
    public static BadgeView createRectangle(Context context){
        return  new BadgeView(context)
                .setWidthAndHeight(25,20)
                .setTextSize(12)
                .setBadgeGravity(Gravity.RIGHT| Gravity.TOP)
                .setShape(BadgeView.SHAPE_RETANGLE);
    }
    public static BadgeView createOval(Context context){
        return  new BadgeView(context)
                .setWidthAndHeight(25,20)
                .setTextSize(12)
                .setBadgeGravity(Gravity.RIGHT| Gravity.TOP)
                .setShape(BadgeView.SHAPE_OVAL);
    }
    public static BadgeView createSquare(Context context){
        return  new BadgeView(context)
                .setWidthAndHeight(20,20)
                .setTextSize(12)
                .setBadgeGravity(Gravity.RIGHT| Gravity.TOP)
                .setShape(BadgeView.SHAPE_SQUARE);
    }
    public static BadgeView createRoundRect(Context context){
        return  new BadgeView(context)
                .setWidthAndHeight(25,20)
                .setTextSize(12)
                .setBadgeGravity(Gravity.RIGHT| Gravity.TOP)
                .setShape(BadgeView.SHAPE_ROUND_RECTANGLE);
    }
    public static BadgeView create(Context context){
        return  new BadgeView(context);
    }
}
