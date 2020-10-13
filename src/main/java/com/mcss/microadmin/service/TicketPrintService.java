/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.service;

import com.mcss.microadmin.data.dto.Store;
import com.mcss.microadmin.data.dto.TicketData;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.entity.ProductOrder;
import com.mcss.microadmin.service.print.USBPrinter;
import io.github.escposjava.print.Printer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oscardanielrangelmartinez
 */
@Service
public class TicketPrintService {

    @Autowired
    TicketData ticketData;
    
    @Autowired
    Store store;

    public void printOrder(Order order) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DecimalFormat f = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = f.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        f.setDecimalFormatSymbols(symbols);

        Printer printer = new USBPrinter(ticketData);
        io.github.escposjava.PrinterService ps = new io.github.escposjava.PrinterService(printer);
        
        //String productos = getProducts(order);
        String direccion = "Av Madero Norte #25 Colonia Centro, CP:61940, Huetamo, Michoacan";
        ps.init();
        ps.setTextAlignCenter();
        ps.printImage(ticketData.getLogoPath());
        ps.setTextSizeNormal();
        ps.printLn(store.getBussinesName());
        ps.printLn(direccion);
        ps.setTextTypeBold();
        ps.lineBreak(1);
        ps.printLn("CONSUMO");
        ps.lineBreak(1);
        ps.setTextTypeNormal();
        ps.setTextSizeNormal();
        ps.setTextAlignLeft();
        getProducts(order, ps);
        ps.lineBreak(1);
        ps.setTextTypeBold();
        ps.printLn(indentation("Total:")+"$ "+f.format(order.getTotal_amount()));
        ps.lineBreak(2);
        ps.setTextTypeBold();
        ps.print("Fecha: ");
        ps.printLn(hourformatter.format(order.getOrder_date()));
        ps.setTextTypeBold();
        ps.lineBreak(2);
        ps.printLn("Mesa: "+order.getTable().getName());
        ps.lineBreak(2);
        ps.printLn("Responsable: "+order.getResponsible());
        ps.setTextAlignCenter();
        ps.lineBreak(3);
        ps.printLn(ticketData.getMessageOne());
        ps.lineBreak(3);
        ps.printLn(ticketData.getFooter());
        ps.lineBreak(10);
        ps.cutFull();
        ps.close();
    }
    
    public void getProducts(Order order, io.github.escposjava.PrinterService ps) throws UnsupportedEncodingException {
        for (ProductOrder product : order.getProducts()) {
            if (product.getQuantity().compareTo(BigDecimal.ONE) >= 1) {
                ps.printLn(product.getProduct().getDescription());
                ps.print(indentation("       " + product.getQuantity().toString() + " X $"
                        + product.getProduct().getRetailPrice().toString()));
                ps.printLn("$ " + product.getProduct().getRetailPrice().multiply(product.getQuantity()));
            } else {
                ps.print(indentation(product.getProduct().getDescription()));
                ps.printLn("$ " + product.getProduct().getRetailPrice().toString());
            }
        }

    }
   
    private String indentation(String word) {
        String indent = "....................................";
        word += indent.substring(0, indent.length() - word.length());
        return word;
    }
    
}
