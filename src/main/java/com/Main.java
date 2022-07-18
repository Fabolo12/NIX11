package com;

import com.command.Command;
import com.command.Commands;
import com.repository.PhoneRepository;
import com.service.OptionalExamples;
import com.service.OrderService;

import java.util.Scanner;


public class Main {
    private static final OptionalExamples OPTIONAL_EXAMPLES = new OptionalExamples(PhoneRepository.getInstance());
    private static final OrderService ORDER_SERVICE = new OrderService();

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final Commands[] values = Commands.values();
        boolean exit;

        do {
            exit = userAction(values);
        } while (!exit);
    }

    private static boolean userAction(final Commands[] values) {
        int userCommand = -1;
        do {
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i].getName());
            }
            int input = SCANNER.nextInt();
            if (input >= 0 && input < values.length) {
                userCommand = input;
            }
        } while (userCommand == -1);
        final Command command = values[userCommand].getCommand();
        if (command == null) {
            return true;
        } else {
            command.execute();
            return false;
        }
    }

    /*private static void orderExamples() {
        TV_SERVICE.createAndSave(2);
        final List<TV> tvs = TV_SERVICE.getAll();
        final Order order = ORDER_SERVICE.creatOrder(tvs);
        System.out.println(order);

        System.out.println("~".repeat(5));

        final List<Phone> phones = PHONE_SERVICE.getAll();
        ORDER_SERVICE.addProducts(order, phones);
        System.out.println(order);

        System.out.println("~".repeat(5));

        final Product remove = ORDER_SERVICE.remove(order, 1);
        System.out.println(order);

        System.out.println("~".repeat(5));

        ORDER_SERVICE.setProduct(order, 0, remove);
        System.out.println(order);

        System.out.println("~".repeat(5));

        ORDER_SERVICE.addProduct(order, 0, remove);
        System.out.println(order);
    }

    private static void optionalExamples() {
        PHONE_SERVICE.createAndSave(1);
        final String id = PHONE_SERVICE.getAll().get(0).getId();

        OPTIONAL_EXAMPLES.printIfPresent(id);
        OPTIONAL_EXAMPLES.printOrGetDefault(id);
        OPTIONAL_EXAMPLES.printOrCreatDefault(id);
        OPTIONAL_EXAMPLES.mapPhoneToString(id);
        OPTIONAL_EXAMPLES.printOrPrintDefault(id);
        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 1000);
        OPTIONAL_EXAMPLES.checksPhoneLessThen(id, 10);
        OPTIONAL_EXAMPLES.checksPhoneLessThen("123", 1000);
        try {
            OPTIONAL_EXAMPLES.printPhoneOrElseThrowException(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        OPTIONAL_EXAMPLES.printPhone(id);
    }*/
}