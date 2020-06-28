package com.video.text.utils;

import com.video.text.bean.RecordEntity;
import com.video.text.bean.RegisterEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.List;

public class RecordDao {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;

    public RecordDao() {
        config = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    public void insert(String type, String time, String score, String phone) {
        RecordEntity entity = new RecordEntity();
        entity.setRecordtype(type);
        entity.setRecordscore(score);
        entity.setRecordtime(time);
        entity.setPhone(phone);
        session.save(entity);
        tx.commit();
        session.close();
    }

    public List queryByphone(String phoneNum) {
        String hql = " from  RecordEntity E where E.phone =" + phoneNum;
        Query query = session.createQuery(hql);
        List<RecordEntity> results = query.list();
        session.close();
        return results;
    }
}
