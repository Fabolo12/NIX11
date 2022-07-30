package com.command;

import com.model.product.Phone;
import com.model.product.ProductType;
import com.model.product.TV;
import com.service.PhoneService;
import com.service.ProductService;
import com.service.TVService;
import com.util.UserInputUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Update implements Command {

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = TVService.getInstance();

    @Override
    public void execute() {
        System.out.println("What do you want to update:");

        final ProductType[] values = ProductType.values();
        final List<String> names = Arrays.stream(values)
                .map(Enum::name)
                .collect(Collectors.toList());

        final int userInput = UserInputUtil.getUserInput(names);

        switch (values[userInput]) {
            case PHONE -> updatePhone();
            case TV -> updateTV();
            default -> throw new IllegalStateException("Illegal argument " + values[userInput]);
        }
    }

    private void updatePhone() {
        // TODO: 25/07/22
    }

    private void updateTV() {
        // TODO: 25/07/22
    }
}
