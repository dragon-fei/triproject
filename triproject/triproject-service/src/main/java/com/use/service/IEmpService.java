package com.use.service;

import java.util.Map;
import java.util.Set;

public interface IEmpService {
    public Map<String,Object> get(String eid,String password);
    public Map<String, Set<String>> listRoleAndAction(String eid) ;
}
