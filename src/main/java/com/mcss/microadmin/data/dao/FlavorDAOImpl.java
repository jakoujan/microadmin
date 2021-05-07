/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Flavor;
import com.mcss.microadmin.data.filter.FlavorFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FlavorDAOImpl implements ExtendedFlavorDAO {

   @PersistenceContext
   private EntityManager manager;

    @Override
    public List<Flavor> findByFilter(FlavorFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Flavor> criteria = builder.createQuery(Flavor.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Flavor> root = criteria.from(Flavor.class);
        if (filter.getEntity().getName()!= null && !filter.getEntity().getName().equals("")) {
            predicates.add(builder.like(root.get("name"), "%" + filter.getEntity().getName() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Flavor> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("name")));
        TypedQuery<Flavor> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(FlavorFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Flavor> root = criteria.from(Flavor.class);
        if (filter.getEntity().getName() != null && !filter.getEntity().getName().equals("")) {
            predicates.add(builder.like(root.get("name"), "%" + filter.getEntity().getName() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
        
    }
    
}
