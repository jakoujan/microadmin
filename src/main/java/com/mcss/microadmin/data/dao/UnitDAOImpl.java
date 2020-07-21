/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Unit;
import com.mcss.microadmin.data.filter.UnitFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UnitDAOImpl implements ExtendedUnitDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<Unit> findByFilter(UnitFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Unit> root = criteria.from(Unit.class);
        if (filter.getEntity().getUnit() != null && !filter.getEntity().getUnit().equals("")) {
            predicates.add(builder.like(root.get("unit"), "%" + filter.getEntity().getUnit() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Unit> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<Unit> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(UnitFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Unit> root = criteria.from(Unit.class);
        if (filter.getEntity().getUnit() != null && !filter.getEntity().getUnit().equals("")) {
            predicates.add(builder.like(root.get("unit"), "%" + filter.getEntity().getUnit() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
