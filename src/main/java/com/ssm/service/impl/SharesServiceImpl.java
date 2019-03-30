package com.ssm.service.impl;

import java.util.List;
import java.util.Map;
import com.ssm.dao.SharesDao;
import com.ssm.model.Shares;
import com.ssm.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssm.util.DateUtil;

/**
 * @author dell
 * @date 2019/03/30
 */
@Service
public class SharesServiceImpl implements SharesService {

	@Autowired
	private SharesDao sharesDao;
	
	@Override
	public List<Shares> findShares(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sharesDao.findShares(map);
	}

	@Override
	public Long getTotalShares(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sharesDao.getTotalShares(map);
	}

	
	@Override
	public int addShares(Shares shares) {
		// TODO Auto-generated method stub
		shares.setCreatetime(DateUtil.getCurrentDateStr());
		return sharesDao.addShares(shares);
	}

	@Override
	public int updateShares(Shares shares) {
		// TODO Auto-generated method stub
		shares.setUpdatetime(DateUtil.getCurrentDateStr());
		return sharesDao.updateShares(shares);
	}

	@Override
	public int deleteShares(Integer id) {
		// TODO Auto-generated method stub
		return sharesDao.deleteShares(id);
	}

	@Override
	public List<Shares> getSharesName() {
		// TODO Auto-generated method stub
		return sharesDao.getSharesName();
	}

}
