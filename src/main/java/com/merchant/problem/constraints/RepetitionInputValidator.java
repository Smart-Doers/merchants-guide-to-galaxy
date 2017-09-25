package com.merchant.problem.constraints;

import java.util.Arrays;
import java.util.List;

public class RepetitionInputValidator implements InputValidator {

    private static Integer MAX_REPETITON_ALLOWED;
    private static List<Character> VALUES_NOT_TO_BE_REPEATED;

    static {
        MAX_REPETITON_ALLOWED = 3;
        VALUES_NOT_TO_BE_REPEATED = Arrays.asList(
                                    'D',
                                    'L',
                                    'V');
    }

    /**
     * This method will perform below mentioned steps to validate input
     * 1) Convert String to a charArray
     * 2) For Each Character
     *                      1) If It exist in NOT_TO_BE_REPEATED_LIST check for any successive repetition
     *                      2) If It is not in NOT_TO_BE_REPEATED_LIST check for repetition not crossing MAX_LIMIT
     *
     */
    public Boolean isValidInput(String input) {

        char [] inputCharArray = input.toCharArray();
        char currentInputChar;
        Boolean result = true;

        for(int i = 0 ;i<inputCharArray.length ; i++) {
            currentInputChar = inputCharArray[i];

            if(VALUES_NOT_TO_BE_REPEATED.contains(currentInputChar)){
                if(i < inputCharArray.length - 1){
                    if(currentInputChar == inputCharArray[i+1]){
                        return false;
                    }
                }
            } else {
                if(i+2 < inputCharArray.length){
                    String subpartToBeValidated = input.substring(i,i+3);
                    if(new StringBuffer(subpartToBeValidated).reverse().toString().equals(subpartToBeValidated)) {
                        return false;
                    }
                }
            }
        }
        return result;
    }
}
