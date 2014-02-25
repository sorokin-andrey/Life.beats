package classes.DBTables;

import android.content.Context;
import classes.CommonFunctions;
import classes.StaticVariables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 17.02.14
 * Time: 12:00
 */
public class MyMedications {
    //region parameters
    private int id;
    private int userId;
    private int doctorId;
    private int medicationId;
    private Date eventDate;
    private double dosage;
    private int dosageUnit;
    private double quantity;
    private int quantityUnit;
    private int diseaseId;
    private int recommendationId;
    //endregion

    //region getters and setters
    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventTime(Context context) {
        return CommonFunctions.getStringFromDate(context, eventDate, StaticVariables.dateTimeFormat);
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public int getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(int dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(int quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }
    //endregion

    public MyMedications() {
        this.id = 0;
        this.userId = 0;
        this.doctorId = 0;
        this.medicationId = 0;
        this.eventDate = new Date();
        this.dosage = 0;
        this.dosageUnit = 1;
        this.quantity = 0;
        this.quantityUnit = 1;
        this.diseaseId = 0;
        this.recommendationId = 0;
    }

    public MyMedications(int id, int userId, int doctorId, int medicationId, Date eventDate, double dosage, int dosageUnit, double quantity, int quantityUnit, int diseaseId, int recommendationId) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.medicationId = medicationId;
        this.eventDate = eventDate;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.diseaseId = diseaseId;
        this.recommendationId = recommendationId;
    }
}
