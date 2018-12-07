package com.use.dao;

import com.use.util.dao.IBaseDAO;
import com.use.vo.Action;

import java.util.Set;

public interface IActionDAO extends IBaseDAO<String, Action> {
    public Set<String> findAllIdByEmp(String eid);
}
