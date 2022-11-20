package com.example.xml_exercise.productShop.services;

import com.example.xml_exercise.productShop.entities.products.Product;
import com.example.xml_exercise.productShop.entities.products.ExportProductsInRangeDTO;
import com.example.xml_exercise.productShop.entities.products.ProductWithAttributesDTO;
import com.example.xml_exercise.productShop.entities.users.User;
import com.example.xml_exercise.productShop.repositories.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ProductWithAttributesDTO> productToDToMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

        this.mapper = new ModelMapper ();
        //от seller User -> firstName + lastName
        Converter<User, String> userToFullNameConverter =
                context -> context.getSource () == null ? null ://ако имам user му използвай цялото име
                context.getSource ().getFullName ();// за да върнеш като резултат Стринг

        TypeMap<Product, ProductWithAttributesDTO> typeMap =
                this.mapper.createTypeMap (Product.class, ProductWithAttributesDTO.class);

        this.productToDToMapping  = typeMap.addMappings (map ->
                map.using (userToFullNameConverter)
                        .map (Product::getSeller, ProductWithAttributesDTO::setSeller));


    }


    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal rangeFrom = BigDecimal.valueOf (from);
        BigDecimal rangeTo = BigDecimal.valueOf (to);

        List<Product> products = this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc (rangeFrom, rangeTo);

        List<ProductWithAttributesDTO>dtos =
                products.stream ()
                        .map (this.productToDToMapping::map)
                        .collect(Collectors.toList());

        return new ExportProductsInRangeDTO (dtos);
    }
}
