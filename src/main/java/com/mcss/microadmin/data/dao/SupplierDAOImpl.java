/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Supplier;
import com.mcss.microadmin.data.filter.SupplierFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SupplierDAOImpl implements ExtendedSupplierDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Supplier> findByFilter(SupplierFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Supplier> criteria = builder.createQuery(Supplier.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Supplier> root = criteria.from(Supplier.class);
        if (filter.getEntity().getBusiness_name() != null && !filter.getEntity().getBusiness_name().equals("")) {
            predicates.add(builder.like(root.get("business_name"), "%" + filter.getEntity().getBusiness_name() + "%"));
        }
        if (filter.getEntity().getContact() != null && !filter.getEntity().getContact().equals("")) {
            predicates.add(builder.like(root.get("contact"), "%" + filter.getEntity().getContact() + "%"));
        }
        if (filter.getEntity().getEmail() != null && !filter.getEntity().getEmail().equals("")) {
            predicates.add(builder.like(root.get("email"), "%" + filter.getEntity().getEmail() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Supplier> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<Supplier> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(SupplierFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Supplier> root = criteria.from(Supplier.class);
        if (filter.getEntity().getBusiness_name() != null && !filter.getEntity().getBusiness_name().equals("")) {
            predicates.add(builder.like(root.get("business_name"), "%" + filter.getEntity().getBusiness_name() + "%"));
        }
        if (filter.getEntity().getContact() != null && !filter.getEntity().getContact().equals("")) {
            predicates.add(builder.like(root.get("contact"), "%" + filter.getEntity().getContact() + "%"));
        }
        if (filter.getEntity().getEmail() != null && !filter.getEntity().getEmail().equals("")) {
            predicates.add(builder.like(root.get("email"), "%" + filter.getEntity().getEmail() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
