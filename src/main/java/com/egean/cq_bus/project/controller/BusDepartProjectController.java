package com.egean.cq_bus.project.controller;
import com.egean.cq_bus.domain.TableListForHistoryProject;
import com.egean.cq_bus.project.dao.BusDepartProjectDao;
import com.egean.cq_bus.project.domain.BusDepartProject;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shiyinzhi
 */
public class BusDepartProjectController {

    private static SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 条件分页查询排班方案信息
     * @param queryEntity
     * @return
     */
    public static List<TableListForHistoryProject> findBusDepartProjectByPage(BusDepartProjectDao busDepartProjectDao, BusDepartProject queryEntity) throws Exception{
        try {

            List<TableListForHistoryProject> tableListForHistoryProjectList = new ArrayList<>();
            Specification<BusDepartProject> querySpecifi = (root, criteriaQuery, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(queryEntity.getSearchTime())) {
                    //模糊查找
                    try{
//                        predicates.add(cb.greaterThanOrEqualTo(root.get("createTime"), sdf.parse(queryEntity.getSearchTime()+" 00:00:00")));
//                        predicates.add(cb.lessThanOrEqualTo(root.get("createTime"), sdf.parse(queryEntity.getSearchTime()+" 23:59:59")));
                        predicates.add(cb.equal(root.get("projectDate"), queryEntity.getSearchTime()));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                if(StringUtils.isNotBlank(queryEntity.getLineNum())){
                    predicates.add(cb.equal(root.get("lineNum"), queryEntity.getLineNum()));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            TableListForHistoryProject tableListForHistoryProject = new TableListForHistoryProject();
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            Pageable pageable = new PageRequest(queryEntity.getPage()-1, queryEntity.getPageSize(), sort);
            Page<BusDepartProject> pages = busDepartProjectDao.findAll(querySpecifi,pageable);
            tableListForHistoryProject.setAaData(pages.getContent());
            tableListForHistoryProject.setiTotalDisplayRecords(Integer.parseInt(pages.getTotalElements()+""));
            tableListForHistoryProject.setiTotalRecords(Integer.parseInt(pages.getTotalElements()+""));
            tableListForHistoryProject.setLineNum(queryEntity.getLineNum());
            tableListForHistoryProjectList.add(tableListForHistoryProject);
            return tableListForHistoryProjectList;
        }catch (Exception ex){
            throw ex;
        }
    }


    /**
     * 保存排班方案
     * @param busDepartProjectDao
     * @param queryEntity
     */
    public static BusDepartProject saveBusDepartProject(BusDepartProjectDao busDepartProjectDao, BusDepartProject queryEntity){
        return busDepartProjectDao.save(queryEntity);
    }

}
