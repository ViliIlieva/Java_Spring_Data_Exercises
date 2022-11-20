package com.example.xml_exercise.productShop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

//<product name="TRAMADOL HYDROCHLORIDE" price="516.46" seller="Christine Gomez" />
//когато изреждам на един ред са атрибути, когато е на отделни са елементи
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithAttributesDTO {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlAttribute
    private String seller;

    public ProductWithAttributesDTO() {
    }
//мога да използвам конструктора във първото куери, вместо мапъра за от изър към стринг
    public ProductWithAttributesDTO(Product product) {
        this.name = product.getName ();
        this.price = product.getPrice ();
        this.seller = product.getSeller ().getFullName ();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
