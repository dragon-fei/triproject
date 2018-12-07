package com.use.util.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDAO<K,V> {
    //执行数据的增加操作
    public boolean doCreate(V vo);
    //执行数据的修改操作
    public boolean doUpdate(V vo);
    //执行数据的删除操作
    public boolean doRemove(K id);
    //执行数据的批量删除操作
    public boolean doRemoveBatch(K[] ids);
    //根据ID进行数据查询操作
    public V findById(K id);
    //查询全部数据信息
    public List<V> findAll();
    //进行数据的分页操作
    public List<V> findAllSplitNoKeyWord(Integer currentPage,Integer lineSize);
    //数据模糊的分页操作
    public List<V> findAllSplit(Map<String,Object> param);
    //进行全部数据统计个数的查询
    public Long getAllCountNoKeyWord();
    //统计模糊查询的数据量
    public Long getAllCount(Map<String,Object> param);

}
