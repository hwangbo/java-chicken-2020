package view;

import domain.Menu;
import domain.Table;
import domain.TableRepository;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String BOTTOM_LINE_ORDERED = "└ ₩ ┘";
    private static final String[] COMMANDS = {"주문하기", "결제하기", "프로그램 종료"};

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printBottomLine(BOTTOM_LINE);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    private static void printBottomLine(String bottomLine) {
        for (Table table : TableRepository.tables()) {
            printBottomLineByOrdered(bottomLine, table);
        }
        System.out.println();
    }

    private static void printBottomLineByOrdered(String bottomLine, Table table) {
        if (!table.isEmpty()) {
            bottomLine = BOTTOM_LINE_ORDERED;
        }
        System.out.print(bottomLine);
    }

    public static void printCommand() {
        System.out.println("## 메인화면");
        for (int i = 0; i < COMMANDS.length; i++) {
            System.out.println((i + 1) + " - " + COMMANDS[i]);
        }
        System.out.println();
    }

    public static void printEndMessage() {
        System.out.println("감사합니다! 지금까지 작은곰네 치킨집이었습니다!");
    }

    public static void printBill(Table table) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");
        Map<Menu, Integer> bill = table.bill();
        for (Menu menu : bill.keySet()) {
            System.out.println(menu.getName() + " " + bill.get(menu) + " " + table.menuPrice(menu));
        }
        System.out.println();
    }

    public static void printPayment(Table table) {
        System.out.println(table.toString() + "번 테이블의 결제를 진행합니다.");
    }

    public static void printTotalPrice(int totalPrice) {
        System.out.println("## 최종 결제할 금액");
        System.out.println(totalPrice);
        System.out.println();
    }
}
