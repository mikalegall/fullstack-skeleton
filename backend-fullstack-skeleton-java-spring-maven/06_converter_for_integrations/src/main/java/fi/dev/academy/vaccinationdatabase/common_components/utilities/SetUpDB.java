package fi.dev.academy.vaccinationdatabase.common_components.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IOrderDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetUpDB {
    private static final Logger log = LoggerFactory.getLogger(SetUpDB.class);

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
    @Autowired
    private IOrderDAO orderRepository;


    public void initDB() throws JsonProcessingException {

        List<Order> orders = null;

        ReadFiles readFiles = new ReadFiles();

        orders = readFiles.readOrders();
        if (orders != null) {
            log.info("Start to save order ORMs from JSON order files into PostreSQL");
            for (Order order : orders) {
                orderRepository.save(order);
            }
            log.info("Ready, all order ORMs saved into PostreSQL");
        } else {
            log.info("No orders to process");
        }

    }

}