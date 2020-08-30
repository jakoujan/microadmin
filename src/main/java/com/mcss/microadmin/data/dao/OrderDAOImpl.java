/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderFilter;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrderDAOImpl implements ExtendedOrderDAO {

   @PersistenceContext
   private EntityManager manager;

    @Override
    public List<Order> findByFilter(OrderViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Order> root = criteria.from(Order.class);
        if (filter.getEntity().getResponsible()!= null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }
        
        if (filter.getEntity().getTable()!= null && !filter.getEntity().getTable().equals("")) {
            predicates.add(builder.like(root.get("table"), "%" + filter.getEntity().getTable() + "%"));
        }
        
        /*if (filter.getEntity().getStatus()!= null && !filter.getEntity().getStatus().equals("")) {
            predicates.add(builder.like(root.get("status"), "%" + filter.getEntity().getTable() + "%"));
        }*/
        
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Order> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("responsible")));
        TypedQuery<Order> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(OrderViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Order> root = criteria.from(Order.class);
        if (filter.getEntity().getResponsible() != null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }
        
        if (filter.getEntity().getResponsible()!= null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }
        
        if (filter.getEntity().getTable()!= null && !filter.getEntity().getTable().equals("")) {
            predicates.add(builder.like(root.get("table"), "%" + filter.getEntity().getTable() + "%"));
        }
        
        /*if (filter.getEntity().getStatus()!= null && !filter.getEntity().getStatus().equals("")) {
            predicates.add(builder.like(root.get("status"), "%" + filter.getEntity().getTable() + "%"));
        }*/
        
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
        
    }
    
}
