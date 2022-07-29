package com.ej;

import com.ej.common.DomainComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(includeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value= {DomainComponent.class})})
@SpringBootApplication
public class HexagonalMusahebeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalMusahebeApplication.class, args);
    }

}
