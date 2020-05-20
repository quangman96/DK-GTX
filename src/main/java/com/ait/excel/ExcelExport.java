//package com.ait.excel;
//
//import com.ait.model.Vehicle;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class ExcelExport {
//    public static ByteArrayInputStream listToExcelFile(List<Vehicle> vehicles) {
//        try(Workbook workbook = new XSSFWorkbook()){
//            Sheet sheet = workbook.createSheet("Vehicles");
//
//            Row row = sheet.createRow(0);
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
//            headerCellStyle.setFillPattern(FillPatternType.THIN_BACKWARD_DIAG);
//            // Creating header
//            Cell cell = row.createCell(0);
//            cell.setCellValue("name");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(1);
//            cell.setCellValue("address");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(2);
//            cell.setCellValue("identity");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(3);
//            cell.setCellValue("identity_day");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(4);
//            cell.setCellValue("engine_num");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(5);
//            cell.setCellValue("chassis_num");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(6);
//            cell.setCellValue("colorName");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(7);
//            cell.setCellValue("brandName");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(8);
//            cell.setCellValue("provinceName");
//            cell.setCellStyle(headerCellStyle);
//
//            cell = row.createCell(9);
//            cell.setCellValue("typeName");
//            cell.setCellStyle(headerCellStyle);
//
//            // Creating data rows for each customer
////            for(int i = 0; i < vehicles.size(); i++) {
////                Row dataRow = sheet.createRow(i + 1);
////                dataRow.createCell(0).setCellValue(vehicles.get(i).getName());
////                dataRow.createCell(1).setCellValue(vehicles.get(i).getAddress());
////                dataRow.createCell(2).setCellValue(vehicles.get(i).getIdentity());
////                dataRow.createCell(3).setCellValue(vehicles.get(i).getIdentity_day());
////                dataRow.createCell(4).setCellValue(vehicles.get(i).getEngine_num());
////                dataRow.createCell(5).setCellValue(vehicles.get(i).getChassis_num());
////                dataRow.createCell(6).setCellValue(vehicles.get(i).getColorName());
////                dataRow.createCell(7).setCellValue(vehicles.get(i).getBrandName());
////                dataRow.createCell(8).setCellValue(vehicles.get(i).getProvinceName());
////                dataRow.createCell(9).setCellValue(vehicles.get(i).getTypeName());
////
////
////            }
//
//            // Making size of column auto resize to fit with data
//            sheet.autoSizeColumn(0);
//            sheet.autoSizeColumn(1);
//            sheet.autoSizeColumn(2);
//            sheet.autoSizeColumn(3);
//            sheet.autoSizeColumn(4);
//            sheet.autoSizeColumn(5);
//            sheet.autoSizeColumn(6);
//            sheet.autoSizeColumn(7);
//            sheet.autoSizeColumn(8);
//            sheet.autoSizeColumn(9);
//
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            workbook.write(outputStream);
//            return new ByteArrayInputStream(outputStream.toByteArray());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//}
