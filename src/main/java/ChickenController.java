import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChickenController {
    private static final List<Table> tables = TableRepository.tables();

    public static void order() {
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();

        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);

        int menuNumber = InputView.inputMenuNumber();
        int menuCount = InputView.inputMenuCount();

        Table table = TableRepository.of(tableNumber);
        Menu menu = MenuRepository.of(menuNumber);

        table.orderMenu(menu, menuCount);
    }

    public static void pay() {
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();

        Table table = TableRepository.of(tableNumber);
        OutputView.printBill(table);

        OutputView.printPayment(table);
        int payment = InputView.inputPayment();

        OutputView.printTotalPrice(table.totalPrice(payment));
        table.leave();
    }
}
