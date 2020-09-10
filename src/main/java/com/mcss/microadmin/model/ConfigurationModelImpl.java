/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dto.ConfigurationDTO;
import com.mcss.microadmin.data.dto.TicketData;
import com.ispc.slibrary.dto.Response;
import com.mcss.mcom.serial.PortConfig;
import com.mcss.microadmin.data.dto.Store;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationModelImpl implements ConfigurationModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationModelImpl.class);

    @Autowired
    TicketData ticket;

    @Autowired
    Store store;

    @Autowired
    PortConfig port;

    public static final String CONFIG_FILE = "configuration.properties";

    @Override
    public Response getConfiguration() {
        Response response = Response.getInstance();
        response.addField(Constants.TICKET, this.ticket);
        response.addField(Constants.STORE, this.store);
        response.addField(Constants.PORT, this.port);
        return response;
    }

    @Override
    public Response save(ConfigurationDTO configuration) {
        Response response = Response.getInstance();
        this.ticket.setHeader(configuration.getTicket().getHeader().trim());
        this.ticket.setFooter(configuration.getTicket().getFooter().trim());
        this.ticket.setMessageOne(configuration.getTicket().getMessageOne().trim());
        this.ticket.setMessageTwo(configuration.getTicket().getMessageTwo().trim());
        this.ticket.setPrinterName(configuration.getTicket().getPrinterName().trim());
        this.ticket.setLogoPath(configuration.getTicket().getLogoPath().trim());

        this.store.setName(configuration.getStore().getName().trim());
        this.store.setBussinesName(configuration.getStore().getBussinesName().trim());
        this.store.setTaxId(configuration.getStore().getTaxId().trim());
        this.store.setStreet(configuration.getStore().getStreet().trim());
        this.store.setExternal(configuration.getStore().getExternal().trim());
        this.store.setInternal(configuration.getStore().getInternal().trim());
        this.store.setColony(configuration.getStore().getColony().trim());
        this.store.setCity(configuration.getStore().getCity().trim());
        this.store.setCounty(configuration.getStore().getCounty().trim());
        this.store.setState(configuration.getStore().getState());
        this.store.setCountry(configuration.getStore().getCountry());
        this.store.setPostalCode(configuration.getStore().getPostalCode().trim());
        this.store.setPhoneNumber(configuration.getStore().getPhoneNumber().trim());
        this.store.setEmail(configuration.getStore().getEmail().trim());
        this.store.setWebpage(configuration.getStore().getWebpage().trim());
        this.store.setTaxRegime(configuration.getStore().getTaxRegime().trim());

        this.port.setBaudRate(configuration.getPort().getBaudRate());
        this.port.setDataBits(configuration.getPort().getDataBits());
        this.port.setName(configuration.getPort().getName());
        this.port.setParity(configuration.getPort().getParity());
        this.port.setStopBits(configuration.getPort().getStopBits());

        Properties prop = new Properties();
        try (OutputStream out = new FileOutputStream(CONFIG_FILE)) {
            prop.setProperty("ticket.printer", this.ticket.getPrinterName());
            prop.setProperty("ticket.header", this.ticket.getHeader());
            prop.setProperty("ticket.footer", this.ticket.getFooter());
            prop.setProperty("ticket.message.1", this.ticket.getMessageOne());
            prop.setProperty("ticket.message.2", this.ticket.getMessageTwo());
            prop.setProperty("ticket.logo.path", this.ticket.getLogoPath());

            prop.setProperty("store.name", this.port.getName());
            prop.setProperty("store.bussinesName", this.store.getBussinesName());
            prop.setProperty("store.taxId", this.store.getTaxId());
            prop.setProperty("store.street", this.store.getStreet());
            prop.setProperty("store.external", this.store.getExternal());
            prop.setProperty("store.internal", this.store.getInternal());
            prop.setProperty("store.colony", this.store.getColony());
            prop.setProperty("store.city", this.store.getCity());
            prop.setProperty("store.county", this.store.getCounty());
            prop.setProperty("store.state", String.valueOf(this.store.getState()));
            prop.setProperty("store.country", String.valueOf(this.store.getCountry()));
            prop.setProperty("store.postalCode", this.store.getPostalCode());
            prop.setProperty("store.phoneNumber", this.store.getPhoneNumber());
            prop.setProperty("store.email", this.store.getEmail());
            prop.setProperty("store.webpage", this.store.getWebpage());
            prop.setProperty("store.taxRegime", this.store.getTaxRegime());

            prop.setProperty("port.baudrate", String.valueOf(this.port.getBaudRate()));
            prop.setProperty("port.databits", String.valueOf(this.port.getDataBits()));
            prop.setProperty("port.name", this.port.getName());
            prop.setProperty("port.parity", String.valueOf(this.port.getParity().getId()));
            prop.setProperty("port.stopbits", String.valueOf(this.port.getStopBits().getId()));

            prop.store(out, "Archivo de configuración del sistema");
            response.setMessage("Configuración guardada correctamente");
            response.addField(Constants.TICKET, this.ticket);
        } catch (IOException ex) {
            LOGGER.error("Error al guardar la configuración", ex);
            response.setCode(500);
            response.setStatus(Response.RESPONSE_NOT_OK);
            response.setMessage("Error al guardar la configuración: " + ex.getMessage());
        }

        return response;
    }

}
