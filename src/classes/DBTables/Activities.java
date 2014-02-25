package classes.DBTables;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 16:08
 */
public class Activities {

    //region parameters
    private int id;
    private int userId;
    private String description;
    private int quantity;
    private int quantityUnit;
    private Date startDate;
    private Date endDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(int quantityUnit) {
        this.quantityUnit = quantityUnit;
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

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }
    //endregion


    public Activities() {
        this.id = 0;
        this.userId = 0;
        this.description = "some description";
        this.quantity = 0;
        this.quantityUnit = 1;
        this.startDate = new Date();
        this.endDate = new Date();
        this.recommendationId = 1;
    }

    public Activities(int id, int userId, String description, int quantity, int quantityUnit, Date startDate, Date endDate, int recommendationId) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recommendationId = recommendationId;
    }
}
