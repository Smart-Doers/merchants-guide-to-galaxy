package com.merchant.problem.constraints;

public interface Constraints {

    /**
     * All the implementers should apply constraint on the provided input values
     * @param current
     * @param next
     * @return true if constraint is passed and vice-versa
     */
    Boolean isConstraintPassed(Character current, Character next);
}
