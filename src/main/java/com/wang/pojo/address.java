package com.wang.pojo;

import java.sql.Date;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/12 0012
 */
public class address {
    private int id;
    private String contact;
    private String addressDesc;
    private int postCode;
    private int tel;
    private int createBy;
    private Date creationDate;

    public address() {
    }

    public address(int id, String contact, String addressDesc, int postCode, int tel, int createBy, Date creationDate) {
        this.id = id;
        this.contact = contact;
        this.addressDesc = addressDesc;
        this.postCode = postCode;
        this.tel = tel;
        this.createBy = createBy;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String  toString() {
        return "address{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", addressDesc='" + addressDesc + '\'' +
                ", postCode=" + postCode +
                ", tel=" + tel +
                ", createBy=" + createBy +
                ", creationDate=" + creationDate +
                '}';
    }
}
