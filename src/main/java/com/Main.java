package

import com.command.Commands;
import com.repository.PhoneRepository;
import com.service.OptionalExamples;
import com.service.OrderService;
import com.util.UserInputUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main {
    private static final OptionalExamples OPTIONAL_EXAMPLES = new OptionalExamples(PhoneRepository.getInstance());
    private static final OrderService ORDER_SERVICE = new OrderService();

    private static final Predicate<List<Integer>> IS_VALID =
            integers -> integers != null && !integers.isEmpty() && integers.contains(1);

    private static final BiPredicate<List<Integer>, Integer> IS_VALID_SIZE = (list, size) -> list.size() >= size;

    public static void main(String[] args) {
        final Commands[] values = Commands.values();
        boolean notExit;

        do {
            notExit = userAction(values);
        } while (notExit);
    }

    private static boolean userAction(final Commands[] values) {
        final List<String> names = Arrays.stream(values)
                .map(Enum::name)
                .collect(Collectors.toList());
        final int userInput = UserInputUtil.getUserInput(names);
        return values[userInput].execute();
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