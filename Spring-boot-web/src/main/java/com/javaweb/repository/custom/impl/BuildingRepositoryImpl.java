package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.security.utils.StringUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public void queryWhereNormal(StringBuilder where, BuildingSearchRequest buildingSearchRequest) {
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.startsWith("area")&& !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchRequest);
                    if(value != null) {
                        if(StringUtil.checkString(value.toString())) {
                            if(item.getType().getName().equals("java.lang.Long") ) {
                                where.append(" AND building." + fieldName + " = " + value);
                            }else if(item.getType().getName().equals("java.lang.Integer") ) {
                                where.append(" AND building." + fieldName + " = " + value);
                            }
                            else if(item.getType().getName().equals("java.lang.String") ) {
                                where.append(" AND building." + fieldName+ " Like '%" + value + "%'");
                            }
                        }
                    }

                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }


    public void queryWhereSpecial(StringBuilder where, BuildingSearchRequest buildingSearchRequest) {
        Long userId = buildingSearchRequest.getStaffId();
        if (userId != null) {
            where.append(" AND user.id = " + userId) ;
        }
        Long rentAreaFrom = buildingSearchRequest.getAreaFrom();
        Long rentAreaTo = buildingSearchRequest.getAreaTo();
        if (rentAreaFrom != null) {
            where.append(" AND rentarea.value >= " + rentAreaFrom);
        }
        if (rentAreaTo != null) {
            where.append(" AND rentarea.value <= " + rentAreaTo) ;
        }
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" AND building.rentprice >= " + rentPriceFrom);
        }
        if (rentPriceTo != null ) {
            where.append(" AND building.rentprice <= " + rentPriceTo);
        }
        // java 7
//		if (!renttypecode.isEmpty() && renttypecode != null) {
//			where.append(" AND ( renttype.code = ");
//			int index = 0;
//			while (index < renttypecode.size()) {
//				String item = renttypecode.get(index);
//				where.append("'" + item + "'");
//				index++;
//				if (index < renttypecode.size()) {
//					where.append( " OR renttype.code = ");
//				}
//			}
//			where.append(")");
//		}
        // java 8
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND (");
            String sqlJoin = typeCode.stream().map(item-> "building.type like '%" + item + "%'").collect(Collectors.joining(" OR "));
            where.append(sqlJoin + ")");
        }

    }


    public void queryJoin(StringBuilder sql, BuildingSearchRequest buildingSearchRequest) {
        // join if user id exists
        Long userId = buildingSearchRequest.getStaffId();
        if (userId != null) {
            sql.append(" JOIN assignmentbuilding ON building.id = assignmentbuilding.buildingid JOIN user ON assignmentbuilding.staffid = user.id");
        }
        // join if rent area exists
        Long rentAreaFrom = buildingSearchRequest.getAreaFrom();
        Long rentAreaTo = buildingSearchRequest.getAreaTo();
        if ( rentAreaFrom != null || rentAreaTo != null ) {
            sql.append( " JOIN rentarea ON building.id = rentarea.buildingid");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        // sql
        StringBuilder sql = new StringBuilder("SELECT building.* FROM building ") ;
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        String groupby = " GROUP BY building.id";
        //where conditions
        queryJoin(sql, buildingSearchRequest);
        queryWhereNormal(where, buildingSearchRequest);
        queryWhereSpecial(where, buildingSearchRequest);
        sql.append(where) ;
        sql.append(groupby) ;
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }
    private String buildQueryFilter() {
        String sql = "SELECT * FROM building";
        return sql;
    }
}
