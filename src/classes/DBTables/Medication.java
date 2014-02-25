package classes.DBTables;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 17:32
 */
public class Medication {

    //region parameters
    private int id;
    private int userId;
    private int medicationId;
    private double dosage;
    private int dosageUnit;
    private double quantity;
    private int quantityUnit;
    private int periodicity;
    private int periodicityUnit; // 1 - день, 2 - неделя, 3 - месяц
    private Date startDate;
    private Date endDate;
    private int diseaseId;
    private int recommendationId;
    //endregion

    //region getters and setters
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

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
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

    public int getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }

    public int getPeriodicityUnit() {
        return periodicityUnit;
    }

    public void setPeriodicityUnit(int periodicityUnit) {
        this.periodicityUnit = periodicityUnit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }
    //endregion

    public Medication() {
        this.id = 0;
        this.userId = 0;
        this.medicationId = 0;
        this.dosage = 0;
        this.dosageUnit = 0;
        this.quantity = 0;
        this.quantityUnit = 0;
        this.periodicity = 0;
        this.periodicityUnit = 0;
        this.startDate = new Date();
        this.endDate = new Date();
        this.diseaseId = 0;
        this.recommendationId = 0;
    }

    public Medication(int id, int userId, int medicationId, double dosage, int dosageUnit, int quantity, int quantityUnit, int periodicity, int periodicityUnit, Date startDate, Date endDate, int diseaseId, int recommendationId) {
        this.id = id;
        this.userId = userId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.periodicity = periodicity;
        this.periodicityUnit = periodicityUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diseaseId = diseaseId;
        this.recommendationId = recommendationId;
    }
}
