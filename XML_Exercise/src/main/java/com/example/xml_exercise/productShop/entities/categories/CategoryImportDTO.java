package com.example.xml_exercise.productShop.entities.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {
    @XmlElementWrapper(name = "categories")
    private List<CategoryNameDTO> categories;

    public CategoryImportDTO() {
    }

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }
}
