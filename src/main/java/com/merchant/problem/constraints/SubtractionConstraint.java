package com.merchant.problem.constraints;

import java.util.Arrays;
import java.util.List;

public class SubtractionConstraint implements Constraints {

    private static List<Character> INVALID_SUBSTRACTION;
    private static List<String> VALID_SUBSTRACTION;

    static {
        INVALID_SUBSTRACTION = Arrays.asList('D','L','V');
        VALID_SUBSTRACTION = Arrays.asList("IV","IX","XL","XC","CD","CM");
    }


    public Boolean isConstraintPassed(Character current, Character next) {

        if(INVALID_SUBSTRACTION.contains(current)){
            return !INVALID_SUBSTRACTION.contains(current);
        } else {
            return VALID_SUBSTRACTION.contains(current+""+next);
        }
    }
}
