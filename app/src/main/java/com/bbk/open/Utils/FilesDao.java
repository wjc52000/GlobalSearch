package com.bbk.open.Utils;


import android.content.Context;
import com.bbk.open.model.FileInfo;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by administration on 2016/7/15.
 * files DAO
 */
public class FilesDao {
    private Context context;
    private Dao<FileInfo, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public FilesDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(FileInfo.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     * @param file
     */
    public void add(FileInfo file)
    {
        try
        {
            userDaoOpe.create(file);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * TODO 查询操作
     */
    public List<FileInfo> qurry(String key) throws SQLException {
        List<FileInfo> files=new ArrayList<FileInfo>();
        QueryBuilder builder = userDaoOpe.queryBuilder();
        builder .where().like("name","%"+key+"%").or().like("searchInfo","%"+key+"%");
        files=builder.query();
        return files;
    }

    public void delete() throws SQLException {
        userDaoOpe.executeRaw("delete from tb_infos");
        userDaoOpe.executeRaw("update sqlite_sequence set seq = 0 WHERE name = 'tb_infos'");
    }

}
