package com.video.text.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "register", schema = "video", catalog = "")
public class RegisterEntity {
    private int id;
    private String userName;
    private String phoneNum;
    private String password;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterEntity that = (RegisterEntity) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(phoneNum, that.phoneNum) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, phoneNum, password);
    }
}
