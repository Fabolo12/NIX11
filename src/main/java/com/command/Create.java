package com.command;

import com.model.product.ProductType;
import com.service.ProductFactory;
import com.util.UserInputUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Create implements Command {
    @Override
    public void execute() {
        System.out.println("What do you want to create:");
        final ProductType[] values = ProductType.values();
        final List<String> names = Arrays.stream(values)
                .map(Enum::name)
                .collect(Collectors.toList());

        final int userInput = UserInputUtil.getUserInput(names);
        ProductFactory.createAndSave(values[userInput]);
    }
}
