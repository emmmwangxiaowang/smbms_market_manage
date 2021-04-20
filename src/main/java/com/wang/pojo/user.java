package com.wang.pojo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/12 0012
 */
public class user {
    private Integer id;
    private String userCode;
    private String  userName;
    private String userPassword;
    private Integer gender;
    private java.util.Date birthday;
    private String phone;
    private String address;
    private Integer userRole;
    private Integer createBy;
    private Timestamp creationDate;
    private Integer modifyBy;
    private Timestamp modifyDate;

    private Integer age;//年龄
    private String userRoleName;//用户角色名

    public user() {
    }

    public user(Integer id, String userCode, String userName, String userPassword, Integer gender, Timestamp birthday, String phone, String address, Integer userRole, Integer createBy, Timestamp creationDate, Integer modifyBy, Timestamp modifyDate) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createBy = createBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUserRoleName(){ return userRoleName; }

    public void setUserRoleName(String userRoleName){ this.userRoleName=userRoleName; }

    public Integer getAge(){
        java.util.Date date= new java.util.Date();
        Integer age= date.getYear()-birthday.getYear();
        return age;
    }
    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword=" + userPassword +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", userRole=" + userRole +
                ", createBy=" + createBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
