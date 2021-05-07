/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Table;
import com.mcss.microadmin.data.filter.TableFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TableDAOImpl implements ExtendedTableDAO {

   @PersistenceContext
   private EntityManager manager;

    @Override
    public List<Table> findByFilter(TableFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Table> criteria = builder.createQuery(Table.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Table> root = criteria.from(Table.class);
        if (filter.getEntity().getName()!= null && !filter.getEntity().getName().equals("")) {
            predicates.add(builder.like(root.get("name"), "%" + filter.getEntity().getName() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Table> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("name")));
        TypedQuery<Table> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(TableFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Table> root = criteria.from(Table.class);
        if (filter.getEntity().getName() != null && !filter.getEntity().getName().equals("")) {
            predicates.add(builder.like(root.get("name"), "%" + filter.getEntity().getName() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
        
    }
    
}
