/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.filter.PaymentMethodFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PaymentMethodDAOImpl implements ExtendedPaymentMethodDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<PaymentMethod> findByFilter(PaymentMethodFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<PaymentMethod> criteria = builder.createQuery(PaymentMethod.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<PaymentMethod> root = criteria.from(PaymentMethod.class);
        if (filter.getEntity().getMethod()!= null && !filter.getEntity().getMethod().equals("")) {
            predicates.add(builder.like(root.get("method"), "%" + filter.getEntity().getMethod() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<PaymentMethod> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<PaymentMethod> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(PaymentMethodFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<PaymentMethod> root = criteria.from(PaymentMethod.class);
        if (filter.getEntity().getMethod()!= null && !filter.getEntity().getMethod().equals("")) {
            predicates.add(builder.like(root.get("method"), "%" + filter.getEntity().getMethod() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();        
    }

}
