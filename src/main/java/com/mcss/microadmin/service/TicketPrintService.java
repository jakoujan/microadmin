/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.service;

import com.mcss.microadmin.data.dto.Store;
import com.mcss.microadmin.data.dto.TicketData;
import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.service.print.USBPrinter;
import io.github.escposjava.print.Printer;
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
        
        ps.init();
        ps.setTextAlignCenter();
        ps.lineBreak(3);
        ps.setTextSize2W();
        ps.printLn(store.getBussinesName());
    }
}
