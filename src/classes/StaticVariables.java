package classes;

import classes.DBTables.Medication;
import classes.DBTables.MyMedications;
import classes.DBTables.User;
import com.quickblox.lifebeats.ui.Periodicity_Interval;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 11/30/13
 * Time: 4:50 PM
 */
public class StaticVariables {
    public static ArrayList<Calendar> selectedDateList = new ArrayList<Calendar>();
    public static ArrayList<Periodicity_Interval> selectedTimeList = new ArrayList<Periodicity_Interval>();

    public static String dateFormat = "yyyy-MM-dd";
    public static String dateTimeFormat = "yyyy-MM-dd hh:mm:ss";
    public static String timeFormat = "hh:mm:ss";
    public static String databaseName = "lifebeats";
    public static Calendar startDate = Calendar.getInstance();
    public static Calendar endDate = Calendar.getInstance();

    public static List<MyMedications> medicationList = new ArrayList<MyMedications>();

    //public static List<Integer> calendarMonthDates = new ArrayList<Integer>();
    public static User currentUser;

    public static int periodicityType = 0;

    public static String TAG_START_NEW_ACTIVITY = "startNewActivity";
    public static String TAG_START_NEW_ACTIVITY_WITH_CLEARING_BACK_STACK = "startNewActivityWithClearingBackStack";
    public static String TAG_RETURN_TO_PREVIOUS_ACTIVITY = "returnToPreviousActivity";
}
