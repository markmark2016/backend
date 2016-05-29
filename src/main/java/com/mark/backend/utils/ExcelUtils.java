package com.mark.backend.utils;

import com.mark.backend.dto.RemarkDto;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author liming
 */
public class ExcelUtils {

    public static String[] remarkExcelTitle = new String[]{
            "书评ID",
            "用户ID",
            "昵称",
            "时间",
            "标题",
            "内容",
            "评论个数",
            "点赞个数"
    };

    public static void generateRemarkExcel(HSSFWorkbook workBook, List<RemarkDto> remarkData) {


        Collections.sort(remarkData, new Comparator<RemarkDto>() {
            @Override
            public int compare(RemarkDto o1, RemarkDto o2) {
                return o1.getRemarkId().compareTo(o2.getRemarkId());
            }
        });

        Sheet sheet = workBook.createSheet("书评");
        Row row = sheet.createRow(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // create style for header cells
        CellStyle style = workBook.createCellStyle();
        Font font = workBook.createFont();
        font.setFontName("Microsoft YaHei");
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);

        for (int i = 0; i < remarkExcelTitle.length; i++) {
            row.createCell(i).setCellValue(remarkExcelTitle[i]);
            row.getCell(i).setCellStyle(style);
        }
        for (int j = 0; j < remarkData.size(); j++) {
            RemarkDto remark = remarkData.get(j);
            Row recordRow = sheet.createRow(j + 1);
            recordRow.createCell(0).setCellValue(remark.getRemarkId());
            recordRow.getCell(0).setCellStyle(style);
            recordRow.createCell(1).setCellValue(remark.getUserId());
            recordRow.getCell(1).setCellStyle(style);
            recordRow.createCell(2).setCellValue(remark.getUserName());
            recordRow.getCell(2).setCellStyle(style);
            recordRow.createCell(3).setCellValue(sdf.format(remark.getCreateTime()));
            recordRow.getCell(3).setCellStyle(style);
            recordRow.createCell(4).setCellValue(remark.getTitle());
            recordRow.getCell(4).setCellStyle(style);
            recordRow.createCell(5).setCellValue(remark.getComment());
            recordRow.getCell(5).setCellStyle(style);
            recordRow.createCell(6).setCellValue(remark.getTotalReply());
            recordRow.getCell(6).setCellStyle(style);
            recordRow.createCell(7).setCellValue(remark.getTotalLike());
            recordRow.getCell(7).setCellStyle(style);
        }
    }
}
