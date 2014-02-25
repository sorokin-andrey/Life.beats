package com.quickblox.lifebeats.ui;

import android.content.Context;
import android.util.Log;
import classes.CommonFunctions;

public class Periodicity_Interval {
    public int itemId;
    public String itemName;
    public Boolean isChecked;

    public Periodicity_Interval(int itemIdIn, String timeIn, Boolean isCheckedIn) {
        itemId = itemIdIn;
        itemName = timeIn;
        isChecked = isCheckedIn;
    }

    public int getHourPart(Context context) {
        int result = 0;
        try {
            String text = itemName.substring(0, itemName.indexOf(":"));
            result = CommonFunctions.getIntFromString(context, text);
        } catch (Exception e) {
            Log.e("ConvertException", "Exception in Periodicity_Interval.getHourPart " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
        return result;
    }

    public int getMinutePart(Context context) {
        int result = 0;
        try {
            String text = itemName.substring(itemName.indexOf(":") + 1, itemName.length());
            result = CommonFunctions.getIntFromString(context, text);
        } catch (Exception e) {
            Log.e("ConvertException", "Exception in Periodicity_Interval.getMinutePart " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
        return result;
    }
}
