/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.filter.SectionFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SectionDAOImpl implements ExtendedSectionDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Iterable<Section> findByFilter(SectionFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Section> criteria = builder.createQuery(Section.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Section> root = criteria.from(Section.class);
        if (filter.getEntity().getSection() != null && !filter.getEntity().getSection().equals("")) {
            predicates.add(builder.like(root.get("section"), "%" + filter.getEntity().getSection() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Section> where = criteria.where(builder.and(predicates.toArray(pa)));
        TypedQuery<Section> tq = manager.createQuery(where);
        tq.setFirstResult(filter.getPage() * filter.getRows());
        tq.setMaxResults(filter.getRows());
        return tq.getResultList();
    }

    @Override
    public Long count(SectionFilter filter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Section> root = criteria.from(Section.class);
        if (filter.getEntity().getSection() != null && !filter.getEntity().getSection().equals("")) {
            predicates.add(builder.like(root.get("section"), "%" + filter.getEntity().getSection() + "%"));
        }
        predicates.add(builder.equal(root.get("active"), Boolean.TRUE));
        Predicate[] pa = new Predicate[predicates.size()];
        CriteriaQuery<Long> where = criteria.select(builder.count(root)).where(builder.and(predicates.toArray(pa)));
        return manager.createQuery(where).getSingleResult();
    }

}
