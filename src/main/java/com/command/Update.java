package com.command;

import com.model.product.Phone;
import com.model.product.TV;
import com.repository.TVRepository;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TVService;

public class Update implements Command {

    private static final ProductService<Phone> PHONE_SERVICE =  PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = new TVService(new TVRepository());
    @Override
    public void execute() {
        System.out.println("What do you want to update:");
    }
}
