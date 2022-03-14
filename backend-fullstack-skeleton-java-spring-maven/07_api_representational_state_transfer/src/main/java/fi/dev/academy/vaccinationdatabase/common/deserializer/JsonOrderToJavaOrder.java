package fi.dev.academy.vaccinationdatabase.common.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonOrderToJavaOrder extends StdDeserializer<Order> {

    public JsonOrderToJavaOrder() {
        this(null);
    }

    public JsonOrderToJavaOrder(Class<?> vc) {
        super(vc);
    }

    @Override
    public Order deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Order order = new Order();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode ordered_ampule_bottle_idNode = node.get("id");
        String ordered_ampule_bottle_id = ordered_ampule_bottle_idNode.asText();
        order.setOrderedAmpuleBottleId(ordered_ampule_bottle_id);

        JsonNode responsiblePersonNode = node.get("responsiblePerson");
        String responsible_person = responsiblePersonNode.asText();
        order.setResponsiblePerson(responsible_person);

        JsonNode healthCareDistrictNode = node.get("healthCareDistrict");
        String health_care_district = healthCareDistrictNode.asText();
        order.setHealthCareDistrict(health_care_district);

        JsonNode vaccineNode = node.get("vaccine");
        String vaccine = vaccineNode.asText();
        order.setVaccine(vaccine);

        JsonNode orderNumberNode = node.get("orderNumber");
        int order_number = orderNumberNode.asInt();
        order.setOrderNumber(order_number);

        JsonNode injectionsNode = node.get("injections");
        int injections = injectionsNode.asInt();
        order.setInjections(injections);

        JsonNode orderArrivedNode = node.get("arrived");
        String orderArrivedString = orderArrivedNode.asText();
        // https://www.programcreek.com/java-api-examples/?class=java.time.format.DateTimeFormatter&method=ISO_DATE_TIME
        LocalDateTime orderArrived = LocalDateTime.parse(orderArrivedString, DateTimeFormatter.ISO_DATE_TIME);
        order.setArrived(orderArrived);

        return order;
    }
}