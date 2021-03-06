package com.location.voicetest.db.green;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.location.voicetest.dbbean.TestInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TEST_INFO".
*/
public class TestInfoDao extends AbstractDao<TestInfo, Long> {

    public static final String TABLENAME = "TEST_INFO";

    /**
     * Properties of entity TestInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Count = new Property(2, int.class, "count", false, "COUNT");
        public final static Property Beizhu1 = new Property(3, String.class, "beizhu1", false, "BEIZHU1");
        public final static Property Beizhu2 = new Property(4, String.class, "beizhu2", false, "BEIZHU2");
        public final static Property Beizhu3 = new Property(5, String.class, "beizhu3", false, "BEIZHU3");
    }


    public TestInfoDao(DaoConfig config) {
        super(config);
    }
    
    public TestInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TEST_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERNAME\" TEXT," + // 1: username
                "\"COUNT\" INTEGER NOT NULL ," + // 2: count
                "\"BEIZHU1\" TEXT," + // 3: beizhu1
                "\"BEIZHU2\" TEXT," + // 4: beizhu2
                "\"BEIZHU3\" TEXT);"); // 5: beizhu3
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TEST_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TestInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
        stmt.bindLong(3, entity.getCount());
 
        String beizhu1 = entity.getBeizhu1();
        if (beizhu1 != null) {
            stmt.bindString(4, beizhu1);
        }
 
        String beizhu2 = entity.getBeizhu2();
        if (beizhu2 != null) {
            stmt.bindString(5, beizhu2);
        }
 
        String beizhu3 = entity.getBeizhu3();
        if (beizhu3 != null) {
            stmt.bindString(6, beizhu3);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TestInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
        stmt.bindLong(3, entity.getCount());
 
        String beizhu1 = entity.getBeizhu1();
        if (beizhu1 != null) {
            stmt.bindString(4, beizhu1);
        }
 
        String beizhu2 = entity.getBeizhu2();
        if (beizhu2 != null) {
            stmt.bindString(5, beizhu2);
        }
 
        String beizhu3 = entity.getBeizhu3();
        if (beizhu3 != null) {
            stmt.bindString(6, beizhu3);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TestInfo readEntity(Cursor cursor, int offset) {
        TestInfo entity = new TestInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.getInt(offset + 2), // count
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // beizhu1
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // beizhu2
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // beizhu3
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TestInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCount(cursor.getInt(offset + 2));
        entity.setBeizhu1(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBeizhu2(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setBeizhu3(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TestInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TestInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TestInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
