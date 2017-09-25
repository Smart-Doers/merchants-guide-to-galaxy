package com.merchant.problem.controller;

import com.merchant.problem.constraints.*;
import com.merchant.problem.util.InputParserUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
//glob is I
//prok is V
//prish is X
//tegj is L
//glob glob Silver is 34 Credits
//glob prok Gold is 57800 Credits
//presh presh Iron is 3910 Credits
//how many Credits is glob prok Silver ?
//how many Credits is glob prok Gold ?
//how many Credits is glob prok Iron ?
 **/
public class MerchantGalaxyController {

    public static Map<Character, Integer> ROMAN_VALUES_MAP = new HashMap();
    public static List<Constraints> ALL_CONSTRAINTS = new ArrayList<>();
    public static List<InputValidator> ALL_VALIDATORS = new ArrayList<>();
    public static Map<String, Character> EXCHANGE_VALUES_MAP = new HashMap<>();
    public static Map<String, Integer> PER_VALUE_MAP = new HashMap<>();

    static {
        ROMAN_VALUES_MAP.put('I', 1);
        ROMAN_VALUES_MAP.put('V', 5);
        ROMAN_VALUES_MAP.put('X', 10);
        ROMAN_VALUES_MAP.put('L', 50);
        ROMAN_VALUES_MAP.put('C', 100);
        ROMAN_VALUES_MAP.put('D', 500);
        ROMAN_VALUES_MAP.put('M', 1000);

        ALL_CONSTRAINTS = Arrays.asList(new SubtractionConstraint());
        ALL_VALIDATORS = Arrays.asList(new RepetitionInputValidator());

    }


    private static void startBusinessWithGalaxyGuys() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();

            // Get Type of Request
            RequestType requestType = InputParserUtil.getUserRequestType(input);
            System.out.println(requestType);

            switch (requestType) {
                case EXCHANGE: {
                    InputParserUtil.addValueInExchange(input, EXCHANGE_VALUES_MAP);
                    break;
                }
                case PER_VALUE: {
                    InputParserUtil.addPerComponentValue(input,EXCHANGE_VALUES_MAP, PER_VALUE_MAP,ROMAN_VALUES_MAP,ALL_VALIDATORS.get(0),ALL_CONSTRAINTS.get(0));
                    break;
                }
                case QUESTION: {
                    InputParserUtil.answerQuestion(input,PER_VALUE_MAP,EXCHANGE_VALUES_MAP,ALL_CONSTRAINTS.get(0),ROMAN_VALUES_MAP);
                    break;
                }
            }
//            System.out.println(EXCHANGE_VALUES_MAP);
//            if(!PER_VALUE_MAP.isEmpty()){
//                System.out.println(PER_VALUE_MAP);
//            }
        }

    }

    public static void main(String[] args) throws Exception {
        startBusinessWithGalaxyGuys();
//        String inputValue = "XX";
//        System.out.println(InputParserUtil.getNumeralValue(inputValue,ROMAN_VALUES_MAP,ALL_CONSTRAINTS.get(0)));
    }

}
