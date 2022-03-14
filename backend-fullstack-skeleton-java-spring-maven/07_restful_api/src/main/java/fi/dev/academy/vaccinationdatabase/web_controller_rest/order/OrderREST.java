package fi.dev.academy.vaccinationdatabase.web_controller_rest.order;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces.IOrderDAO;
import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderREST {
    private static final Logger log = LoggerFactory.getLogger(OrderREST.class);

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
    @Autowired
    private IOrderDAO orderRepository;

    // REST Crud: CREATE
    @PostMapping("/order")
    public void addOneOrder(@RequestBody fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order order) {

        log.info("addOneOrder() Someone poked POST /order " + order);
        orderRepository.save(order);
    }


    // REST cRud: READ
    @GetMapping("/orders")
    public Iterable<fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order> getAllOrders() {
        log.info("getAllOrders() Someone poked GET /orders");
        return orderRepository.findAll(); // Remote Procedure Call (RPC) way to do things
    }

    @GetMapping("/order/{id}")
    public Optional<Order> getOneOrderById(@PathVariable String id) {

        log.info("getOneOrderById() Someone poked GET /order/{id} " + id);

        return orderRepository.findById(Long.parseLong(id));
    }


    // REST crUd: UPDATE
    @PutMapping("/order/{id}")
    public void updateOneOrder(@RequestBody fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order orderRequest, @PathVariable Long id) {

        log.info("updateOneOrder() Someone poked PUT /order/{id} " + id + orderRequest);

        fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order updatedOrder = orderRepository.findById(id)
                .map(obj -> {
                    obj.setOrderedAmpuleBottleId(orderRequest.getOrderedAmpuleBottleId());
                    obj.setHealthCareDistrict(orderRequest.getHealthCareDistrict());
                    obj.setOrderNumber(orderRequest.getOrderNumber());
                    obj.setResponsiblePerson(orderRequest.getResponsiblePerson());
                    obj.setInjections(orderRequest.getInjections());
                    obj.setArrived(orderRequest.getArrived());
                    obj.setVaccine(orderRequest.getVaccine());
                    return orderRepository.save(obj);
                })
                .orElseGet(() -> {
                    return orderRepository.save(orderRequest);
                });
    }


    // REST cruD: DELETE
    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOneOrder(@PathVariable String id) {

        log.info("deleteOneOrder() Someone poked DELETE /order/{id} " + id);

        orderRepository.deleteById(Long.parseLong(id));

        return ResponseEntity.noContent().build();
    }

}