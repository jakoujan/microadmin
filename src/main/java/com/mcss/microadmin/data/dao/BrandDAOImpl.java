/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Brand;
import com.mcss.microadmin.data.filter.BrandFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BrandDAOImpl implements ExtendedBrandDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<Brand> findByFilter(BrandFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Brand> root = criteria.from(Brand.class);
        if (filter.getEntity().getBrand() != null && !filter.getEntity().getBrand().equals("")) {
            predicates.add(builder.like(root.get("brand"), "%" + filter.getEntity().getBrand() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Brand> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("brand")));
        TypedQuery<Brand> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(BrandFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Brand> root = criteria.from(Brand.class);
        if (filter.getEntity().getBrand() != null && !filter.getEntity().getBrand().equals("")) {
            predicates.add(builder.like(root.get("brand"), "%" + filter.getEntity().getBrand() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
