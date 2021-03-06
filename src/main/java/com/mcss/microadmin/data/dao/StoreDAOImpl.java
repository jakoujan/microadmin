/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.filter.StoreFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StoreDAOImpl implements ExtendedStoreDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<Store> findByFilter(StoreFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Store> criteria = builder.createQuery(Store.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Store> root = criteria.from(Store.class);
        if (filter.getEntity().getDescription() != null && !filter.getEntity().getDescription().equals("")) {
            predicates.add(builder.like(root.get("description"), "%" + filter.getEntity().getDescription() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Store> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("description")));
        TypedQuery<Store> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(StoreFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Store> root = criteria.from(Store.class);
        if (filter.getEntity().getDescription() != null && !filter.getEntity().getDescription().equals("")) {
            predicates.add(builder.like(root.get("description"), "%" + filter.getEntity().getDescription() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
