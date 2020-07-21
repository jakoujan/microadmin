/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Stock;
import com.mcss.microadmin.data.filter.StockFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StockDAOImpl implements ExtendedStockDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<Stock> findByFilter(StockFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Stock> criteria = builder.createQuery(Stock.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Stock> root = criteria.from(Stock.class);
        if (filter.getEntity().getProduct() != null) {
            predicates.add(builder.equal(root.get("product"), filter.getEntity().getProduct()));
        }
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Stock> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<Stock> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(StockFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Stock> root = criteria.from(Stock.class);
        if (filter.getEntity().getProduct() != null) {
            predicates.add(builder.equal(root.get("product"), filter.getEntity().getProduct()));
        }
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
