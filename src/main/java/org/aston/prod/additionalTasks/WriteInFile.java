package org.aston.prod.additionalTasks;

import org.aston.prod.model.Student;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.SequenceWriter;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
/**
 * Утилитарный класс для записи списка объектов {@link Student} в файл формата JSONL.
 * <p>
 * Класс предоставляет метод для сериализации списка студентов в формат JSONL (JSON Lines),
 * где каждый объект записывается на отдельной строке. При записи в существующий файл
 * добавляется разделитель строк перед новыми данными.
 */
public class WriteInFile {
    /**
     * Записывает список студентов в файл формата JSONL (каждая запись — на отдельной строке).
     * Если файл существует и не пуст, перед новыми данными добавляется разделитель строк.
     * Файл создаётся в директории src/main/resources/ с расширением .jsonl.
     *
     * @param studentList список объектов Student для записи (может быть пустым, но не null)
     * @param fileName имя файла без расширения (расширение .jsonl добавится автоматически)
     *
     * @throws IOException при ошибках ввода‑вывода (доступ, диск и т. д.)
     * @throws JacksonException при ошибках сериализации объектов Student в JSON
     *
     * @example
     * WriteInFile writer = new WriteInFile();
     * writer.writeInFile(students, "myStudents");
     * // Создаст файл src/main/resources/myStudents.jsonl
     */
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
        } catch (IOException | JacksonException e) {
            System.out.println("В процессе записи возникла ошибка " + e.getMessage());
            System.out.println("Повторите попытку записи еще раз");
        }
    }
}
