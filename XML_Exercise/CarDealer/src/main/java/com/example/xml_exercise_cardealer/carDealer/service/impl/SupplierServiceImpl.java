package com.example.xml_exercise_cardealer.carDealer.service.impl;

import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.ExportLocalSupplierDTO;
import com.example.xml_exercise_cardealer.carDealer.entities.suppliers.ExportLocalSupplierWrapperDTO;
import com.example.xml_exercise_cardealer.carDealer.repositories.SupplierRepository;
import com.example.xml_exercise_cardealer.carDealer.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private ModelMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.mapper = new ModelMapper ();
    }

    @Override
    public ExportLocalSupplierWrapperDTO getAllLocalSuppliers() {

        List<ExportLocalSupplierDTO> suppliers = this.supplierRepository.getLocalSupplierPartCount ();

        return new ExportLocalSupplierWrapperDTO (suppliers);
    }
}
