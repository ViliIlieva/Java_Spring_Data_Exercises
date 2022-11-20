package com.example.xml_exercise.productShop.entities.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {
    @XmlElement(name = "category")
    private List<CategoryNameDTO> categories;

    public CategoryImportDTO() {
    }

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }
}
