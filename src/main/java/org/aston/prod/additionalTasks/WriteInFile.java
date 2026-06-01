package org.aston.prod.additionalTasks;

import org.aston.prod.model.Student;
import tools.jackson.databind.SequenceWriter;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WriteInFile {

    public void writeInFile(List<Student> studentList, String fileName) {
        JsonMapper mapper = new JsonMapper();
        Path path = Path.of("src/main/resources/" + fileName + ".jsonl");

        try (OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            if (Files.exists(path) && Files.size(path) > 0)
                outputStream.write(System.lineSeparator().getBytes());
            SequenceWriter sequenceWriter = mapper.writer().withRootValueSeparator("\n").writeValues(outputStream);
            for (Student student : studentList) {
                sequenceWriter.write(student);
            }
            sequenceWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
