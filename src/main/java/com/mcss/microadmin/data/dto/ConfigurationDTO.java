/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dto;

import com.mcss.mcom.serial.PortConfig;

/**
 *
 * @author edgar
 */
public class ConfigurationDTO {

    TicketData ticket;
    Store store;
    PortConfig port;

    public TicketData getTicket() {
        return ticket;
    }

    public void setTicket(TicketData ticket) {
        this.ticket = ticket;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public PortConfig getPort() {
        return port;
    }

    public void setPort(PortConfig port) {
        this.port = port;
    }

}
