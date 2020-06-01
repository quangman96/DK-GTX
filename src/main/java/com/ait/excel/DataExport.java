package com.ait.excel;

import com.ait.model.Brand;
import com.ait.model.Color;
import com.ait.model.Customer;
import com.ait.model.Province;
import com.ait.model.Vehicle;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DataExport {
    public static ByteArrayInputStream listToExcelFile(List<Customer> customers, List<Vehicle> vehicles,
                                                       List<Province> provinces, List<Brand> brands,
                                                       List<Color> colors
                                                       ) {

        try(Workbook workbook = new XSSFWorkbook()){
            Sheet customerSheet = workbook.createSheet("Customers");
            Sheet vehicleSheet = workbook.createSheet("Vehicles");
            Sheet provinceSheet = workbook.createSheet("Provinces");
            Sheet brandSheet = workbook.createSheet("Brands");
            Sheet colorSheet = workbook.createSheet("Colors");
            Sheet test = workbook.createSheet("test");

            Font font = workbook.createFont();
            font.setBold(true);
            font.setItalic(false);
            font.setFontHeightInPoints((short) 20);


            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.BIG_SPOTS);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFont(font);

//            customerSheet.addMergedRegion(CellRangeAddress.valueOf("C3:H4"));
            Row row = customerSheet.createRow(2);
            Cell cell = row.createCell(2);
            cell.setCellValue("Dữ liệu chủ sở hữu đăng ký xe");
            cell.setCellStyle(headerCellStyle);

            test.autoSizeColumn(0);

            // Creating header
            cell = row.createCell(2);
            cell.setCellValue("id");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("address");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("phone");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("identity");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("province_name");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows for each customer
            for(int i = 0; i < customers.size(); i++) {
                Row dataRow = customerSheet.createRow(i + 1);
                dataRow.createCell(2).setCellValue(customers.get(i).getId());
                dataRow.createCell(3).setCellValue(customers.get(i).getName());
                dataRow.createCell(4).setCellValue(customers.get(i).getAddress());
                dataRow.createCell(5).setCellValue(customers.get(i).getPhone());
                dataRow.createCell(6).setCellValue(customers.get(i).getIdentity());
                dataRow.createCell(7).setCellValue(customers.get(i).getProvince_name());
            }

            // Making size of column auto resize to fit with data
            customerSheet.autoSizeColumn(2);
            customerSheet.autoSizeColumn(3);
            customerSheet.autoSizeColumn(4);
            customerSheet.autoSizeColumn(5);
            customerSheet.autoSizeColumn(6);
            customerSheet.autoSizeColumn(7);



            Row row1 = vehicleSheet.createRow(5);
            // Creating header
            Cell cell1 = row1.createCell(2);
            cell1.setCellValue("id");
            cell1.setCellStyle(headerCellStyle);

            cell1 = row1.createCell(3);
            cell1.setCellValue("customer_name");
            cell1.setCellStyle(headerCellStyle);

            cell1 = row1.createCell(4);
            cell1.setCellValue("brand_name");
            cell1.setCellStyle(headerCellStyle);

            cell1 = row1.createCell(5);
            cell1.setCellValue("color_name");
            cell1.setCellStyle(headerCellStyle);

            cell1 = row1.createCell(6);
            cell1.setCellValue("engine_num");
            cell1.setCellStyle(headerCellStyle);

            cell1 = row1.createCell(7);
            cell1.setCellValue("chassis_num");
            cell1.setCellStyle(headerCellStyle);

            // Creating data rows for each customer
            for(int i = 0; i < vehicles.size(); i++) {
                Row dataRow = vehicleSheet.createRow(i + 1);
                dataRow.createCell(2).setCellValue(vehicles.get(i).getId());
                dataRow.createCell(3).setCellValue(vehicles.get(i).getCustomer_name());
                dataRow.createCell(4).setCellValue(vehicles.get(i).getBrand_name());
                dataRow.createCell(5).setCellValue(vehicles.get(i).getColor_name());
                dataRow.createCell(6).setCellValue(vehicles.get(i).getEngine_num());
                dataRow.createCell(7).setCellValue(vehicles.get(i).getChassis_num());
            }

            // Making size of column auto resize to fit with data
            vehicleSheet.autoSizeColumn(2);
            vehicleSheet.autoSizeColumn(3);
            vehicleSheet.autoSizeColumn(4);
            vehicleSheet.autoSizeColumn(5);
            vehicleSheet.autoSizeColumn(6);
            vehicleSheet.autoSizeColumn(7);


            Row row2 = provinceSheet.createRow(0);
            // Creating header
            Cell cell2 = row2.createCell(0);
            cell2.setCellValue("id");
            cell2.setCellStyle(headerCellStyle);

            cell2 = row2.createCell(1);
            cell2.setCellValue("name");
            cell2.setCellStyle(headerCellStyle);

            cell2 = row2.createCell(2);
            cell2.setCellValue("province_code");
            cell2.setCellStyle(headerCellStyle);

            cell2 = row2.createCell(3);
            cell2.setCellValue("telephone_code");
            cell2.setCellStyle(headerCellStyle);


            // Creating data rows for each customer
            for(int i = 0; i < provinces.size(); i++) {
                Row dataRow = provinceSheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(provinces.get(i).getId());
                dataRow.createCell(1).setCellValue(provinces.get(i).getName());
                dataRow.createCell(2).setCellValue(provinces.get(i).getProvince_code());
                dataRow.createCell(3).setCellValue(provinces.get(i).getTelephone_code());

            }

            // Making size of column auto resize to fit with data
            provinceSheet.autoSizeColumn(0);
            provinceSheet.autoSizeColumn(1);
            provinceSheet.autoSizeColumn(2);
            provinceSheet.autoSizeColumn(3);


            Row row3 = brandSheet.createRow(0);
            // Creating header
            Cell cell3 = row3.createCell(0);
            cell3.setCellValue("id");
            cell3.setCellStyle(headerCellStyle);

            cell3 = row3.createCell(1);
            cell3.setCellValue("name");
            cell3.setCellStyle(headerCellStyle);

            // Creating data rows for each customer
            for(int i = 0; i < brands.size(); i++) {
                Row dataRow = brandSheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(brands.get(i).getId());
                dataRow.createCell(1).setCellValue(brands.get(i).getName());
            }

            // Making size of column auto resize to fit with data
            brandSheet.autoSizeColumn(0);

            Row row4 = colorSheet.createRow(0);

            // Creating header
            Cell cell4 = row4.createCell(0);
            cell4.setCellValue("id");
            cell4.setCellStyle(headerCellStyle);

            cell4 = row4.createCell(1);
            cell4.setCellValue("name");
            cell4.setCellStyle(headerCellStyle);

            // Creating data rows for each customer
            for(int i = 0; i < colors.size(); i++) {
                Row dataRow = colorSheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(colors.get(i).getId());
                dataRow.createCell(1).setCellValue(colors.get(i).getName());
            }
            // Making size of column auto resize to fit with data
            colorSheet.autoSizeColumn(0);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
