/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.CashAction;
import com.mcss.microadmin.data.filter.CashActionFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CashActionDAOImpl implements ExtendedCashActionDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<CashAction> findByFilter(CashActionFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<CashAction> criteria = builder.createQuery(CashAction.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<CashAction> root = criteria.from(CashAction.class);
        if (filter.getEntity().getAction_date()!= null && !filter.getEntity().getAction_date().equals("")) {
            predicates.add(builder.like(root.get("order_date"), "%" + filter.getEntity().getAction_date() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<CashAction> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("cashAction")));
        TypedQuery<CashAction> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(CashActionFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<CashAction> root = criteria.from(CashAction.class);
        if (filter.getEntity().getAction_date()!= null && !filter.getEntity().getAction_date().equals("")) {
            predicates.add(builder.like(root.get("order_date"), "%" + filter.getEntity().getCashier()+ "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
