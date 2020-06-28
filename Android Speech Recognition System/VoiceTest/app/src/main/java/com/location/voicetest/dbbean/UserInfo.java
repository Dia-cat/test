package com.location.voicetest.dbbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String pwd;
    /** 1管理员 2普通员工 */
    private String quanxian;
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
    public String getQuanxian() {
        return this.quanxian;
    }
    public void setQuanxian(String quanxian) {
        this.quanxian = quanxian;
    }
    public String getPwd() {
        return this.pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1612457121)
    public UserInfo(Long id, String name, String pwd, String quanxian,
            String beizhu1, String beizhu2, String beizhu3) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.quanxian = quanxian;
        this.beizhu1 = beizhu1;
        this.beizhu2 = beizhu2;
        this.beizhu3 = beizhu3;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
}
