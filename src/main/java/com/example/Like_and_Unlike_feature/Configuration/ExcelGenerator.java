package com.example.Like_and_Unlike_feature.Configuration;

import com.example.Like_and_Unlike_feature.Model.Favourites;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator
{
    public static ByteArrayInputStream FavouritesToExcel(List<Favourites> FavouritesList) throws IOException
    {
        String[] columns = {"favouriteStatus","fromCoin","toCoin"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            Sheet sheet = workbook.createSheet("Favourites");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            //Row for Header-->
            Row headerRow = sheet.createRow(0);

            //Header
            for(int col=0; col<columns.length; col++)
            {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx =1;
            for(Favourites fav: FavouritesList)
            {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(fav.getFavouriteStatus());
                row.createCell(1).setCellValue(fav.getFromCoin());
                row.createCell(2).setCellValue(fav.getToCoin());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}

