package classes;

import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 14.02.14
 * Time: 9:23
 */
public class CalendarHelper {
    public static void addNewCalendarEvent(Context context, Calendar dtStart, Calendar dtEnd, String title, String description, int hasAlarm) {
        try {
            Calendar cal = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", dtStart.getTimeInMillis());
            intent.putExtra("allDay", false);
            intent.putExtra("hasAlarm", hasAlarm);
            intent.putExtra("rrule", "FREQ=DAILY;COUNT=1");
            intent.putExtra("endTime", dtEnd.getTimeInMillis());
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            context.startActivity(intent);
        } catch (Exception e) {
            CommonFunctions.showAlert(context, "Exception in CalendarHelper.addNewCalendarEvent", e.getLocalizedMessage());
        }
    }
}
