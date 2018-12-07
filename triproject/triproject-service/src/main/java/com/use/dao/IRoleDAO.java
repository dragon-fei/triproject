package com.use.dao;

import com.use.util.dao.IBaseDAO;
import com.use.vo.Role;

import java.util.Set;

public interface IRoleDAO extends IBaseDAO<String, Role> {
    public Set<String> findAllIdByEmp(String eid);
}
