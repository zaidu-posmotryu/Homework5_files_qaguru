package files.dasha;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseJava {
    ClassLoader cl = FileParseJava.class.getClassLoader();
    @Test
    void zipTest() throws Exception {
        InputStream is = cl.getResourceAsStream("Files.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String entryName = entry.getName();
            if (entryName == ("File1.pdf")) {
                try (InputStream is1 = cl.getResourceAsStream(entryName)) {
                    PDF pdf = new PDF(is1);
                    assertThat(pdf.text).contains("Tinkoff PRO/PREMIUM/PRIVATE");
                    System.out.println("PDF is OK");
                }
            }
            else if (entryName == ("File2.xlsx")) {
                try (InputStream is2 = cl.getResourceAsStream(entryName)) {
                    XLS xls = new XLS(is2);
                    assertThat(
                            xls.excel.getSheetAt(0)
                                    .getRow(12)
                                    .getCell(2)
                                    .getStringCellValue()
                    ).isEqualTo("100,0");
                    System.out.println("XLS is OK");
                }
            }
            else if (entryName == ("File3.csv")) {
                try (InputStream is3 = cl.getResourceAsStream(entryName)) {
                    CSVReader reader = new CSVReader(new InputStreamReader(is3));
                    List<String[]> content = reader.readAll();
                    String[] row = content.get(1);
                    assertThat(row[0]).isEqualTo("Russia");
                    assertThat(row[1]).isEqualTo("per day");
                    System.out.println("CSV is OK");
                }
            }
        }
    }
}