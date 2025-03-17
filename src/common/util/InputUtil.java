import java.util.Optional;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    // exit 입력 시 Optional.empty() 반환
    public static Optional<String> getInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if ("exit".equalsIgnoreCase(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    // 숫자 입력만 허용하여 Optional<Integer> 반환, exit 입력 시 즉시 Optional.empty()
    public static Optional<Integer> getMenuSelection(String prompt) {
        while (true) {
            Optional<String> inputOptional = getInput(prompt);
            if (!inputOptional.isPresent()) {
                return Optional.empty();
            }
            String input = inputOptional.get();
            try {
                int menuNumber = Integer.parseInt(input);
                return Optional.of(menuNumber);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력");
            }
        }
    }
}