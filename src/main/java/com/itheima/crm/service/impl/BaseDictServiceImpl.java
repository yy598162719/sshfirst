package com.itheima.crm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;

@Transactional
@Service
public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByDictTypeCode(String dict_type_code) {
		return baseDictDao.findByDictTypeCode(dict_type_code);
	}
}
