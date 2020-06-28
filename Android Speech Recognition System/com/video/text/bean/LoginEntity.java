package com.video.text.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login", schema = "video", catalog = "")
public class LoginEntity {
    private int id;
    private String islogin;
    private String userName;
    private String phoneNum;
    private String testRecord;
    private String collectionRecord;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "islogin")
    public String getIslogin() {
        return islogin;
    }

    public void setIslogin(String islogin) {
        this.islogin = islogin;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "phoneNum")
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "testRecord")
    public String getTestRecord() {
        return testRecord;
    }

    public void setTestRecord(String testRecord) {
        this.testRecord = testRecord;
    }

    @Basic
    @Column(name = "collectionRecord")
    public String getCollectionRecord() {
        return collectionRecord;
    }

    public void setCollectionRecord(String collectionRecord) {
        this.collectionRecord = collectionRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginEntity that = (LoginEntity) o;
        return id == that.id &&
                Objects.equals(islogin, that.islogin) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(phoneNum, that.phoneNum) &&
                Objects.equals(testRecord, that.testRecord) &&
                Objects.equals(collectionRecord, that.collectionRecord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, islogin, userName, phoneNum, testRecord, collectionRecord);
    }
}
