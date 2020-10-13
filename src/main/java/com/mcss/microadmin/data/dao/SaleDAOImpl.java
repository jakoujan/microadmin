/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.ispc.slibrary.helper.DateHelper;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import com.mcss.microadmin.data.view.SaleReportView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SaleDAOImpl implements ExtendedSaleDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<SaleReportView> findByFilter(SaleReportViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<SaleReportView> criteria = builder.createQuery(SaleReportView.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<SaleReportView> root = criteria.from(SaleReportView.class);
        if (filter.getEntity().getSaleDate() != null) {
            predicates.add(builder.between(root.<Date>get("saleDate"), 
                    DateHelper.prepareDateForBetweenStart(filter.getEntity().getSaleDate()), 
                    DateHelper.prepareDateForBetweenEnd(filter.getEntity().getSaleDate())));
        }

        if (filter.getEntity().getCashier() != null) {
            predicates.add(builder.equal(root.get("cashier"), filter.getEntity().getCashier()));
        }

        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<SaleReportView> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("orderComand")));
        criteria.orderBy(builder.asc(root.get("cashier")));
        TypedQuery<SaleReportView> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(SaleReportViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<SaleReportView> root = criteria.from(SaleReportView.class);
        if (filter.getEntity().getSaleDate() != null) {
            predicates.add(builder.equal(root.get("saleDate"), filter.getEntity().getSaleDate()));
        }

        if (filter.getEntity().getCashier() != null) {
            predicates.add(builder.equal(root.get("cashier"), filter.getEntity().getCashier()));
        }

        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();

    }

}
