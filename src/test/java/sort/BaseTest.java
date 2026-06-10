package sort;

import org.aston.prod.sort.SortStrategy;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest<T extends SortStrategy> {
    protected StudentMockData mock = new StudentMockData();
    protected T sort;
    private final Class<T> clazz;

    public BaseTest(Class<T> clazz) {
        this.clazz = clazz;
    }

    @BeforeEach
    void setUp() {
        sort = createSortStrategy();
    }

    protected T createSortStrategy() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр " + clazz.getSimpleName(), e);
        }
    }
}
