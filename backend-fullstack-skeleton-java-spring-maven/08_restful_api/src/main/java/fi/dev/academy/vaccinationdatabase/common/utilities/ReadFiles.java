package fi.dev.academy.vaccinationdatabase.common.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fi.dev.academy.vaccinationdatabase.common.deserializer.JsonOrderToJavaOrder;
import fi.dev.academy.vaccinationdatabase.common.deserializer.JsonVaccinationToJavaVaccination;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReadFiles {

    private static final Logger log = LoggerFactory.getLogger(ReadFiles.class);

    public List<Order> readOrders() throws JsonProcessingException {

        StringBuilder strb = new StringBuilder();

        List<String> srcPaths = new ArrayList<>();

        // https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/thl/"))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> srcPaths.add(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // https://www.baeldung.com/jackson-object-mapper-tutorial
        ObjectMapper mapper = new ObjectMapper();

        for (String path : srcPaths) {

            if (!path.equals("src/main/resources/thl/vaccinations.source")) {
                log.info("Read file = " + path);

                try {
                    FileReader readInitImport = new FileReader(path);
                    BufferedReader buffer = new BufferedReader(readInitImport);

                    int readByte;

                    while ((readByte = buffer.read()) != -1) {
                        strb.append((char) readByte);
                    }

                    buffer.close();
                    readInitImport.close();

                    strb.trimToSize();

                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("IOException read file = " + path + " and error = " + e);
                }
            }
        }

        strb.reverse().append("\n[").reverse();
        String input = strb.toString().replace("}", "},");
        // FIXME refactor input.length() into "find last comma"
        String temp = input.substring(0, input.length() - 2) + "\n]";

        SimpleModule module =
                new SimpleModule("CustomOrderDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Order.class, new JsonOrderToJavaOrder());
        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        List<Order> ordersToDB = mapper.readValue(temp, new TypeReference<List<Order>>() {
        });
        log.info("Return object from order files");

        return ordersToDB;
    }

    public List<Vaccination> readVaccinations() throws JsonProcessingException {

        List<Vaccination> vaccinationsToDB = null;

        StringBuilder strb = new StringBuilder();

        try {
            log.info("Read file vaccinations.source");
            FileReader readInitImport = new FileReader("src/main/resources/thl/vaccinations.source");
            BufferedReader buffer = new BufferedReader(readInitImport);

            int readByte;

            while ((readByte = buffer.read()) != -1) {
                strb.append((char) readByte);
            }
            buffer.close();
            readInitImport.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException fooBar", e);
        }

        strb.reverse().append("\n[").reverse();
        String input = strb.toString().replace("}", "},");
        // FIXME refactor input.length() into "find last comma"
        String temp = input.substring(0, input.length() - 2) + "\n]";

        // https://www.baeldung.com/jackson-object-mapper-tutorial
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module =
                new SimpleModule("CustomVaccinationDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Vaccination.class, new JsonVaccinationToJavaVaccination());
        mapper.registerModule(module);

        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        vaccinationsToDB = mapper.readValue(temp, new TypeReference<List<Vaccination>>() {
        });

        log.info("Return object from vaccination file");
        return vaccinationsToDB;
    }
}