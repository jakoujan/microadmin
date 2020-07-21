/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.exporter;

//import com.mcss.microadmin.data.dto.TicketData;
//import com.mcss.microadmin.data.entity.Batch;
//import com.mcss.microadmin.data.entity.Customer;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//import javax.imageio.ImageIO;
//import net.sf.jasperreports.engine.JRDefaultScriptlet;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author edgar
 */
public class PDFFileExporter { // extends JRDefaultScriptlet {

//    private static final String LOGO_PATH = "/opt/spc/redos/logo.png";
//    private static final Logger LOGGER = LoggerFactory.getLogger(PDFFileExporter.class);
//
//    public static final String exportB64TicketFile(Batch batch, String template, TicketData data, BigDecimal correctionFactor) throws JRException, FileNotFoundException, IOException {
//        InputStream inputStream = PDFFileExporter.class.getClassLoader().getResourceAsStream(template);
//        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, PDFFileExporter.getParameters(batch, data, correctionFactor));
//        byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
//        return Base64.getEncoder().encodeToString(pdfBytes);
//    }
//
//    public BigDecimal calculateDosification(Batch batch) {
//        BigDecimal result = batch.getStartWeight().subtract(batch.getEndWeight());
//        BigDecimal cv = result.compareTo(BigDecimal.ZERO) <= 0 ? result.multiply(new BigDecimal(-1)) : result;
//        return cv;
//    }
//
//    public String formatAddress(Batch batch) {
//        Customer customer = batch.getCustomer();
//        if (customer != null) {
//            StringBuilder sb = new StringBuilder(customer.getStreet());
//            sb
//                    .append(" ").append(customer.getExternal_number())
//                    .append(" ").append(customer.getInternal_number())
//                    .append("\n").append(customer.getSettlement())
//                    .append(", ").append(customer.getCity())
//                    .append("\n").append(customer.getCounty())
//                    .append("\n").append(customer.getState().getName())
//                    .append(",").append(customer.getCountry().getName())
//                    .append("\nC.P. ").append(customer.getPostal_code());
//            return sb.toString();
//        }
//        return "";
//    }
//
//    private static Map getParameters(Batch batch, TicketData data, BigDecimal correctionFactor) throws FileNotFoundException, IOException {
//        Map parameters = new HashMap();
//        parameters.put("BATCH", batch);
//        parameters.put("INFO", data);
//        parameters.put("LOGO_IMAGE", getLogo(LOGO_PATH));
//        parameters.put("CORRECTION_FACTOR", correctionFactor);
//        parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX"));
//        return parameters;
//    }
//
//    public static BufferedImage getLogo(String path) throws IOException {
//        return ImageIO.read(new File(path));
//    }
}
