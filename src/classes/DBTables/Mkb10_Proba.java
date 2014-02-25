package classes.DBTables;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 04.02.14
 * Time: 16:57
 */
public class Mkb10_Proba {

    //region parameters
    private int id;
    private int parrent;
    private String code;
    private String name;
    //endregion

    //region getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParrent() {
        return parrent;
    }

    public void setParrent(int parrent) {
        this.parrent = parrent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public Mkb10_Proba(int id, int parrent, String code, String name) {
        this.id = id;
        this.parrent = parrent;
        this.code = code;
        this.name = name;
    }

    public Mkb10_Proba() {
        this.id = 0;
        this.parrent = 0;
        this.code = "";
        this.name = "";
    }
}
