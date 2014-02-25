package classes.DBTables;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 18:39
 */
public class Recommendation {
    //region parameters
    private int id;
    private int userId;
    private Date creationDate;
    private int creationUserId;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }
    //endregion

    public Recommendation() {
        this.id = 1;
        this.userId = 1;
        this.creationDate = new Date();
        this.creationUserId = 1;
    }

    public Recommendation(int id, int userId, Date creationDate, int creationUserId) {
        this.id = id;
        this.userId = userId;
        this.creationDate = creationDate;
        this.creationUserId = creationUserId;
    }
}
