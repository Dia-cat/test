package com.video.text.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "record", schema = "video", catalog = "")
public class RecordEntity {
    private int id;
    private String recordtime;
    private String recordscore;
    private String recordtype;
    private String phone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "recordtime")
    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    @Basic
    @Column(name = "recordscore")
    public String getRecordscore() {
        return recordscore;
    }

    public void setRecordscore(String recordscore) {
        this.recordscore = recordscore;
    }

    @Basic
    @Column(name = "recordtype")
    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordEntity that = (RecordEntity) o;
        return id == that.id &&
                Objects.equals(recordtime, that.recordtime) &&
                Objects.equals(recordscore, that.recordscore) &&
                Objects.equals(recordtype, that.recordtype) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, recordtime, recordscore, recordtype, phone);
    }
}
