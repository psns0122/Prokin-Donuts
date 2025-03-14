import config.DiConfig;
import controller.MainController;

public class Main {
    public static void main(String[] args) {
        DiConfig diConfig = new DiConfig();
        MainController mainController = diConfig.mainController();
        mainController.run();
    }
}