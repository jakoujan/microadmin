/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.data.filter.SaleFilter;
//import com.mcss.microadmin.data.filter.SaleViewFilter;
//import com.mcss.microadmin.data.view.SaleView;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SaleDAOImpl{

   /*@PersistenceContext
   private EntityManager manager;

    @Override
    public List<SaleView> findByFilter(SaleViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<SaleView> criteria = builder.createQuery(SaleView.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<SaleView> root = criteria.from(SaleView.class);
        if (filter.getEntity().getResponsible()!= null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }
        
        if (filter.getEntity().getTable()!= null && !filter.getEntity().getTable().equals("")) {
            predicates.add(builder.like(root.get("table"), "%" + filter.getEntity().getTable() + "%"));
        }
        
        if (filter.getEntity().getStatus()!= null && !filter.getEntity().getStatus().equals("")) {
            predicates.add(builder.equal(root.get("status"), filter.getEntity().getStatus()));
        }
        
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<SaleView> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.saleBy(builder.asc(root.get("responsible")));
        TypedQuery<SaleView> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(SaleViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<SaleView> root = criteria.from(SaleView.class);
        if (filter.getEntity().getResponsible() != null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }
          
        if (filter.getEntity().getTable()!= null && !filter.getEntity().getTable().equals("")) {
            predicates.add(builder.like(root.get("table"), "%" + filter.getEntity().getTable() + "%"));
        }
        
        if (filter.getEntity().getStatus()!= null && !filter.getEntity().getStatus().equals("")) {
            predicates.add(builder.equal(root.get("status"), filter.getEntity().getStatus()));
        }
        
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
        
    }*/
    
}