package com.lin.heart_master.HeartView;

/**
 * 工具类
 * <p>
 * Created by wangchenlong on 16/1/5.
 */
public class Utils {
//    if (mCurrentProgress < 0.3f) {
//        mCurrentRadius1 = (float) Utils.mapValueFromRangeToRange(mCurrentProgress, 0.0f, 0.3f, 0, mMaxOuterDotsRadius * 0.8f);
//    } else {
//        mCurrentRadius1 = (float) Utils.mapValueFromRangeToRange(mCurrentProgress, 0.3f, 1f, 0.8f * mMaxOuterDotsRadius, mMaxOuterDotsRadius);
//    }                                             mCurrentProgress,   0.0f,              0.3f,                0,        mMaxOuterDotsRadius * 0.8f
//                                              mCurrentProgress,          0.3f,             1f,        0.8f * mMaxOuterDotsRadius, mMaxOuterDotsRadius
//                                              mCurrentProgress,         0.7f,              1f,             mMaxDotSize,             0
    public static double mapValueFromRangeToRange(  double value,    double fromLow,  double fromHigh,    double toLow,         double toHigh) {
        return toLow + ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));
    }

    // 中间值, value<low, 返回low, value>high, 返回high.


    public static double clamp(double value, double low, double high) {
        return Math.min(Math.max(value, low), high);
    }
}
