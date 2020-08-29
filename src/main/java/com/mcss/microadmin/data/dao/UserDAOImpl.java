/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.data.filter.UserFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author edgar
 */
public class UserDAOImpl implements ExtendedUserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<User> findByFilter(UserFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteria.from(User.class);
        if (filter.getEntity().getName() != null && !filter.getEntity().getName().equals("")) {
            predicates.add(builder.like(root.get("name"), "%" + filter.getEntity().getName() + "%"));
        }
        if (filter.getEntity().getUsername() != null && !filter.getEntity().getUsername().equals("")) {
            predicates.add(builder.like(root.get("username"), "%" + filter.getEntity().getUsername() + "%"));
        }
        if (filter.getEntity().getEmail() != null && !filter.getEntity().getEmail().equals("")) {
            predicates.add(builder.like(root.get("email"), "%" + filter.getEntity().getEmail() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<User> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<User> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(UserFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteria.from(User.class);
        if (filter.getEntity().getName() != null && !filter.getEntity().getName().equals("")) {
            predicates.add(builder.like(root.get("name"), "%" + filter.getEntity().getName() + "%"));
        }
        if (filter.getEntity().getUsername() != null && !filter.getEntity().getUsername().equals("")) {
            predicates.add(builder.like(root.get("username"), "%" + filter.getEntity().getUsername() + "%"));
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
