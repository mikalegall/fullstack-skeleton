package fi.dev.academy.vaccinationdatabase.common.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IOrderDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IVaccinationDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.vaccination.Vaccination;
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
    @Autowired
    private IVaccinationDAO vaccinationRepository;


    public void initDB() throws JsonProcessingException {

        List<Order> orders = null;
        List<Vaccination> vaccinations = null;

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


        vaccinations = readFiles.readVaccinations();
        if (vaccinations != null) {
            log.info("Start to map vaccinations into orders and then save vaccination ORMs from JSON file into PostreSQL");

            for (Vaccination vaccination : vaccinations) {

                // Create DataBase relation @ManyToOne
                // Get the order where this vaccination belongs to
                List<Order> linkedOrder = orderRepository.findByOrderedAmpuleBottleId(vaccination.getSourceBottle());

                if (linkedOrder != null) {
                    // In this vaccination set the Order-object where this vaccination belongs to)
                    vaccination.setWhichOrderIncludesTheseVaccinationsLinked(linkedOrder.get(0));
                } else {
                    Order tempOrder = new Order();
                    tempOrder.setResponsiblePerson("No order for this 'Ordered Ampule Bottle Id' aka 'Source Bottle'");
                    vaccination.setWhichOrderIncludesTheseVaccinationsLinked(tempOrder);
                }
                vaccinationRepository.save(vaccination);

            }
            log.info("Ready, all vaccination ORMs saved into PostreSQL");

        } else {
            log.info("No vaccinations to process");
        }

    }

}