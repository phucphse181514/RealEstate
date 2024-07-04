package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.security.utils.StringUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void queryWhereNormal(StringBuilder where, CustomerSearchRequest customerSearchRequest){
        try{
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName().toLowerCase();
                if(!fieldName.equals("staffid") && !fieldName.equals("isactive")){
                    Object value = item.get(customerSearchRequest);
                    if(value != null){
                        if(StringUtil.checkString(value.toString())) {
                            if(item.getType().getName().equals("java.lang.Long") ) {
                                where.append(" AND customer." + fieldName + " = " + value.toString().trim());
                            }else if(item.getType().getName().equals("java.lang.Integer") ) {
                                where.append(" AND customer." + fieldName + " = " + value.toString().trim());
                            }
                            else if(item.getType().getName().equals("java.lang.String") ) {
                                where.append(" AND customer." + fieldName+ " Like '%" + value.toString().trim() + "%'");
                            }
                        }
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void queryWhereSpecial(StringBuilder where, CustomerSearchRequest customerSearchRequest) {
        Long userId = customerSearchRequest.getStaffId();
        if (userId != null) {
            where.append(" AND user.id = " + userId) ;
        }
        Long isActive = customerSearchRequest.getIsActive();
        if(isActive != null) {
            where.append(" AND customer.is_active = " + isActive);
        }
    }

    public void queryJoin(StringBuilder sql, CustomerSearchRequest customerSearchRequest){
        Long staffId = customerSearchRequest.getStaffId();
        if(staffId != null){
            sql.append(" JOIN assignmentcustomer ON customer.id = assignmentcustomer.customerid JOIN user ON assignmentcustomer.staffid = user.id");
        }
    }

    @Override
    public List<CustomerEntity> getAllCustomer(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT customer.* FROM customer ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        String groupby = " GROUP BY customer.id ";
        queryJoin(sql, customerSearchRequest);
        queryWhereSpecial(where, customerSearchRequest);
        queryWhereNormal(where, customerSearchRequest);
        sql.append(where) ;
        sql.append(groupby) ;
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }
    @Override
    public int countTotalCustomer(CustomerSearchRequest customerSearchRequest) {
        return getAllCustomer(customerSearchRequest).size();
    }
}
