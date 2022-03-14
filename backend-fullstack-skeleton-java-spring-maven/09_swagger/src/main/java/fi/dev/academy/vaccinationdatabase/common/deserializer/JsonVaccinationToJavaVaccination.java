package fi.dev.academy.vaccinationdatabase.common.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonVaccinationToJavaVaccination extends StdDeserializer<Vaccination> {

    public JsonVaccinationToJavaVaccination() {
        this(null);
    }

    public JsonVaccinationToJavaVaccination(Class<?> vc) {
        super(vc);
    }

    @Override
    public Vaccination deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Vaccination vaccination = new Vaccination();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode vaccinationIdNode = node.get("vaccination-id");
        String vaccination_id = vaccinationIdNode.asText();
        vaccination.setVaccinationId(vaccination_id);

        JsonNode vaccinationSourceBottleNode = node.get("sourceBottle");
        String vaccination_source_bottle = vaccinationSourceBottleNode.asText();
        vaccination.setSourceBottle(vaccination_source_bottle);

        JsonNode vaccinationGenderNode = node.get("gender");
        String vaccinationGender = vaccinationGenderNode.asText();
        vaccination.setGender(vaccinationGender);

        JsonNode vaccinationVaccinationDateNode = node.get("vaccinationDate");
        String vaccinationVaccinationDateString = vaccinationVaccinationDateNode.asText();
        // https://www.programcreek.com/java-api-examples/?class=java.time.format.DateTimeFormatter&method=ISO_DATE_TIME
        LocalDateTime vaccination_vaccination_date = LocalDateTime.parse(vaccinationVaccinationDateString, DateTimeFormatter.ISO_DATE_TIME);
        vaccination.setVaccinationDate(vaccination_vaccination_date);
        return vaccination;
    }
}