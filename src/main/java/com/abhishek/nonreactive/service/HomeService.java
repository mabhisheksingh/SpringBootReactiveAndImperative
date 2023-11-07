package com.abhishek.nonreactive.service;

import com.abhishek.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeService {

    private List<Employee> employeeList = Arrays.asList(
      new Employee(1,"Abhishek Singh")    ,
      new Employee(2,"Ashish Singh") ,
      new Employee(3,"Shivam Singh")  ,
      new Employee(4,"test Singh")
    );

    public Mono<List<Employee>> getAllEmp(){
        return Mono.just(employeeList);
    }
    public Mono<Employee> getEmpById(Integer id){
        return Mono.just( employeeList.stream().filter( emp -> emp.getId() == id).findFirst().get());
    }
    public Mono<Boolean> deleteEmpById(Integer id){
        if( getEmpById(id) != null){
            employeeList.remove(id);
            return Mono.just(true);
        }
        else return Mono.just(false);
    }

}
