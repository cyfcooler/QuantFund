import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by yifeirock on 6/7/17.
 */
public class Utils {

    public static void highlightCells(String[] args, IndexedColors foregroundColor)  throws Exception {

        if (args.length < 2) {
            System.err.println("Usage: must pass <funds xlsx file> and <bought funds list>");
            System.exit(1);
        }

        InputStream inp = new FileInputStream(args[0]);
        DataFormatter formatter = new DataFormatter();

        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = getLastSheet(wb);
        System.out.println("Sheet: " + sheet.getSheetName());

        // funds list should be sorted in advance.

        int i = 1;
        Iterator<Row> iter = sheet.rowIterator();

        // skip title row
        iter.next();
        Row row = iter.next();
        while (iter.hasNext() && i < args.length) {
            Cell cell = row.getCell(0); // column A
            if (cell == null) {
                row = iter.next();
                continue;
            }
            String text = formatter.formatCellValue(cell);
            int comp = args[i].compareTo(text);
            if (comp == 0) {
                System.out.println("Got " + text);
                highlightCell(wb, foregroundColor, cell);
                // both i and iter advanced.
                i++;
                row = iter.next();
            } else if (comp < 0) {
                System.err.println("ERROR: fund " + args[i] + " is still not processed.");
                // only i advanced.
                i++;
            } else {
                // only iter advanced.
                row = iter.next();
            }
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(args[0]);
        wb.write(fileOut);
        fileOut.close();
        inp.close();

    }

    private static void highlightCell(Workbook wb, IndexedColors foregroundColor, Cell cell) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(foregroundColor.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        DataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("000000"));
        cell.setCellStyle(style);
    }

    private static Sheet getLastSheet(Workbook wb) {
        int num = wb.getNumberOfSheets();
        return num > 0 ? wb.getSheetAt(num - 1) : null;
    }
}
