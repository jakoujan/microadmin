/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.service.print;

import com.mcss.microadmin.data.dto.TicketData;
import com.mcss.microadmin.data.entity.Store;
import io.github.escposjava.print.Printer;
import java.io.File;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author edgar
 */
public class USBPrinter implements Printer {

    private static final Logger LOGGER = LoggerFactory.getLogger(USBPrinter.class);

    private static final String FILEPATH = "/home/edgar/PDF/ThermalTest.txt";
    private static final File FILE = new File(FILEPATH);

    private final TicketData ticketData;

    private DocPrintJob job;
    private DocFlavor flavor;

    ByteBuffer bbuf;

    public USBPrinter(TicketData ticketData) {
        this.ticketData = ticketData;
    }

    @Override
    public void open() {
        bbuf = ByteBuffer.allocate(5120);
        // find the printService of name printerName
        flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);
        PrintService service = findPrintService(this.ticketData.getPrinterName(), printService);
        this.job = service.createPrintJob();
    }

    @Override
    public void write(byte[] bytes) {
        this.bbuf.put(bytes);
    }

    @Override
    public void close() {
        try {
            LOGGER.info("Buffer size: " + this.bbuf.array().length);
            LOGGER.info("Buffer used bytes: " + this.bbuf.position());
            byte[] ar = Arrays.copyOfRange(this.bbuf.array(), 0, this.bbuf.position());
            LOGGER.info("array size: " + ar.length);
            //USBPrinter.printToFile(ar);
            Doc doc = new SimpleDoc(ar, flavor, null);
            job.print(doc, null);
        } catch (PrintException e) {
            LOGGER.error("Error al imprimir", e);
        }
    }

    public static void printToFile(byte[] bytes) {
        try (PrintWriter out = new PrintWriter(FILEPATH)) {
            out.print(new String(bytes));
        } catch (Exception e) {
            LOGGER.error("Error al crear el archivo: " + FILE.getName());
        }
    }

    private PrintService findPrintService(String printerName,
            PrintService[] services) {
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }

        return null;
    }

}
