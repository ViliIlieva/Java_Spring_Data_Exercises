package com.example.xml_exercise.productShop.entities.users;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportSellersDTO {

    @XmlElement(name = "user")
    List<ExportUserWithSoldProductsDTO> users;

    public ExportSellersDTO(){}

    public ExportSellersDTO(List<ExportUserWithSoldProductsDTO> users) {
        this.users = users;
    }

    public void setUsers(List<ExportUserWithSoldProductsDTO> users) {
        this.users = users;
    }
}
