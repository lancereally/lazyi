package lancereally.tanxin;

import java.util.Arrays;
import java.util.Collections;

public class TXSFProblem {
    // 现有的物品
    private Knapsack[] bags;
    // 背包的总承重
    private int totalWeight;
    // 背包最大总价值
    private int bestValue;

    public TXSFProblem(Knapsack[] bags, int totalWeight) {
        this.bags = bags;
        this.totalWeight = totalWeight;
        // 对背包按单位重量价值从大到小排序
        Arrays.sort(bags, Collections.reverseOrder());
    }

    public void solve() {
        int tempWeight = totalWeight;

        for (int i = 0; i < bags.length; i++) {
            //判断当前物品是否可以放入背包中，若不能则继续循环，查找下一个物品
            if (tempWeight - bags[i].getWeight() < 0)
                continue;

            tempWeight -= bags[i].getWeight();
            bestValue += bags[i].getValue();
        }
    }

    public int getBestValue() {
        return bestValue;
    }
}
 class TXSFTest {

    public static void main(String[] args) {

        Knapsack[] bags = new Knapsack[] { new Knapsack(2, 13),
                new Knapsack(1, 10), new Knapsack(3, 24), new Knapsack(2, 15),
                new Knapsack(4, 28), new Knapsack(5, 33), new Knapsack(3, 20),
                new Knapsack(1, 8) };
        int totalWeight = 12;

        TXSFProblem problem = new TXSFProblem(bags, totalWeight);
        problem.solve();

        System.out.println(problem.getBestValue());
    }

}
