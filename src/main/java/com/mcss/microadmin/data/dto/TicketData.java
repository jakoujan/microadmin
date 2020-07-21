/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dto;

import java.math.BigDecimal;

/**
 *
 * @author edgar
 */
public class TicketData {

    private String printerName;
    private String header;
    private String footer;
    private String messageOne;
    private String messageTwo;

    public TicketData() {
    }

    public TicketData(String printerName, String header, String footer, String messageOne, String messageTwo) {
        this.printerName = printerName;
        this.header = header;
        this.footer = footer;
        this.messageOne = messageOne;
        this.messageTwo = messageTwo;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getMessageOne() {
        return messageOne;
    }

    public void setMessageOne(String messageOne) {
        this.messageOne = messageOne;
    }

    public String getMessageTwo() {
        return messageTwo;
    }

    public void setMessageTwo(String messageTwo) {
        this.messageTwo = messageTwo;
    }

}
