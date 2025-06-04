package resources;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;

public class excelUtilities extends utilities {


    public static void main(String[] args) throws Exception {
        excelUtilities eu = new excelUtilities();
        ArrayList data = eu.getData("data", "Testdata", "TD1");
        System.out.println(data);
    }


    public ArrayList getData(String sheetname, String rowname, String columnname) throws Exception {

    ArrayList data = new ArrayList();

    FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\testData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fs);
    int numberOfSheets = workbook.getNumberOfSheets();
    //print(numberOfSheets);
    for(int i=0;i<numberOfSheets;i++){
        XSSFSheet sheet = workbook.getSheetAt(i);
        // Sheet is a collection of rows
       // print("Sheet Name: " + sheet.getSheetName());
        if (sheet.getSheetName().equalsIgnoreCase(sheetname)){
            Iterator<Row> rows = sheet.iterator();
            // Rows are a collection of cells
            Row firstrow = rows.next();
            Iterator<Cell> cell = firstrow.cellIterator();
            int k = 0;
            int column = 0;
            while (cell.hasNext()){
                Cell value = cell.next();
                if (value.getStringCellValue().equalsIgnoreCase(rowname)){
                    column = k;
                }
                k++;
            }
          //  print(column);
            while (rows.hasNext()){
                Row r = rows.next();
                if(r.getCell(column).getStringCellValue().equalsIgnoreCase(columnname)){
                    Iterator<Cell> c = r.cellIterator();
                    while (c.hasNext()){
                        Cell cv = c.next();
                      // print(c.next().getStringCellValue());
                        if(cv.getCellType()== CellType.STRING){
                            print("String Cell Value: " + cv.getStringCellValue());
                            data.add(cv.getStringCellValue());
                        } else  {
                            data.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
                            print("Numeric Cell Value: " + cv.getNumericCellValue());


                       // print(data);
                    }
                }
            }

        }
    }


    }

        return data;
    } }


