package cn.willlu.shorturl.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides utility methods for Luhn check digit calculation.<br/><br/>
 *
 * @author willlu.zheng
 * @date 2019-11-22
 */
public class LuhnUtil {

    /**
     * Calculates the last digit expected for a number that passes the Luhn test,
     * ignoring the last digit. This is useful for creating Luhn numbers.
     * The actual evaluation if a number passes the test is done by
     * {@link #luhnCheck(String)}.
     *
     * @param code the code
     * @return i
     */
    public static int requiredCheckDigit(String code) {
        int[] cardNoArr = convertStrToInArr(code);

        if (luhnCheck(code)) {
            return cardNoArr[0];
        }

        for (int i = 0; i < cardNoArr.length; i += 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }

        int sum = 0;

        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }

        return sum * 9 % 10;
    }

    /**
     * Tests a number against the Luhn algorithm.
     *
     * @param code the code
     * @return b
     * @see #requiredCheckDigit(String)
     */
    public static boolean luhnCheck(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        } else {
            int[] checkArr = convertStrToInArr(code);

            int sum;
            for (sum = 1; sum < checkArr.length; sum += 2) {
                checkArr[sum] <<= 1;
                checkArr[sum] = checkArr[sum] / 10 + checkArr[sum] % 10;
            }

            sum = 0;

            for (int i = 0; i < checkArr.length; ++i) {
                sum += checkArr[i];
            }

            return sum % 10 == 0;
        }
    }

    private static int[] convertStrToInArr(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException();
        }

        int index = code.length();

        int[] cardNoArr = new int[code.length()];

        for (char c : code.toCharArray()) {
            cardNoArr[--index] = c - '0';
        }
        return cardNoArr;
    }


    public static void main(String[] args) {
        System.out.println(luhnCheck("tAAqVd9P4"));
        System.out.println(requiredCheckDigit("tAAqVd9P4"));
    }

}