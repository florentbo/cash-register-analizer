package be.florentbo.register.controller;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExcelBuilder extends AbstractExcelView {

    private final Map<String, Integer> report;

    protected ExcelBuilder(Map<String, Integer> report) {
        this.report = report;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        new WorkbookBuilder().put(this.report).in(workbook).build();
        response.setHeader("Content-Disposition", "attachment; filename=\"report.xls\"");
    }

    public static class WorkbookBuilder {
        private Map<String, Integer> report;
        private HSSFWorkbook workbook;

        public WorkbookBuilder put(Map<String, Integer> report) {
            this.report = report;
            return this;
        }

        public WorkbookBuilder in(HSSFWorkbook workbook) {
            this.workbook = workbook;
            return this;
        }

        public void build(){
            HSSFSheet sheet = workbook.createSheet("Java Books");
            sheet.setDefaultColumnWidth(30);

            // create style for header cells
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontName("Arial");
            style.setFillForegroundColor(HSSFColor.BLUE.index);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setColor(HSSFColor.WHITE.index);
            style.setFont(font);

            // create header row
            HSSFRow header = sheet.createRow(0);

            header.createCell(0).setCellValue("Article");
            header.getCell(0).setCellStyle(style);

            header.createCell(1).setCellValue("Quantity");
            header.getCell(1).setCellStyle(style);

            MutableNonSafeInt rowCount = new MutableNonSafeInt();

            report.entrySet().forEach((e) -> {
                HSSFRow aRow = sheet.createRow(rowCount.get());
                aRow.createCell(0).setCellValue(e.getKey());
                aRow.createCell(1).setCellValue(e.getValue());
            });

        }

        static class MutableNonSafeInt {
            private int i = 1;

            public int get() {
                return i++;
            }
        }
    }
}