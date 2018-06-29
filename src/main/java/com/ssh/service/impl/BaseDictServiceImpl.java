package com.ssh.service.impl;

import com.ssh.dao.BaseDictDao;
import com.ssh.domain.BaseDict;
import com.ssh.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(com.ssh.dao.impl.BaseDictDaoImpl baseDictDao) {
    }

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return baseDictDao.findByTypeCode(dict_type_code);
    }
}
