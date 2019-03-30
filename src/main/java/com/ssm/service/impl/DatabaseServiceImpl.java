package com.ssm.service.impl;

import com.ssm.dao.DatabaseDao;
import com.ssm.model.Database;
import com.ssm.service.DatabaseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据库管理层的service实现类
 * @author dell
 * @date 2019/03/30
 *
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {
	
	@Autowired
	private DatabaseDao databaseDao;
	
	@Override
	public List<Database> findDataBack(Map<String, Object> map) {
		return databaseDao.findDataBack(map);
	}
	
	@Override
	public Long getDataBackTotal(Map<String, Object> map) {
		return databaseDao.getDataBackTotal(map);
	}

	@Override
	public int addDatabase(Database database) {
		return databaseDao.addDatabase(database);
	}
	
	@Override
	public int deleteDatabase(Integer id) {
		return databaseDao.deleteDatabase(id);
	}
	
	@Override
	public int truncateTable(@Param("tablename") String tablename){
		return databaseDao.truncateTable(tablename);
	}
	
	@Override
	public int deleteOrderdata(@Param("tablename") String tablename,@Param("startid") Integer startid,@Param("endid") Integer endid){
		return databaseDao.deleteOrderdata(tablename,startid,endid);
	}
}
