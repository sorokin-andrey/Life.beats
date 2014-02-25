package com.quickblox.lifebeats.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import classes.Alarm;
import classes.CommonFunctions;
import classes.DBHandlers.DosageUnit_DBHandler;
import classes.DBHandlers.MedicalLs_DBHandler;
import classes.DBHandlers.MyMedications_DBHandler;
import classes.DBHandlers.QuantityUnit_DBHandler;
import classes.DBTables.MyMedications;
import classes.StaticVariables;
import com.quickblox.lifebeats.R;
import com.quickblox.lifebeats.ui.old.Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 02.11.13
 * Time: 17:23
 */
public class Medications extends FragmentActivity {

    Spinner spinnerUnit;
    Spinner spinnerTradeName;
    Spinner spinnerDosage;
    EditText etQuantity;
    Alarm alarm = new Alarm();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medications);

        ConstructUI();
    }

    private void ConstructUI() {
        spinnerUnit = (Spinner) findViewById(R.id.medications_Spinner_quantity);
        spinnerTradeName = (Spinner) findViewById(R.id.medications_Spinner_tradeName);
        spinnerDosage = (Spinner) findViewById(R.id.medications_Spinner_dosage);
        etQuantity = (EditText) findViewById(R.id.medications_EditText_quantity);

        prepare_unit();
        prepare_tradeName();
        prepare_dosage();
    }

    private void prepare_unit() {
        QuantityUnit_DBHandler qubh = new QuantityUnit_DBHandler(this);
        List<String> list = qubh.getAllTitles();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(dataAdapter);
    }

    private void prepare_tradeName() {
        MedicalLs_DBHandler mdbh = new MedicalLs_DBHandler(this);
        List<String> list = mdbh.getAllItemsTradeNameList();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTradeName.setAdapter(dataAdapter);
    }

    private void prepare_dosage() {
        DosageUnit_DBHandler mdbh = new DosageUnit_DBHandler(this);
        List<String> list = mdbh.getAllItemsTradeNameList();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDosage.setAdapter(dataAdapter);
    }

    public void startMedicationPeriodicityActivity(View v) {
        startActivity(new Intent(this, Medications_Periodicity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void createNewMedication_onClick(View v) {
        if (StaticVariables.medicationList.size() > 0) {
            String tradeName = spinnerTradeName.getSelectedItem().toString();
            double quantity = CommonFunctions.getDoubleFromString(this, CommonFunctions.getStringFromEditText(this, etQuantity));
            String quantityUnitName = spinnerUnit.getSelectedItem().toString();
            String dosageUnitName = spinnerDosage.getSelectedItem().toString();

            int medicationId = new MedicalLs_DBHandler(this).getItem(tradeName).getIid();
            int quantityUnitId = new QuantityUnit_DBHandler(this).getItem(quantityUnitName).getId();
            int dosageUnitId = new DosageUnit_DBHandler(this).getItem(dosageUnitName).getId();

            MyMedications_DBHandler mmdbh = new MyMedications_DBHandler(this);

            for (MyMedications item : StaticVariables.medicationList) {
                item.setDoctorId(1);
                item.setUserId(StaticVariables.currentUser.getId());
                item.setQuantity(quantity);
                item.setDosage(quantity);
                item.setMedicationId(medicationId);
                item.setQuantityUnit(quantityUnitId);
                item.setDosageUnit(dosageUnitId);
                mmdbh.addItem(item);
            }

            StaticVariables.selectedDateList = new ArrayList<Calendar>();

            List<MyMedications> list = mmdbh.getAllItems();
            StaticVariables.medicationList = new ArrayList<MyMedications>();
            String s = "";
            CommonFunctions.showAlert(this, "Success", "Medications successfully added");
            CommonFunctions.startNewActivityWithClearingBackStack(this, NewMainPage.class);
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    //        Calendar dtStart = Calendar.getInstance();
    //        dtStart.set(dtStart.get(Calendar.YEAR),dtStart.get(Calendar.MONTH),dtStart.get(Calendar.DAY_OF_MONTH),dtStart.get(Calendar.HOUR_OF_DAY),dtStart.get(Calendar.MINUTE) + 1);
    //        Calendar dtEnd = Calendar.getInstance();
    //        dtEnd.set(dtStart.get(Calendar.YEAR),dtStart.get(Calendar.MONTH),dtStart.get(Calendar.DAY_OF_MONTH),dtStart.get(Calendar.HOUR_OF_DAY),dtStart.get(Calendar.MINUTE) + 2);
    //
    //        CalendarHelper.addNewCalendarEvent(this, dtStart, dtEnd, "test event", "generated automatically", 1);
    //        alarm.SetAlarm(this, dtEnd.getTimeInMillis());
        } else {
            CommonFunctions.showAlert(this, "Error", "You need to choose periodicity first");
        }
    }

    public void cancel_onClick(View v) {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        StaticVariables.medicationList = new ArrayList<MyMedications>();
        CommonFunctions.startNewActivityWithClearingBackStack(this, Main.class);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    @Override
    public void onBackPressed() {
        Log.d(StaticVariables.TAG_RETURN_TO_PREVIOUS_ACTIVITY, this.getLocalClassName());
        StaticVariables.medicationList = new ArrayList<MyMedications>();
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }
}