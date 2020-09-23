/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.data.filter.SaleFilter;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;

/**
 *
 * @author edgar
 */
public interface SaleReportModel {
    
    public Response getSaleReports(SaleReportViewFilter filter);
    
}
