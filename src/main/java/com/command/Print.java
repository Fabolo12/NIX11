package com.command;

import com.model.product.Phone;
import com.model.product.ProductType;
import com.service.PhoneService;
import com.service.ProductService;
import com.util.UserInputUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Print implements Command {

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();

    @Override
    public void execute() {
        System.out.println("What do you want to print:");
        final ProductType[] values = ProductType.values();
        final List<String> names = Arrays.stream(values)
                .map(Enum::name)
                .collect(Collectors.toList());
        final int userInput = UserInputUtil.getUserInput(names);

        switch (values[userInput]) {
            case PHONE -> PHONE_SERVICE.printAll();
            default -> {
                throw new IllegalArgumentException("Unknown ProductType " + values[userInput]);
            }
        }
    }
}
