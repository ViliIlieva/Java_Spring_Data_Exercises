package com.example.xml_exercise_cardealer.carDealer.entities.sales;

import com.example.xml_exercise_cardealer.carDealer.entities.cars.Car;
import com.example.xml_exercise_cardealer.carDealer.entities.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleImportDTO {

    private Customer customer;
    private Car car;
    private BigDecimal discount;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDiscount(BigDecimal discount){
        if(this.getCustomer ().isYoungDriver ()){
            this.discount = discount.add (BigDecimal.valueOf (0.05));
        }else {
            this.discount = discount;
        }
    }
}
