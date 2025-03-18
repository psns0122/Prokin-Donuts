package common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


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

    // 정수 입력 받기
    public static int getIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("올바른 정수를 입력하세요.");
            }
        }
    }

    /**
     * 날짜 받기
     * -> DATE_FORMATTER 사용 추후 확인 필요 !
     * @param prompt
     * @return
     */
    public static LocalDate getDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (NumberFormatException e) {
                System.out.println("올바른 날짜를 입력하세요.");
            }
        }
    }
}