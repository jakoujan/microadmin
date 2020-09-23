/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author oscardanielrangelmartinez
 */
public interface SaleDAO extends PagingAndSortingRepository<Sale, Integer>{ 

    public Object findByFilter(SaleReportViewFilter filter);

    public Object count(SaleReportViewFilter filter);
     
}
