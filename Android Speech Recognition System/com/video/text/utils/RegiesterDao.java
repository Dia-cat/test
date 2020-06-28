package com.video.text.utils;

import com.video.text.bean.RegisterEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.List;

public class RegiesterDao {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;

    public RegiesterDao() {
        config = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    public void insert(String userName, String userPass, String phoneNum) {
        RegisterEntity entity = new RegisterEntity();
        entity.setPassword(userPass);
        entity.setUserName(userName);
        entity.setPhoneNum(phoneNum);
        session.save(entity);
        tx.commit();
        session.close();
    }

    public void update(int id, String userName, String pass) {
        RegisterEntity entity = (RegisterEntity) session.get(RegisterEntity.class, new Integer(id));
        if (!StringUtil.isNullOrEmpty(userName))
            entity.setUserName(userName);
        if (!StringUtil.isNullOrEmpty(pass))
            entity.setPassword(pass);
        session.update(entity);
        tx.commit();
    }

    public List queryByphone(String phoneNum) {
        String hql = " from  RegisterEntity E where E.phoneNum =" + phoneNum;
        Query query = session.createQuery(hql);
        List<RegisterEntity> results = query.list();
        return results;
    }

    public List queryByuserName(String userName) {
        String hql = " from  RegisterEntity entity where entity.userName ='" + userName + "'";
        Query query = session.createQuery(hql);
        List<RegisterEntity> results = query.list();
        return results;
    }
}
