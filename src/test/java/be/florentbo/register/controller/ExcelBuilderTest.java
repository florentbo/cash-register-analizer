package be.florentbo.register.controller;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class ExcelBuilderTest {

    private static final String LABEL_1 = "Label 1";
    private static final String LABEL_2 = "Label 2";
    private static final double QUANTITY_1 = 99.0;
    private static final double QUANTITY_2 = 33.0;

    @Test
    public void testBuildExcelDocument() throws Exception {

        HSSFWorkbook workbook = createWorkbook();
        HSSFSheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getRow(1).getCell(0).getStringCellValue()).isEqualTo(LABEL_1);
        assertThat(sheet.getRow(1).getCell(1).getNumericCellValue()).isEqualTo(QUANTITY_1);
        assertThat(sheet.getRow(2).getCell(0).getStringCellValue()).isEqualTo(LABEL_2);
        assertThat(sheet.getRow(2).getCell(1).getNumericCellValue()).isEqualTo(QUANTITY_2);

    }

    private static HSSFWorkbook createWorkbook() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HashMap<String, Integer> report = new HashMap<String, Integer>() {
            {
                put(LABEL_1, (int) QUANTITY_1);
                put(LABEL_2, (int) QUANTITY_2);
            }
        };

        ExcelBuilder builder = new ExcelBuilder(report);
        Map<String, Object> model = null;
        HttpServletRequest req = null;
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        builder.buildExcelDocument(model, workbook, req, resp);
        return workbook;
    }

    public static void main(String[] args) throws Exception {
        File temp = File.createTempFile("workbook", ".xls");

        FileOutputStream fileOut = new FileOutputStream(temp);
        createWorkbook().write(fileOut);
        fileOut.close();

        DesktopApi.open(temp);
    }
}
