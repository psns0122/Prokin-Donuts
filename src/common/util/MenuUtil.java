import java.util.Map;
import java.util.Optional;


public class MenuUtil {

    // 메뉴 번호와 실행할 액션(Runnable)을 Map으로 등록하고, 한 줄의 호출로 메뉴 선택 및 액션 실행을 처리
    public static void handleMenuSelection(String prompt, Map<Integer, Runnable> menuActions) {
        Optional<Integer> selection = InputUtil.getMenuSelection(prompt);
        if (!selection.isPresent()) {
            System.out.println("전 메뉴로");
            return;
        }
        Runnable action = menuActions.get(selection.get());
        if (action == null) {
            System.out.println("없는 메뉴");
        } else {
            action.run();
        }
    }
}