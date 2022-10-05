package files.dasha;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
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
            assertThat(entry.getName()).isEqualTo("File1.pdf");
            try (InputStream is1 = cl.getResourceAsStream(entryName)) {
                PDF pdf = new PDF(is1);
                assertThat(pdf.text).contains("Tinkoff PRO/PREMIUM/PRIVATE");

            }
        }
    }

    @Test
    void pdfTest() throws Exception {
        InputStream is = cl.getResourceAsStream("File1.pdf");
        PDF pdf = new PDF(is);
        assertThat(pdf.text).contains("Tinkoff PRO/PREMIUM/PRIVATE");

    }
}


