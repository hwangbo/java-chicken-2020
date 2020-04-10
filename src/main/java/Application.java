import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        int command = 0;
        while (command != 3) {
            OutputView.printCommand();
            command = InputView.inputCommand();
            checkCommand(command);
        }
    }

    private static void checkCommand(int command) {
        if (command < 1 || command > 3) {
            throw new IllegalArgumentException("기능의 번호를 잘못 입력하였습니다.");
        } else if (command == 1) {
            ChickenController.order();
            return;
        } else if (command == 2) {
            ChickenController.pay();
            return;
        }
        OutputView.printEndMessage();
    }
}
