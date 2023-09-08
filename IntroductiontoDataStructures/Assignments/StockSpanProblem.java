import java.util.Arrays;

public class StockSpanProblem {

    public static int[] calculateStockSpans(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n];
        spans[0] = 1;

        for (int i = 1; i < n; i++) {
            int span = 1;
            int j = i - 1;

            while (j >= 0 && prices[i] >= prices[j]) {
                span += spans[j];
                j -= spans[j];
            }

            spans[i] = span;
        }

        return spans;
    }

    public static void main(String[] args) {
        StockSpanProblem ssp = new StockSpanProblem();
        //Example input
        System.out.println(Arrays.toString(ssp.calculateStockSpans(new int[]{100, 80, 100, 100, 75})));
    }

}
