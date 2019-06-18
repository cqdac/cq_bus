package com.egean.cq_bus.IC.controller;

import com.egean.cq_bus.IC.dao.DebitGprsDetailtbbbDao;
import com.egean.cq_bus.IC.domain.DebitGprsDetailtbbb;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shiyinzhi
 */
public class DebitGprsDetailtbbbController {


    /**
     * 条件分页查询公交刷卡信息
     * @param queryEntity
     * @return
     */
    public static List<DebitGprsDetailtbbb> findDebitGprsDetailtbbbByPage(DebitGprsDetailtbbbDao debitGprsDetailtbbbDao,DebitGprsDetailtbbb queryEntity) throws Exception{
        try {

//            List<DebitGprsDetailtbbb> debitGprsDetailtbbbs = new ArrayList<>();

//            ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("UPBUSID",ExampleMatcher.GenericPropertyMatchers.contains())
//                    .withMatcher(ExampleMatcher.MatcherConfigurer)
//                    .withIgnorePaths("TXNTYPE","debitmoney");
//            Example<DebitGprsDetailtbbb> example = Example.of(queryEntity ,matcher);
//            Sort sort = new Sort(Sort.Direction.DESC, "adddatetime");
//            Pageable pageable = new PageRequest(queryEntity.getPage()-1, queryEntity.getPageSize(), sort);
//
//            Page<DebitGprsDetailtbbb> pages = debitGprsDetailtbbbDao.findAll(example,pageable);
//            debitGprsDetailtbbbs.addAll(pages.getContent());

            Specification<DebitGprsDetailtbbb> querySpecifi = (root, criteriaQuery, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(queryEntity.getStartTime())) {
                    //大于或等于传入时间
                    predicates.add(cb.greaterThanOrEqualTo(root.get("debitdate").as(String.class), queryEntity.getStartTime()));
                }
                if (StringUtils.isNotBlank(queryEntity.getEndTime())) {
                    //小于或等于传入时间
                    predicates.add(cb.lessThanOrEqualTo(root.get("debitdate").as(String.class), queryEntity.getEndTime()));
                }
                if (StringUtils.isNotBlank(queryEntity.getUPBUSID())) {
                    //模糊查找
                    predicates.add(cb.equal(root.get("UPBUSID").as(String.class), queryEntity.getUPBUSID()));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            };

            return debitGprsDetailtbbbDao.findAll(querySpecifi);
        }catch (Exception ex){
            throw ex;
        }
    }



}
