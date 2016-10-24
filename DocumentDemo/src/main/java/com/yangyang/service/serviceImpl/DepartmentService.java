package com.yangyang.service.serviceImpl;

import com.yangyang.dao.idao.IDepartmentDao;
import com.yangyang.model.DepScope;
import com.yangyang.model.Department;
import com.yangyang.service.iservice.IDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Resource
    private IDepartmentDao departmentDao;

    @Override
    public void add(Department dep) {
        departmentDao.add(dep);
    }

    @Override
    public void update(Department dep) {
        departmentDao.update(dep);
    }

    @Override
    public void delete(int id) {
        //1: 判断部门的用户,有->跑出异常
        //2: 将部门之间的关联信息删除
        String hql = "delete DepScope ds where ds.id=? or ds.scopeDep.id=? ";
        departmentDao.executeByHql(hql,new Object[]{id,id});

        //3: 删除部门
        departmentDao.delete(id);
    }

    @Override
    public Department load(int id) {
        return departmentDao.load(id);
    }

    @Override
    public List<Department> listAllDeps() {
        return departmentDao.list("from Department");
    }

    @Override
    public void addScopeDep(int depId, int sDepId) {
        String hql = "select ds from DepScope ds where ds.depId=? and ds.scopeDep.id=?";
        DepScope dep = (DepScope) this.departmentDao.queryForHql(hql,new Object[]{depId,sDepId});
        if(dep != null) return; //如果存在则直接返回
        dep = new DepScope();
        dep.setDepId(depId);
        dep.setScopeDep(departmentDao.load(sDepId));
        departmentDao.addObj(dep);
    }

    private List<Integer> getCross(List<Integer> eids,List<Integer> sdeps){
        List<Integer> result = new ArrayList<>();
        result.addAll(eids);
        result.retainAll(sdeps);
        return result;
    }
    private List<Integer> getDelIds(List<Integer> eids,List<Integer> sdeps){
        List<Integer> result = new ArrayList<>();
        result.addAll(eids);
        result.removeAll(getCross(eids, sdeps));
        return result;
    }
    private List<Integer> getAddIds(List<Integer> eids,List<Integer> sdeps){
        List<Integer> result = new ArrayList<>();
        result.addAll(sdeps);
        result.removeAll(getCross(eids, sdeps));
        return result;
    }
    @Override
    public void addScopeDeps(int depId, int[] sDepId) {
        List<Integer> sdeps = CollectionUtils.arrayToList(sDepId);
        List<Integer> eids = departmentDao.listAllExistIds(depId);

        List<Integer> delIds = getDelIds(eids,sdeps);
        List<Integer> addIds = getAddIds(eids,sdeps);

        //删除 id
        for(int sid : delIds){
            this.deleteScopeDep(depId,sid);
        }
        //添加的id
        for(int sid : addIds){
            this.addScopeDep(depId,sid);
        }
    }

    @Override
    public void deleteScopeDep(int depId, int sDepId) {
        String hql = "delete DepScope ds where ds.depId=? and ds.scopeDep.id=?";
        departmentDao.executeByHql(hql,new Object[]{depId,sDepId});
    }

    @Override
    public void deleteScopeDeps(int depId) {
        String hql = "delete DepScope ds where ds.depId=?";
        departmentDao.executeByHql(hql,depId);
    }

    @Override
    public List<Department> listDepScopeDep(int depId) {
        String hql = "select dep from DepScope ds left join ds.scopeDep dep where ds.depId=?";
        return departmentDao.list(hql,depId);
    }

    @Override
    public List<Department> listUserDep(int userId) {
        return null;
    }

    @Override
    public List<Integer> listDepSocpeDepId(int depId) {
        return departmentDao.listAllExistIds(depId);
    }
}
