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
import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.service.print.USBPrinter;
import io.github.escposjava.print.Printer;
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

    public void print(Sale sale) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DecimalFormat f = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = f.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        f.setDecimalFormatSymbols(symbols);

        Printer printer = new USBPrinter(ticketData);
        io.github.escposjava.PrinterService ps = new io.github.escposjava.PrinterService(printer);
        
        String products = getProducts(sale.getOrder().getOrder());
        String direccion = "Av Madero Norte #25 Colonia Centro, CP:61940, Huetamo, Michoacan";
        
        ps.init();
        ps.setTextAlignCenter();
        ps.lineBreak(3);
        ps.setTextSize2W();
        ps.printLn(store.getBussinesName());
        ps.printLn(direccion);
        ps.printLn("CONSUMO");
        ps.printLn(products);
        
             
    }
    
    public String getProducts(Order order){
        
        String productsString = "";
        BigDecimal total = new BigDecimal(0);
        
        for(ProductOrder product : order.getProducts()) {
            productsString += product.getProduct().getDescription();
            if(product.getQuantity().compareTo(BigDecimal.ONE) >= 1){
                productsString += "\n";
                productsString += "     ";
                productsString += product.getQuantity().toString()+" X $"+
                        product.getProduct().getRetailPrice().toString();
                productsString += ".......$"+product.getProduct().getRetailPrice().multiply(product.getQuantity());
                productsString += "\n";
                
                total.add(product.getProduct().getRetailPrice().multiply(product.getQuantity()));
                
            }else{
                productsString += "..........$"+product.getProduct().getRetailPrice();
                productsString += "\n";
                total.add(product.getProduct().getRetailPrice());
            }
        }
        
        productsString += "\n";
        productsString += "TOTAL..........$ "+total.toString();
        
       return productsString;
    }
}
