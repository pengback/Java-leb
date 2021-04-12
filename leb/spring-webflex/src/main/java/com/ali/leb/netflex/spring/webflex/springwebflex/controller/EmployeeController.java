package com.ali.leb.netflex.spring.webflex.springwebflex.controller;

import com.ali.leb.netflex.spring.webflex.springwebflex.bean.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: aliber
 * @Date: 2020/12/3 下午11:55
 **/
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @GetMapping(value = "employees", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1));
        list.add(new Employee(2));
        list.add(new Employee(3));
        list.add(new Employee(4));
        list.add(new Employee(5));
        return Flux.range(1, 5).map(val -> {
            return list.stream().filter(employee -> employee.getId() == val).findFirst().get();
        }).delayElements(Duration.ofMillis(1000));
    }

}
