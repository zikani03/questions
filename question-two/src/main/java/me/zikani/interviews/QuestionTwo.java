package me.zikani.interviews;

import java.math.BigInteger;

/*
Maxi is the largest number that can obtained by swapping two digits, Mini is the smallest.
Write a function that takes a number and returns Maxi and Mini. Leading zeros are not
permitted.
Use the examples below to test your code:
maximini(12340) => [42310, 10342]
maximini(98761) => [98761, 18769]
maximini(9000) => [9000, 9000]
maximini(357758017083851) => [857758017083351, 157758017083853]
*/
public class QuestionTwo {

    public static void main(String[] args) {
        if (args.length == 1) {
            if ("runTest".equalsIgnoreCase(args[0])) {
                runTests();
                return;
            }

            try {
                new BigInteger(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number");
                return;
            }
            var result = maximini(args[0]);
            System.out.printf("maxi=%s mini=%s", result[0], result[1]);
        }
    }

    /**
     * Swaps the first element with an element at the given index.
     *
     * @param arr
     * @param idx
     * @return
     */
    public static String swap(char[] arr, int idx) {
        return swapAt(arr, idx, 0);
    }

    public static String swapAt(char[] arr, int first, int second) {
        char tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
        return new String(arr);
    }

    /**
     * Maximini returns the largest and smallest numbers after swapping two digits in the given number.
     *
     * @param num the argument must be given as a string
     * @return
     */
    public static String[] maximini(String num) {
        char[] arr = num.toCharArray();
        // We want to find the right-most big integer > 0 and move it to the front
        // We also want to find the left-most small integer > 0 and move it to the front
        int curMax = Integer.MIN_VALUE, curMin = Integer.MAX_VALUE;
        int idxMax = -1;
        int idxMin = arr.length - 1;
        int cur;
        for (int i = 0; i < arr.length; i++) {
            cur = Integer.parseInt(arr[i] + "");
            if (cur >= curMax) {
                idxMax = i;
                curMax = cur;
            }
            if ((cur != 0 || idxMin == arr.length) && cur <= curMin) {
                idxMin = i;
                curMin = cur;
            }
        }
        String maxi = swap(num.toCharArray(), idxMax);
        String mini = swap(num.toCharArray(), idxMin);
        // Special case handling for situations where we have a number that ends with 0
        // We try to swap the last zero with the next number in the sequence.
        if (arr[arr.length - 1] == '0' && arr.length >= 1) {
            String tmpMini = swapAt(num.toCharArray(), arr.length-1, 1);
            if (mini.compareTo(tmpMini) >= 1) {
                mini = tmpMini;
            }
        }
        return new String[] { maxi, mini};
    }


    static class TestCaseData {
        String input, max, min;

        public TestCaseData(String input, String max, String min) {
            this.input = input;
            this.max = max;
            this.min = min;
        }
    }

    public static void runTests() {
        TestCaseData[] data = new TestCaseData[]{
//                new TestCaseData("37401", "73401", "17403"),
                new TestCaseData("12340", "42310", "10342"),
                new TestCaseData("98761", "98761", "18769"),
                new TestCaseData("9000", "9000", "9000"),
                new TestCaseData("357758017083851", "857758017083351", "157758017083853")
        };

        for (TestCaseData t : data) {
            var result = maximini(t.input);
            if (t.max.equals(result[0]) && t.min.equals(result[1])) {
                System.out.println("SUCCESSFUL!");
            } else {
                System.out.println("FAILED!");
            }
            System.out.printf("maxi=%s mini=%s", result[0], result[1]);
            System.out.println("------");
        }
    }
}
