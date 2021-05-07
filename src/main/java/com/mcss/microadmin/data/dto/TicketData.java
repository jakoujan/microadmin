/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TicketData {

    @Value("${ticket.printer}")
    private String printerName;
    @Value("${ticket.header}")
    private String header;
    @Value("${ticket.footer}")
    private String footer;
    @Value("${ticket.message.1}")
    private String messageOne;
    @Value("${ticket.message.2}")
    private String messageTwo;
    @Value("${ticket.logo.path}")
    private String logoPath;

    public TicketData() {
    }

    public TicketData(String printerName, String header, String footer, String messageOne, String messageTwo, String logoPath) {
        this.printerName = printerName;
        this.header = header;
        this.footer = footer;
        this.messageOne = messageOne;
        this.messageTwo = messageTwo;
        this.logoPath = logoPath;
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

}
