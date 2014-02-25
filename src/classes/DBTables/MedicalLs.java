package classes.DBTables;

import android.content.Context;
import classes.CommonFunctions;
import classes.StaticVariables;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 04.02.14
 * Time: 15:11
 */
public class MedicalLs {

    //region parameters
    private int iid;
    private String regNumber;
    private String type;
    private String tradeName;
    private String typeView;
    private Date regDate;
    private int expireTerm;
    private Date expireDate;
    private String manufacturer;
    private String country;
    private String classificationLS;
    private String mnn;
    private String atxClassification;
    private String dosageConcentration;
    private String shelfLife;
    private String gmp;
    private String generic;
    private String recipe;
    private String ozhvls;
    private String npp;
    private String brandName;
    private String patentNumber;
    private String typeNd;
    private String ndNumber;
    //endregion

    //region getters and setters
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getTypeView() {
        return typeView;
    }

    public void setTypeView(String typeView) {
        this.typeView = typeView;
    }

    public Date getRegDate() {
        if (regDate != null)
            return regDate;
        else
            return new Date();
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setRegDate(Context context, String regDate) {
        this.regDate = CommonFunctions.getDateFromString(context, regDate, StaticVariables.dateFormat);
    }

    public int getExpireTerm() {
        return expireTerm;
    }

    public void setExpireTerm(int expireTerm) {
        this.expireTerm = expireTerm;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setExpireDate(Context context, String expireDate) {
        this.expireDate = CommonFunctions.getDateFromString(context, expireDate, StaticVariables.dateFormat);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClassificationLS() {
        return classificationLS;
    }

    public void setClassificationLS(String classificationLS) {
        this.classificationLS = classificationLS;
    }

    public String getMnn() {
        return mnn;
    }

    public void setMnn(String mnn) {
        this.mnn = mnn;
    }

    public String getAtxClassification() {
        return atxClassification;
    }

    public void setAtxClassification(String atxClassification) {
        this.atxClassification = atxClassification;
    }

    public String getDosageConcentration() {
        return dosageConcentration;
    }

    public void setDosageConcentration(String dosageConcentration) {
        this.dosageConcentration = dosageConcentration;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getGmp() {
        return gmp;
    }

    public void setGmp(String gmp) {
        this.gmp = gmp;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getOzhvls() {
        return ozhvls;
    }

    public void setOzhvls(String ozhvls) {
        this.ozhvls = ozhvls;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public String getTypeNd() {
        return typeNd;
    }

    public void setTypeNd(String typeNd) {
        this.typeNd = typeNd;
    }

    public String getNdNumber() {
        return ndNumber;
    }

    public void setNdNumber(String ndNumber) {
        this.ndNumber = ndNumber;
    }
    //endregion

    public MedicalLs() {
        this.iid = 0;
        this.regNumber = "";
        this.type = "";
        this.tradeName = "";
        this.typeView = "";
        this.regDate = new Date();
        this.expireTerm = 0;
        this.expireDate = new Date();
        this.manufacturer = "";
        this.classificationLS = "";
        this.country = "";
        this.mnn = "";
        this.atxClassification = "";
        this.dosageConcentration = "";
        this.gmp = "";
        this.shelfLife = "";
        this.generic = "";
        this.recipe = "";
        this.ozhvls = "";
        this.npp = "";
        this.brandName = "";
        this.patentNumber = "";
        this.typeNd = "";
        this.ndNumber = "";
    }

    public MedicalLs(int iid, String regNumber, String type, String tradeName, String typeView, Date regDate, int expireTerm, Date expireDate, String manufacturer, String classificationLS, String country, String mnn, String atxClassification, String dosageConcentration, String gmp, String shelfLife, String generic, String recipe, String ozhvls, String npp, String brandName, String patentNumber, String typeNd, String ndNumber) {
        this.iid = iid;
        this.regNumber = regNumber;
        this.type = type;
        this.tradeName = tradeName;
        this.typeView = typeView;
        this.regDate = regDate;
        this.expireTerm = expireTerm;
        this.expireDate = expireDate;
        this.manufacturer = manufacturer;
        this.classificationLS = classificationLS;
        this.country = country;
        this.mnn = mnn;
        this.atxClassification = atxClassification;
        this.dosageConcentration = dosageConcentration;
        this.gmp = gmp;
        this.shelfLife = shelfLife;
        this.generic = generic;
        this.recipe = recipe;
        this.ozhvls = ozhvls;
        this.npp = npp;
        this.brandName = brandName;
        this.patentNumber = patentNumber;
        this.typeNd = typeNd;
        this.ndNumber = ndNumber;
    }

    public MedicalLs(Context context, int iid, String regNumber, String type, String tradeName, String typeView, String regDate, int expireTerm, String expireDate, String manufacturer, String classificationLS, String country, String mnn, String atxClassification, String dosageConcentration, String gmp, String shelfLife, String generic, String recipe, String ozhvls, String npp, String brandName, String patentNumber, String typeNd, String ndNumber) {
        this.iid = iid;
        this.regNumber = regNumber;
        this.type = type;
        this.tradeName = tradeName;
        this.typeView = typeView;
        setRegDate(context, regDate);
        this.expireTerm = expireTerm;
        setExpireDate(context, expireDate);
        this.manufacturer = manufacturer;
        this.classificationLS = classificationLS;
        this.country = country;
        this.mnn = mnn;
        this.atxClassification = atxClassification;
        this.dosageConcentration = dosageConcentration;
        this.gmp = gmp;
        this.shelfLife = shelfLife;
        this.generic = generic;
        this.recipe = recipe;
        this.ozhvls = ozhvls;
        this.npp = npp;
        this.brandName = brandName;
        this.patentNumber = patentNumber;
        this.typeNd = typeNd;
        this.ndNumber = ndNumber;
    }
}