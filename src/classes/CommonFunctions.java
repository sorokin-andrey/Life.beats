package classes;

import android.app.AlertDialog;
import android.content.*;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.common.base.Strings;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 11/30/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonFunctions {

    public static void startNewActivity(Context context, Class<?> cls) {
        try {
            Intent myIntent = new Intent(context, cls);
            Log.i(StaticVariables.TAG_START_NEW_ACTIVITY, cls.getName().toString());
            context.startActivity(myIntent);
        } catch (IndexOutOfBoundsException e) {
            CommonFunctions.showAlert(context, "error", e.getMessage());
        } catch (IllegalStateException e) {
            showAlert(context, "error", e.getMessage());
        } catch (ActivityNotFoundException e) {
            showAlert(context, "error", e.getMessage());
        } catch (Exception e) {
            showAlert(context, "error", e.getMessage());
        }
    }

    public static void startNewActivityWithClearingBackStack(Context context, Class<?> cls) {
        try {
            Intent myIntent = new Intent(context, cls);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.i(StaticVariables.TAG_START_NEW_ACTIVITY_WITH_CLEARING_BACK_STACK, cls.getName());
            context.startActivity(myIntent);
        } catch (IndexOutOfBoundsException e) {
            CommonFunctions.showAlert(context, "error", e.getMessage());
        } catch (IllegalStateException e) {
            showAlert(context, "error", e.getMessage());
        } catch (ActivityNotFoundException e) {
            showAlert(context, "error", e.getMessage());
        } catch (Exception e) {
            showAlert(context, "error", e.getMessage());
        }
    }

    /**
     * Take String text from EditText
     *
     * @return text from specified EditText or empty String if exception occurred
     */
    public static String getStringFromEditText(Context context, EditText editText) {
        try {
            if (editText != null && editText.getText() != null) {
                return editText.getText().toString();
            }
        } catch (Exception e) {
            showAlert(context, "error", e.getMessage());
        }
        return "";
    }

    /**
     * Check if String is null or empty or whitespace
     *
     * @return text from specified EditText or empty String if exception occurred
     */
    public static Boolean isStringNullOrEmptyOrWhiteSpace(Context context, String text) {
        Boolean result = false;
        try {
            if (Strings.isNullOrEmpty(text))
                result = true;
            else {
                result = false;
            }
        } catch (Exception e) {
            showAlert(context, "Exception in CommonFunctions.isStringNullOrEmptyOrWhiteSpace", e.getMessage());
        }
        return result;
    }

    /**
     * Take int text from String
     *
     * @return text from specified String or 0 if exception occurred
     */
    public static int getIntFromString(Context context, String text) {
        int result = 0;
        try {
            if (!isStringNullOrEmptyOrWhiteSpace(context, text)) {
                result = Integer.parseInt(text);
            }
        } catch (NumberFormatException e) {
            CommonFunctions.showAlert(context, "NumberFormatException in CommonFunctions.getIntFromString", e.getMessage());
        } catch (NullPointerException e) {
            CommonFunctions.showAlert(context, "NullPointerException in CommonFunctions.getIntFromString", e.getMessage());
        } catch (Exception e) {
            CommonFunctions.showAlert(context, "Exception in CommonFunctions.getIntFromString", e.getMessage());
        }
        return result;
    }

    /**
     * Take double text from String
     *
     * @return text from specified String or 0 if exception occurred
     */
    public static double getDoubleFromString(Context context, String text) {
        double result = 0;
        try {
            if (!isStringNullOrEmptyOrWhiteSpace(context, text)) {
                result = Double.parseDouble(text);
            }
        } catch (NumberFormatException e) {
            CommonFunctions.showAlert(context, "NumberFormatException in CommonFunctions.getIntFromString", e.getMessage());
        } catch (NullPointerException e) {
            CommonFunctions.showAlert(context, "NullPointerException in CommonFunctions.getIntFromString", e.getMessage());
        } catch (Exception e) {
            CommonFunctions.showAlert(context, "Exception in CommonFunctions.getIntFromString", e.getMessage());
        }
        return result;
    }

    /**
     * Shows popup with specified title anf message
     *
     * @return
     */
    public static void showAlert(Context context, String title, String message) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.show();
        } catch (IllegalStateException e) {
            showAlert(context, "error", e.getMessage());
        } catch (Exception e) {
            showAlert(context, "error", e.getMessage());
        }
    }

    /**
     * Check if the date is in the correct period
     *
     * @return true if correct, false if not
     */
    public static boolean isThisDateInCorrectPeriod(Calendar startDate, Calendar currentDate, Calendar endDate) {
        if (startDate.get(Calendar.YEAR) < currentDate.get(Calendar.YEAR) && endDate.get(Calendar.YEAR) > currentDate.get(Calendar.YEAR)) {
            if (startDate.get(Calendar.MONTH) < currentDate.get(Calendar.MONTH) && endDate.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)) {
                if (startDate.get(Calendar.DAY_OF_MONTH) < currentDate.get(Calendar.DAY_OF_MONTH) && endDate.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /**
     * Converting string with specified format to calendar
     *
     * @return calendar from string or current calendar if exceptions occurred
     */
    public static Calendar getCalendarFromString(Context context, String textDate, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        try {
            // Make sure user insert date in this format.
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            cal.setTime(formatter.parse(textDate));
        } catch (java.text.ParseException e) {
            showAlert(context, "ParseException in CommonFunctions.getCalendarFromString", e.getMessage());
        } catch (Exception e) {
            showAlert(context, "Exception in CommonFunctions.getCalendarFromString", e.getMessage());
        }
        return cal;
    }

    /**
     * Converting string with specified format to date
     *
     * @param context current context
     * @param text String represent Date
     * @param format format in which you want to take your Date
     *
     * @return Date from string or current date if exceptions occurred
     */
    public static Date getDateFromString(Context context, String text, String format) {
        Date date;
        try {
            // Make sure user insert date in this format.
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            date = formatter.parse(text);
        } catch (java.text.ParseException e) {
            date = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);
            Log.e("ConvertException", "ParseException in CommonFunctions.getDateFromString " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        } catch (Exception e) {
            date = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);
            Log.e("ConvertException", "Exception in CommonFunctions.getDateFromString " + String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Converting date to string with specified format
     *
     * @return formatted string or empty string if exceptions occurred
     */
    public static String getStringFromDate(Context context, Date date, String dateFormat) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            result = sdf.format(date);
        } catch (Exception e) {
            showAlert(context, "Exception in CommonFunctions.getStringFromDate", e.getMessage());
        }
        return result;
    }

    /**
     * Check if the database exist
     *
     * @return true if it exists, false if it doesn't
     */
    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        try {
            String filePath = imageFile.getAbsolutePath();
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Images.Media._ID},
                    MediaStore.Images.Media.DATA + "=? ",
                    new String[]{filePath}, null);
            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.MediaColumns._ID));
                Uri baseUri = Uri.parse("content://media/external/images/media");
                return Uri.withAppendedPath(baseUri, "" + id);
            } else {
                if (imageFile.exists()) {
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DATA, filePath);
                    return context.getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            showAlert(context, "Exception in CommonFunctions.getImageContentUri", e.getMessage());
        }
        return null;
    }

    /**
     * Gets the corresponding path to a file from the given content:// URI
     *
     * @param selectedVideoUri The content:// URI to find the file path from
     * @param contentResolver  The content resolver to use to perform the query.
     * @return the file path as a string
     */
    public static String getFilePathFromContentUri(Uri selectedVideoUri, ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static void setImageToImageView(Context context, ImageView imageView, String pathToFile) {
        try {
            File imgFile = new File(pathToFile);
            if (imgFile.exists()) {
                Uri selectedImage = getImageContentUri(context, imgFile);
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            showAlert(context, "Exception in CommonFunctions.setImageToImageViewFromFile", e.getMessage());
        }
    }

    /**
     * method is used for checking valid string format.
     *
     * @param context context
     * @param editText EditText object to take text from
     * @param regexExpression regex expression
     * @param errorMessage error message, to indicate user about validation error
     * @return boolean result of checking and shows popup with error message if something wrong
     */
    public static boolean checkIfTextIsValid(Context context, EditText editText, String regexExpression, String errorMessage) {
        boolean isEmailValid = false;
        try {
            String text = getStringFromEditText(context, editText);
            if (text.length() > 0) {
                Pattern objPattern = Pattern.compile(regexExpression, Pattern.CASE_INSENSITIVE);
                Matcher objMatcher = objPattern.matcher(text);
                if (!objMatcher.matches()) {
                    showAlert(context, "Something is wrong", errorMessage);
                } else {
                    isEmailValid = true;
                }
            }
        } catch (Exception e) {
            isEmailValid = false;
            showAlert(context, "Exception in CommonFunctions.checkIfTextIsValid", e.getMessage());
        }
        return isEmailValid;
    }

    /**
     * Method for converting Date object to String in specified format
     *
     * @param date date
     * @param timeFormat time format
     * @return String represented formatted date object or current time if error occurred
     */
    public static String getStringFromTime(Time time, String timeFormat) {
        String result = "";
        try {
            DateFormat df = new SimpleDateFormat(timeFormat);
            result = df.format(time);
        } catch (Exception e) {
            Log.e("ConvertException", "Exception in CommonFunctions.getStringFromTime " + String.valueOf(e.getMessage()));
            e.printStackTrace();
            DateFormat df = new SimpleDateFormat(timeFormat);
            result = df.format(new Date(System.currentTimeMillis()));
        }
        return result;
    }
}