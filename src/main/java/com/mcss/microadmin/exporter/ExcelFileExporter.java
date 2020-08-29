/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.exporter;

//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.Base64;
//import java.util.Iterator;
//import java.util.List;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author edgar
 */
public class ExcelFileExporter {

//    public static String exportB64File(List<Batch> records) throws IOException {
//        String b64 = "";
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Reporte generado");
//        String[] titles = {"Folio", "Usuario", "Turno", "Cliente", "Movimiento", "Producto", "Vehículo", "Fecha inicial", "Peso inicial", "Fecha final", "Peso final", "Cantidad", "Dosificación"};
//        Row row = sheet.createRow(0);
//        int cells = 0;
//        CellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
//        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        for (String title : titles) {
//            Cell cell = row.createCell(cells);
//            cell.setCellValue(title);
//            cell.setCellStyle(cellStyle);
//            cells++;
//        }
//        int rows = 1;
//        Iterator<Batch> iterator = records.iterator();
//        while (iterator.hasNext()) {
//            Batch batch = iterator.next();
//            row = sheet.createRow(rows);
//            Cell cell = row.createCell(0);
//            cell.setCellValue(batch.getFolio());
//            cell = row.createCell(1);
//            cell.setCellValue(batch.getUser().getName());
//            cell = row.createCell(2);
//            cell.setCellValue(batch.getShift().getName());
//            cell = row.createCell(3);
//            cell.setCellValue(batch.getCustomer() != null ? batch.getCustomer().getBusiness_name() : "");
//            cell = row.createCell(4);
//            cell.setCellValue(batch.getBatchType() == 1 ? "Dosificación" : "Llenado");
//            cell = row.createCell(5);
//            cell.setCellValue(batch.getMonitor().getProduct());
//            cell = row.createCell(6);
//            cell.setCellValue(batch.getTruckId());
//            cell = row.createCell(7);
//            cellStyle = workbook.createCellStyle();
//            DataFormat format = workbook.createDataFormat();
//            cellStyle.setDataFormat(format.getFormat("[$-809]dd/MM/yyyy HH:mm;@"));
//            cell.setCellStyle(cellStyle);
//            cell.setCellValue(batch.getStartDate());
//            cell = row.createCell(8);
//            cell.setCellValue(parseBigDecimal(batch.getStartWeight()));
//            cell = row.createCell(9);
//            cell.setCellStyle(cellStyle);
//            cell.setCellValue(batch.getEndDate());
//            cell = row.createCell(10);
//            cell.setCellValue(parseBigDecimal(batch.getEndWeight()));
//            cell = row.createCell(11);
//            cell.setCellValue(parseBigDecimal(batch.getQuantity() != null ? batch.getQuantity() : BigDecimal.ZERO));
//            cell = row.createCell(12);
//            cell.setCellValue(calculateDosification(batch));
//            rows++;
//        }
//        for (int i = 0; i < titles.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//            workbook.write(bos);
//            b64 = Base64.getEncoder().encodeToString(bos.toByteArray());
//        }
//        return b64;
//    }
//
//    private static Double calculateDosification(Batch batch) {
//        BigDecimal result = batch.getStartWeight().subtract(batch.getEndWeight());
//        BigDecimal cv = result.compareTo(BigDecimal.ZERO) <= 0 ? result.multiply(new BigDecimal(-1)) : result;
//        return parseBigDecimal(cv);
//    }
//
//    private static final Double parseBigDecimal(BigDecimal value) {
//        return Double.parseDouble(value.toString());
//    }

}
