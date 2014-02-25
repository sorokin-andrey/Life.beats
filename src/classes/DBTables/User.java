package classes.DBTables;

import android.content.Context;
import classes.CommonFunctions;
import classes.DBHandlers.User_DBHandler;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 19:26
 */
public class User {
    //region parameters
    private int id;
    private String name;
    private String surname;
    private Date birthDate;
    private String sex;
    private int weight;
    private int height;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String salt;
    private Date dateCreated;
    private String profileImage;
    //endregion

    //region getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        if (sex.equals(""))
            return "1";
        return sex;
    }

    public String getGender() {
        String result = "";
        if (sex == "0") {
            result = "Female";
        } else {
            result = "Male";
        }
        return result;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGender(String gender) {
        String result = "";
        if (gender.equals("Female")) {
            result = "0";
        } else {
            result = "1";
        }
        this.sex = result;
    }

    public int getWeight() {
        return weight;
    }

    public String getWeightAsString() {
        return String.valueOf(weight);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setWeight(Context context, String weight) {
        this.weight = CommonFunctions.getIntFromString(context, weight);
    }

    public int getHeight() {
        return height;
    }

    public String getHeightAsString() {
        return String.valueOf(getHeight());
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHeight(Context context, String height) {
        int a = CommonFunctions.getIntFromString(context, height);
        this.height = a;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    //endregion

    public User(Context context) {
        User_DBHandler userDBHandler = new User_DBHandler(context);
        this.id = userDBHandler.getItemsCount() + 1;
        this.name = "";
        this.surname = "";
        this.birthDate = new Date();
        this.sex = "";
        this.weight = 0;
        this.height = 0;
        this.address = "";
        this.email = "";
        this.phone = "";
        this.password = "";
        this.salt = "";
        this.dateCreated = new Date();
        this.profileImage = "";
    }

    public User(int id, String name, String surname, Date birthDate, String sex, int weight, int height, String address, String email, String phone, String password, String salt, Date dateCreated, String profileImage) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.dateCreated = dateCreated;
        this.profileImage = profileImage;
    }
}
