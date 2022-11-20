package com.example.xml_exercise.productShop.entities.products;

import com.example.xml_exercise.productShop.entities.categories.CategoryNameDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportDTO {

    @XmlElement(name = "product")
    private List<ProductNameAndPriceDTO> products;

    public ProductsImportDTO(){}

    public List<ProductNameAndPriceDTO> getProducts() {
        return products;
    }
}
