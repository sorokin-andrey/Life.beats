package classes.DBTables;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 16:54
 */
public class DosageUnit {

    //region parameters
    private int id;
    private String unitName;
    //endregion

    //region getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    //endregion


    public DosageUnit() {
        this.id = 0;
        this.unitName = "";
    }

    public DosageUnit(int id, String unitName) {
        this.id = id;
        this.unitName = unitName;
    }
}
