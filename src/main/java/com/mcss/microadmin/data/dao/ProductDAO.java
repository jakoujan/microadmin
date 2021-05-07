/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author edgar
 */
public interface ProductDAO extends PagingAndSortingRepository<Product, Integer>, ExtendedProductDAO {

    @Query("SELECT new com.mcss.microadmin.data.entity.Product(p.id, p.barcode, p.description, p.retailPrice, p.type) From Product p JOIN p.type pt WHERE p.active=:active")
    public Iterable<Product> findByActive(@Param("active") boolean active);

}
