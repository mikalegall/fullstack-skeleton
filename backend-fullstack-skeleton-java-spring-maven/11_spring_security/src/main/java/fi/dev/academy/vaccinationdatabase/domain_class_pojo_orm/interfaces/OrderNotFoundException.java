package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm.interfaces;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}