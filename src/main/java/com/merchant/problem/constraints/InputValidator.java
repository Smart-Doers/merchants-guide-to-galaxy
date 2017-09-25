package com.merchant.problem.constraints;

public interface InputValidator {

    /**
     * All the implementers should check if the input is valid or not
     * @param current
     * @param next
     * @return true if input is clean  and vice-versa
     */

    Boolean isValidInput(String input);
}
