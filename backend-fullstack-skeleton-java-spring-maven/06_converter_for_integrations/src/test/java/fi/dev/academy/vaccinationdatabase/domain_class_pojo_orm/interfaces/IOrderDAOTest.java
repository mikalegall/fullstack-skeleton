package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

import fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IOrderDAOTest {

    @Autowired
    private IOrderDAO orderRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Order test = new Order();
        test.setOrderedAmpuleBottleId("Kombuza");
        test.setResponsiblePerson("Mika Le Gall");
        orderRepository.save(test);
    }

    @Test
    void findByOrderedAmpuleBottleId() {
        List<Order> order = orderRepository.findByOrderedAmpuleBottleId("1251aa6c-ebaf-4e33-89d3-d6f210497b94");
        Assertions.assertThat(order.get(0).getResponsiblePerson()).isEqualTo("Tarvo Puro");
    }
}