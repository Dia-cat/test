package com.location.voicetest.dbbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class TestInfo {
    @Id(autoincrement = true)
    private Long id;
    private String username;
    private int count;
    private String beizhu1;
    private String beizhu2;
    private String beizhu3;
    public String getBeizhu3() {
        return this.beizhu3;
    }
    public void setBeizhu3(String beizhu3) {
        this.beizhu3 = beizhu3;
    }
    public String getBeizhu2() {
        return this.beizhu2;
    }
    public void setBeizhu2(String beizhu2) {
        this.beizhu2 = beizhu2;
    }
    public String getBeizhu1() {
        return this.beizhu1;
    }
    public void setBeizhu1(String beizhu1) {
        this.beizhu1 = beizhu1;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1512847169)
    public TestInfo(Long id, String username, int count, String beizhu1,
            String beizhu2, String beizhu3) {
        this.id = id;
        this.username = username;
        this.count = count;
        this.beizhu1 = beizhu1;
        this.beizhu2 = beizhu2;
        this.beizhu3 = beizhu3;
    }
    @Generated(hash = 1499832600)
    public TestInfo() {
    }
}
