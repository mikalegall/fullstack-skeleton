package fi.dev.academy.vaccinationdatabase.common.basemodel;

// Enum is like 'final variable' and Status is (in this case) somewhat comparable to JavaScript 'promise' for asynchronous operators
public enum Status {
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
// Get inspired
// https://github.com/spring-projects/spring-hateoas-examples/tree/main/spring-hateoas-and-spring-data-rest