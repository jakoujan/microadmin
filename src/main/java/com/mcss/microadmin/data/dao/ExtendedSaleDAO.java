/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.data.filter.SaleFilter;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import com.mcss.microadmin.data.view.SaleReportView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author oscardanielrangelmartinez
 */
public interface ExtendedSaleDAO {
    
    public List<SaleReportView> findByFilter(SaleReportViewFilter filter);
    
    public Long count(SaleReportViewFilter filter);
    
    public BigDecimal sum(SaleReportViewFilter filter);
    
}
