package com.mark.backend.utils;

import com.mark.backend.dto.RemarkDto;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liming
 */
public class RemarkDataExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map,
                                      HSSFWorkbook hssfWorkbook,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("application/msexcel");

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"remark.xls\"");


        List<RemarkDto> remarkData = (ArrayList<RemarkDto>) map.get("remarkData");

        ExcelUtils.generateRemarkExcel(hssfWorkbook, remarkData);

    }
}
