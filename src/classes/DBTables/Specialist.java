package classes.DBTables;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 05.02.14
 * Time: 18:56
 */
public class Specialist {
    //region parameters
    private int id;
    private String name;
    private String surname;
    private String specialistName;
    private String address;
    private String email;
    private String phone;
    private int medicalCenter;
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

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
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

    public int getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(int medicalCenter) {
        this.medicalCenter = medicalCenter;
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

    public Specialist() {
        this.id = 0;
        this.name = "";
        this.surname = "";
        this.specialistName = "";
        this.address = "";
        this.email = "";
        this.phone = "";
        this.medicalCenter = 0;
        this.password = "";
        this.salt = "";
        this.dateCreated = new Date();
        this.profileImage = "";
    }

    public Specialist(int id, String name, String surname, String specialistName, String address, String email, String phone, int medicalCenter, String password, String salt, Date dateCreated, String profileImage) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialistName = specialistName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.medicalCenter = medicalCenter;
        this.password = password;
        this.salt = salt;
        this.dateCreated = dateCreated;
        this.profileImage = profileImage;
    }
}
