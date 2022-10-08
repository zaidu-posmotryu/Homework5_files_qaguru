package files.dasha;

import com.fasterxml.jackson.databind.DeserializationFeature;
import files.dasha.model.StudentEvaluation;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;


public class JsonTest {

   String jsonFile = "StudentEvaluation.json";

    @Test
    void JsonTestWithModel() throws IOException {
        File file = new File("src/test/resources/StudentEvaluation.json");
        ObjectMapper mapper  = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StudentEvaluation studentEvaluation = mapper.readValue(file, StudentEvaluation.class);
        assertThat(studentEvaluation.familyName).isEqualTo("Ivanov");
        assertThat(studentEvaluation.firstName).isEqualTo("Ivan");
        assertThat(studentEvaluation.intraMural).isEqualTo(true);
        assertThat(studentEvaluation.groupCode).isEqualTo(113);
        assertThat(studentEvaluation.coursesTaken[1]).isEqualTo("Logistics");
        assertThat(studentEvaluation.grades[1]).isEqualTo("3");
    }
}