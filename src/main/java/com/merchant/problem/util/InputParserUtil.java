package com.merchant.problem.util;

import com.merchant.problem.constraints.Constraints;
import com.merchant.problem.constraints.InputValidator;
import com.merchant.problem.constraints.RequestType;

import java.util.Map;

public class InputParserUtil {

    public static RequestType getUserRequestType(String input) {
        RequestType requestType = null;
        if(isExchangeTypeRequest(input.split(" "))){
            requestType = RequestType.EXCHANGE;
        }
        else if(isQuestionRequest(input)) {
            requestType = RequestType.QUESTION;
        }
        else if(isPerValueRequest(input)){
            requestType = RequestType.PER_VALUE;
        }
        return requestType == null ? RequestType.RUBBISH : requestType;
    }

    private static Boolean isExchangeTypeRequest(String [] input){
        if(input.length == 3){
            return input[1].equals("is");
        }
        return false;
    }

    private static Boolean isQuestionRequest(String input){
        return input.toLowerCase().contains("how many") && input.contains("?");
    }

    private static Boolean isPerValueRequest(String input){
        return input.toLowerCase().contains("is") && input.toLowerCase().contains("credits");

    }

    public static void addValueInExchange(String input, Map<String, Character> exchangeValuesMap) {
        String [] inputArray = input.split(" ");
        exchangeValuesMap.put(inputArray[0],inputArray[2].charAt(0));
    }

    public static void addPerComponentValue(String input, Map<String, Character> exchangeValuesMap, Map<String, Integer> perValueMap, Map<Character, Integer> romanValuesMap, InputValidator inputValidator, Constraints constraints){
        String components = input.substring(0,input.indexOf("is"));

        String []allComponents = components.trim().split(" ");
        String perValueFor = allComponents[allComponents.length - 1];
        perValueMap.put(perValueFor,1);

        Boolean isValueValid = true;
        StringBuffer inputBuffer = new StringBuffer();
        for(int i=0;i<allComponents.length-1;i++){
            String s = allComponents[i];
            inputBuffer.append(exchangeValuesMap.get(s));
        }

        String quantity = inputBuffer.toString();
        String digitSubstring  = input.substring(input.indexOf("is"));
        String [] digitSubstringArray = digitSubstring.split(" ");
        int totalCost = Integer.parseInt(digitSubstringArray[1]);
        int totalQuantity = getNumeralValue(quantity,romanValuesMap,constraints);

        System.out.println(" 1 Value of "+perValueFor + " is "+totalCost/totalQuantity);

        perValueMap.put(perValueFor,totalCost/totalQuantity);

    }

    public static Integer getNumeralValue(String romanValue, Map<Character, Integer> romanValuesMap, Constraints constraints) {
        int totalValue = 0;
        for (int i = 0; i < romanValue.length(); ) {
            char current = romanValue.charAt(i);
            if (i + 1 < romanValue.length()) {
                char next = romanValue.charAt(i + 1);

                if (current < next) {
                    if (constraints.isConstraintPassed(romanValue.charAt(i), romanValue.charAt(i + 1))) {
                        totalValue += romanValuesMap.get(next) - romanValuesMap.get(current);
                        i += 2;
                        continue;
                    } else {
                        totalValue += romanValuesMap.get(current);
                    }
                } else {
                    totalValue += romanValuesMap.get(current);
                }
            } else {
                totalValue += romanValuesMap.get(current);
            }

            i++;
        }
        return totalValue;
    }

    public static void answerQuestion(String input, Map<String, Integer> perValueMap,Map<String, Character> exchangeValuesMap ,Constraints constraints, Map<Character, Integer> romanValuesMap) {
        String partOfInput = input.substring(input.indexOf("is"));

        String [] partOfInputArray = partOfInput.split(" ");

        StringBuffer totalQuantity = new StringBuffer();
        for(int i=1;i<partOfInputArray.length - 2;i++){
            totalQuantity.append(exchangeValuesMap.get(partOfInputArray[i]));
        }

        Integer qty = getNumeralValue(totalQuantity.toString(),romanValuesMap,constraints);

        System.out.println(perValueMap.get(partOfInputArray[partOfInputArray.length-2])* qty);


    }
}
