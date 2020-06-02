package com.ait.excel;

import com.ait.model.Brand;
import com.ait.model.Color;
import com.ait.model.Customer;
import com.ait.model.Province;
import com.ait.model.Vehicle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DataExport {

    public static ByteArrayInputStream listToExcelFile(List<Customer> customers, List<Vehicle> vehicles,
                                                       List<Province> provinces, List<Brand> brands,
                                                       List<Color> colors) {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet customerSheet = workbook.createSheet("Customers");
            Sheet vehicleSheet = workbook.createSheet("Vehicles");
            Sheet provinceSheet = workbook.createSheet("Provinces");
            Sheet extraSheet = workbook.createSheet("ExtraData");

            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setItalic(false);
            titleFont.setFontHeightInPoints((short) 20);

            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.BROWN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));


            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCellStyle.setFont(titleFont);

            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd"));


            customerSheet.addMergedRegion(CellRangeAddress.valueOf("C3:H4"));
            Row row = customerSheet.createRow(2);
            Cell cell = row.createCell(2);
            cell.setCellValue("Dữ liệu chủ sở hữu đăng ký xe");
            cell.setCellStyle(titleCellStyle);

            customerSheet.autoSizeColumn(0);


            Row rowCustomer = customerSheet.createRow(5);
            Cell cellCustomer = rowCustomer.createCell(2);
            cellCustomer.setCellValue("ID");
            cellCustomer.setCellStyle(headerCellStyle);

            cellCustomer = rowCustomer.createCell(3);
            cellCustomer.setCellValue("NAME");
            cellCustomer.setCellStyle(headerCellStyle);

            cellCustomer = rowCustomer.createCell(4);
            cellCustomer.setCellValue("ADDRESS");
            cellCustomer.setCellStyle(headerCellStyle);

            cellCustomer = rowCustomer.createCell(5);
            cellCustomer.setCellValue("PHONE");
            cellCustomer.setCellStyle(headerCellStyle);

            cellCustomer = rowCustomer.createCell(6);
            cellCustomer.setCellValue("IDENTITY");
            cellCustomer.setCellStyle(headerCellStyle);

            cellCustomer = rowCustomer.createCell(7);
            cellCustomer.setCellValue("PROVINCE");
            cellCustomer.setCellStyle(headerCellStyle);

            for (int i = 0; i < customers.size(); i++) {
                Row dataRow = customerSheet.createRow(i + 6);
                dataRow.createCell(2).setCellValue(customers.get(i).getId());
                dataRow.createCell(3).setCellValue(customers.get(i).getName());
                dataRow.createCell(4).setCellValue(customers.get(i).getAddress());
                dataRow.createCell(5).setCellValue(customers.get(i).getPhone());
                dataRow.createCell(6).setCellValue(customers.get(i).getIdentity());
                dataRow.createCell(7).setCellValue(customers.get(i).getProvince_name());

            }

            customerSheet.autoSizeColumn(2);
            customerSheet.autoSizeColumn(3);
            customerSheet.autoSizeColumn(4);
            customerSheet.autoSizeColumn(5);
            customerSheet.autoSizeColumn(6);
            customerSheet.autoSizeColumn(7);


            vehicleSheet.addMergedRegion(CellRangeAddress.valueOf("C3:I4"));
            Row rowTitle = vehicleSheet.createRow(2);
            Cell cellTitle = rowTitle.createCell(2);
            cellTitle.setCellValue("Dữ liệu phương tiện đã đăng ký");
            cellTitle.setCellStyle(titleCellStyle);

            vehicleSheet.autoSizeColumn(0);


            Row rowVehicle = vehicleSheet.createRow(5);
            Cell cellVehicle = rowVehicle.createCell(2);
            cellVehicle.setCellValue("ID");
            cellVehicle.setCellStyle(headerCellStyle);

            cellVehicle = rowVehicle.createCell(3);
            cellVehicle.setCellValue("NAME");
            cellVehicle.setCellStyle(headerCellStyle);

            cellVehicle = rowVehicle.createCell(4);
            cellVehicle.setCellValue("CMND CSH");
            cellVehicle.setCellStyle(headerCellStyle);

            cellVehicle = rowVehicle.createCell(5);
            cellVehicle.setCellValue("BRAND");
            cellVehicle.setCellStyle(headerCellStyle);

            cellVehicle = rowVehicle.createCell(6);
            cellVehicle.setCellValue("COLOR");
            cellVehicle.setCellStyle(headerCellStyle);

            cellVehicle = rowVehicle.createCell(7);
            cellVehicle.setCellValue("ENGINE NUMBER");
            cellVehicle.setCellStyle(headerCellStyle);

            cellVehicle = rowVehicle.createCell(8);
            cellVehicle.setCellValue("CHASSIS NUMBER");
            cellVehicle.setCellStyle(headerCellStyle);

            for (int i = 0; i < vehicles.size(); i++) {
                Row dataRow = vehicleSheet.createRow(i + 6);
                dataRow.createCell(2).setCellValue(vehicles.get(i).getId());
                dataRow.createCell(3).setCellValue(vehicles.get(i).getVehicle_name());
                dataRow.createCell(4).setCellValue(vehicles.get(i).getCustomer_identity());
                dataRow.createCell(5).setCellValue(vehicles.get(i).getBrand_name());
                dataRow.createCell(6).setCellValue(vehicles.get(i).getColor_name());
                dataRow.createCell(7).setCellValue(vehicles.get(i).getEngine_num());
                dataRow.createCell(8).setCellValue(vehicles.get(i).getChassis_num());

            }

            vehicleSheet.autoSizeColumn(2);
            vehicleSheet.autoSizeColumn(3);
            vehicleSheet.autoSizeColumn(4);
            vehicleSheet.autoSizeColumn(5);
            vehicleSheet.autoSizeColumn(6);
            vehicleSheet.autoSizeColumn(7);
            vehicleSheet.autoSizeColumn(8);

            provinceSheet.addMergedRegion(CellRangeAddress.valueOf("C3:F4"));
            rowTitle = provinceSheet.createRow(2);
            cellTitle = rowTitle.createCell(2);
            cellTitle.setCellValue("Dữ liệu tỉnh thành");
            cellTitle.setCellStyle(titleCellStyle);

            provinceSheet.autoSizeColumn(0);


            Row rowProvince = provinceSheet.createRow(5);
            Cell cellProvince = rowProvince.createCell(2);
            cellProvince.setCellValue("ID");
            cellProvince.setCellStyle(headerCellStyle);

            cellProvince = rowProvince.createCell(3);
            cellProvince.setCellValue("NAME");
            cellProvince.setCellStyle(headerCellStyle);

            cellProvince = rowProvince.createCell(4);
            cellProvince.setCellValue("PROVINCE CODE");
            cellProvince.setCellStyle(headerCellStyle);

            cellProvince = rowProvince.createCell(5);
            cellProvince.setCellValue("PHONE CODE");
            cellProvince.setCellStyle(headerCellStyle);

            for (int i = 0; i < provinces.size(); i++) {
                Row dataRow = provinceSheet.createRow(i + 6);
                dataRow.createCell(2).setCellValue(provinces.get(i).getId());
                dataRow.createCell(3).setCellValue(provinces.get(i).getName());
                dataRow.createCell(4).setCellValue(provinces.get(i).getProvince_code());
                dataRow.createCell(5).setCellValue(provinces.get(i).getTelephone_code());

            }

            provinceSheet.autoSizeColumn(2);
            provinceSheet.autoSizeColumn(3);
            provinceSheet.autoSizeColumn(4);
            provinceSheet.autoSizeColumn(5);

            extraSheet.addMergedRegion(CellRangeAddress.valueOf("C3:D4"));

            rowTitle = extraSheet.createRow(2);
            cellTitle = rowTitle.createCell(2);
            cellTitle.setCellValue("Dữ liệu hãng xe");
            cellTitle.setCellStyle(titleCellStyle);

            extraSheet.autoSizeColumn(0);

            extraSheet.addMergedRegion(CellRangeAddress.valueOf("G3:H4"));
            cellTitle = rowTitle.createCell(6);
            cellTitle.setCellValue("Dữ liệu màu xe");
            cellTitle.setCellStyle(titleCellStyle);

            Row rowExtra = extraSheet.createRow(5);
            Cell cellExtra = rowExtra.createCell(2);
            cellExtra.setCellValue("ID");
            cellExtra.setCellStyle(headerCellStyle);

            cellExtra = rowExtra.createCell(3);
            cellExtra.setCellValue("NAME");
            cellExtra.setCellStyle(headerCellStyle);

            cellExtra = rowExtra.createCell(6);
            cellExtra.setCellValue("ID");
            cellExtra.setCellStyle(headerCellStyle);

            cellExtra = rowExtra.createCell(7);
            cellExtra.setCellValue("NAME");
            cellExtra.setCellStyle(headerCellStyle);

            for (int i = 0; (i < brands.size() && i < colors.size()); i++) {
                Row dataRow = extraSheet.createRow(i + 6);
                dataRow.createCell(2).setCellValue(brands.get(i).getId());
                dataRow.createCell(3).setCellValue(brands.get(i).getName());
                dataRow.createCell(6).setCellValue(colors.get(i).getId());
                dataRow.createCell(7).setCellValue(colors.get(i).getName());
            }


            extraSheet.setColumnWidth(2, 2000);
            extraSheet.setColumnWidth(3, 5000);
            extraSheet.setColumnWidth(6, 2000);
            extraSheet.setColumnWidth(7, 5000);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
