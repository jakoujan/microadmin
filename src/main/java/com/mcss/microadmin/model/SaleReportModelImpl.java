/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.OrderDAO;
import com.mcss.microadmin.data.dao.ProductDAO;
import com.mcss.microadmin.data.dao.SaleDAO;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.entity.ProductOrder;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import com.mcss.microadmin.data.filter.SaleReportViewFilter;
import com.mcss.microadmin.service.TicketPrintService;
import java.io.IOException;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class SaleReportModelImpl implements SaleReportModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleReportModelImpl.class);

    @Autowired
    private SaleModel saleModel;

    

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    SaleDAO saleDAO;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @Override
    public Response getSaleReports(SaleReportViewFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.saleDAO.findByFilter(filter));
        response.addField(Constants.AMOUNT, this.saleDAO.sum(filter));
        response.addField(Constants.COUNT, this.saleDAO.count(filter));
        return response;
    }

    private void updateCheckout() {
        this.messagingTemplate.convertAndSend(Constants.CHECKOUT_TOPIC, "order");
    }

}
