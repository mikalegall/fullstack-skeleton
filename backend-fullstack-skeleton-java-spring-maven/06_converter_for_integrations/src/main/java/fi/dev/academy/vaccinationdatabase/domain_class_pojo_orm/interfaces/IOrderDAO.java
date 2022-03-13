package fi.dev.academy.vaccinationdatabase.classess.interfaces;

import fi.dev.academy.vaccinationdatabase.classess.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // JpaRepository
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
public interface IOrderDAO extends CrudRepository<Order, Long> {

    List<Order> findByOrderedAmpuleBottleId(String orderedAmpuleBottleId);
}
