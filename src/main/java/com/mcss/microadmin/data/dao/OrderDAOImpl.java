/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.filter.OrderFilter;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import com.mcss.microadmin.data.view.OrderView;
import com.mcss.microadmin.data.view.ProductPreparation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrderDAOImpl implements ExtendedOrderDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<OrderView> findByFilter(OrderViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<OrderView> criteria = builder.createQuery(OrderView.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<OrderView> root = criteria.from(OrderView.class);
        if (filter.getEntity().getResponsible() != null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }

        if (filter.getEntity().getTable() != null && !filter.getEntity().getTable().equals("")) {
            predicates.add(builder.like(root.get("table"), "%" + filter.getEntity().getTable() + "%"));
        }

        if (filter.getEntity().getStatus() != null && !filter.getEntity().getStatus().equals("")) {
            predicates.add(builder.equal(root.get("status"), filter.getEntity().getStatus()));
        }

        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<OrderView> where = criteria.where(builder.and(predicates.toArray(pa)));
        criteria.orderBy(builder.asc(root.get("responsible")));
        TypedQuery<OrderView> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(OrderViewFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<OrderView> root = criteria.from(OrderView.class);
        if (filter.getEntity().getResponsible() != null && !filter.getEntity().getResponsible().equals("")) {
            predicates.add(builder.like(root.get("responsible"), "%" + filter.getEntity().getResponsible() + "%"));
        }

        if (filter.getEntity().getTable() != null && !filter.getEntity().getTable().equals("")) {
            predicates.add(builder.like(root.get("table"), "%" + filter.getEntity().getTable() + "%"));
        }

        if (filter.getEntity().getStatus() != null && !filter.getEntity().getStatus().equals("")) {
            predicates.add(builder.equal(root.get("status"), filter.getEntity().getStatus()));
        }

        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();

    }

    @Override
    public Iterable<ProductPreparation> findProductsByStatus(Integer status, Integer section) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<ProductPreparation> criteria = builder.createQuery(ProductPreparation.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<ProductPreparation> root = criteria.from(ProductPreparation.class);
        predicates.add(builder.equal(root.get("status"), status));
        if(section != null) {
            predicates.add(builder.equal(root.get("section"), section));
        }
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<ProductPreparation> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<ProductPreparation> tq = manager.createQuery(where);
        return tq.getResultList();
    }

}
