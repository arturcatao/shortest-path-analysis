package heaps;

public class ContadorDeOperacoes {
    private static long decreaseKeyCount = 0;

    public static void incrementaDecreaseKey() {
        decreaseKeyCount++;
    }

    public static long getDecreaseKeyCount() {
        return decreaseKeyCount;
    }

    public static void reset() {
        decreaseKeyCount = 0;
    }
}
