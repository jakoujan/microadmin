/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.data.ProductStatus;
import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.OrderDAO;
import com.mcss.microadmin.data.dao.ProductDAO;
import com.mcss.microadmin.data.dao.ProductOrderDAO;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.entity.ProductOrder;
import com.mcss.microadmin.data.filter.OrderViewFilter;
import com.mcss.microadmin.data.view.ProductPreparation;
import com.mcss.microadmin.service.TicketPrintService;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class OrderModelImpl implements OrderModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderModelImpl.class);

    @Autowired
    private SaleModel saleModel;

    @Autowired
    private TicketPrintService ticketPrint;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProductOrderDAO productOrderDAO;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    @Transactional
    public Response save(Order order) {
        Response response = Response.getInstance();
        order.getProducts().forEach((ProductOrder product) -> {
            if (product.getStatus() == null) {
                product.setStatus(ProductStatus.PREPARING);
            }
            product.setOrder(order);
            product.setProduct(this.productDAO.findById(product.getProduct().getId()).get());
        });
        this.orderDAO.save(order);
        this.updateCheckout();
        switch (order.getStatus().getId()) {
            case 3: {
                try {
                    ticketPrint.printOrder(order);
                } catch (IOException ex) {
                    LOGGER.error("Error al imprimir ticket", ex);
                } catch (NullPointerException ex) {
                    LOGGER.error("Error al imprimir ticket", ex);
                }
            }
            break;

            case 4:
                this.saleModel.createSaleFromOrder(order);
                break;
        }
        response.setMessage("La orden se ha generado con exito");
        return response;
    }

    @Override
    @Transactional
    public Response delete(Order order) {
        Response response = Response.getInstance();
        //order.setActive(Status.INACTIVE);
        this.orderDAO.delete(order);
        response.setMessage("La orden se ha elimnado");
        response.addField(Constants.ENTITY, order);
        return response;
    }

    @Override
    public Response getOrders(OrderViewFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.orderDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.orderDAO.count(filter));
        return response;
    }

    @Override
    public Response getOrder(Integer id) {
        Response response = Response.getInstance();
        response.addField(Constants.ENTITY, this.orderDAO.findById(id).get());
        return response;
    }

    @Override
    public Response productElaboration(Integer status) {
        Response response = Response.getInstance();
        Iterable<ProductPreparation> products = this.orderDAO.findProductsByStatus(status, null);
        response.addField(Constants.PRODUCTS, products);
        return response;

    }

    @Override
    public Response productElaborationDone(Integer id) {
        Optional<ProductOrder> opo = this.productOrderDAO.findById(id);
        if (opo.isPresent()) {
            ProductOrder po = opo.get();
            po.setStatus(ProductStatus.PREPARED);
            this.productOrderDAO.save(po);
        }
        return this.productElaboration(ProductStatus.PREPARING);
    }

    private void updateCheckout() {
        this.messagingTemplate.convertAndSend(Constants.CHECKOUT_TOPIC, "order");
    }

    @Override
    public Response productElaboration(Integer status, Integer section) {
        Response response = Response.getInstance();
        Iterable<ProductPreparation> products = this.orderDAO.findProductsByStatus(status, section);
        response.addField(Constants.PRODUCTS, products);
        return response;
    }

}
