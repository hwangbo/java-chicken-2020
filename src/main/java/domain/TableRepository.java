package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();
    public static final String INVALID_TABLE_NUMBER_ERROR_MESSAGE = "테이블 번호에 맞는 테이블이 없습니다.";

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static Table of(int number) {
        return tables.stream()
                .filter(table -> table.isTableNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_TABLE_NUMBER_ERROR_MESSAGE));
    }
}
