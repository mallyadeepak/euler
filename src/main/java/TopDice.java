import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopDice {

    public static void main(String args[]) {
        TopDice topDice = new TopDice();
        //topDice.printTotalWays(5, 6, 15, 3);
        //topDice.printTotalWays(10, 6, 15, 3);
        topDice.printTotalWays(20, 12, 70, 10);
    }

    public void printTotalWays(int numberOfDices, int maxDiceValue, int sumRequired, int totalNumbersInSum) {
        long start = System.currentTimeMillis();
        int totalWays = computeTotalWays(numberOfDices, maxDiceValue, sumRequired, totalNumbersInSum);
        long timeTakenInMs = System.currentTimeMillis() - start;
        System.out.println("Total Ways = " + totalWays + ", "
                           + "numberOfDices = " + numberOfDices
                           + ", maxDiceValue = " + maxDiceValue
                           + ", sumRequired = " + sumRequired
                           + ", totalNumbersInSum = " + totalNumbersInSum
                           + " in = " + timeTakenInMs + "ms");
    }

    private int computeTotalWays(int numberOfDices, int maxDiceValue,
                                 int sumRequired, int totalNumbersInSum) {
        List<Integer> listSoFar = new ArrayList<Integer>(maxDiceValue);
        return computeTotalWays(numberOfDices, maxDiceValue, sumRequired, totalNumbersInSum, listSoFar);
    }

    private Set<Integer> areAllElementsSame(List<Integer> list) {
        Set<Integer> set = new HashSet<Integer>();
        set.addAll(list);
        return set;
    }

    private void printList(List<Integer> list) {
        System.out.println(list.toString());
    }

    private int computeTotalWays(int numberOfDicesLeft, int maxDiceValue, int sumRequired,
                                 int totalNumbersInSum, List<Integer> listSoFar) {
        int totalWays = 0;
        int currentTopSum = 0;
        List<Integer> sortedList = new ArrayList<Integer>(listSoFar);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        for (int i = 0; i < totalNumbersInSum; i++) {
            if (i <= (sortedList.size() - 1)) {
                currentTopSum += sortedList.get(i);
            }
        }

        if (currentTopSum == sumRequired) {
            if (listSoFar.size() == totalNumbersInSum) {
                int min = sortedList.get(sortedList.size() - 1);
                totalWays = 1;
                for (int i = 0; i < numberOfDicesLeft; i++) {
                    totalWays *= min;
                }
                return totalWays;
            } else if (listSoFar.size() < totalNumbersInSum) {
                return 0;
            }
        } else if (currentTopSum > sumRequired) {
            return 0;
        }

        // No dices left to roll.
        if (numberOfDicesLeft == 0) {
            if (currentTopSum == sumRequired) {
                printList(listSoFar);
                return 1;
            } else {
                return 0;
            }
        }

        //Recurse deeper.
        for (int i = 1; i <= maxDiceValue; i++) {
            //System.out.println("Level =" + numberOfDicesLeft + ", iteration = " + i);
            List<Integer> tempList = new ArrayList<Integer>(listSoFar);
            tempList.add(i);
            totalWays += computeTotalWays(numberOfDicesLeft - 1, maxDiceValue, sumRequired,
                                          totalNumbersInSum, tempList);
        }
        return totalWays;
    }
}
