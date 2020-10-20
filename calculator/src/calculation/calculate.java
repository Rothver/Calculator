package calculation;

import java.util.Arrays;


public class calculate {
    //Global Array is the input; holds the base arithmatic 
    String[] globalInArray;
    //Just used as a way to regulate what gets called and the order
    char[] pemdasArray = {'p','e','m','d','a','s'};

    /**
     * Takes the desired string of math to solve and splits it based on spaces
     * Does not do anything else though
     * @param inputString
     */
    public calculate(String inputString){
        this.globalInArray = inputString.split(" ");
    }

    /**
     * Begins process of solving the math (formally starts it)
     * @return Solution as one string
     */
    public String startSolution() {
        globalInArray = findSolution(globalInArray);
        return printArray(globalInArray);
    }

    /**
     * Finds the solution to the input array
     * Will quit if there are less than 3 items in the input array
     * @param inputArray
     * @return inputArray
     */
    private String[] findSolution(String[] inputArray){
        if (inputArray.length < 3){
            return inputArray;
        }
        
        for (char c : pemdasArray) {
            inputArray = parseArray(c, inputArray);
        }
        return inputArray;
    }

    /**
     * Goes through each of the different operations possible (or desired)
     * Returns the resulting input array after each operation is called
     * @param symbol
     * @param inputArray
     * @return inputArray
     */
    private String[] parseArray(char symbol, String[] inputArray) {
        switch (symbol) {
            case 'p':
                inputArray = findParenthesis(inputArray);
                break;
            case 'e':
                inputArray = doExponents(inputArray);
                break;
            case 'm':
                inputArray = doMultiplication(inputArray);
                break;
            case 'd':
                inputArray = doDivision(inputArray);
                break;
            case 'a':
                inputArray = doAddition(inputArray);
                break;
            case 's':
                inputArray = doSubtraction(inputArray);
                break;
            default:
                break;
        }
        return inputArray;
    }

    /**
     * Looks through the given input for parentheticals
     * When one is found, it passes that to the find solution function
     * The result is a shortened input array from the inner most "(" to ")" found
     * @param inputArray
     * @return inputArray
     */
    private String[] findParenthesis(String[] inputArray){
        boolean repeat = true;
        int startLoc = -1;
        int endLoc;

        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("(") == 0){
                    startLoc = i;
                } else if (inputArray[i].compareTo(")") == 0){
                    endLoc = i;

                    String temp = findSolution(Arrays.copyOfRange(inputArray, startLoc+1, endLoc))[0];
                    inputArray = shortenInputArray(inputArray,Double.parseDouble(temp),startLoc,endLoc);
                    break;
                }

                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    /**
     * Looks for and preforms the exponentional operation
     * Returns the input array, after shortening it based on what was done 
     * @param inputArray
     * @return inputArray
     */
    private String[] doExponents(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("^")==0){
                    Double baseValue = 0.0;
                    Double pow = 0.0;
                    int replaceLocLow = 0;
                    int replaceLocHigh = 0;

                    try{
                        if (inputArray[i-2].compareTo("-")==0){
                            baseValue = Double.parseDouble(inputArray[i-2] + inputArray[i-1]);
                            replaceLocLow = i-2;
                        }
                    } catch (Exception e){
                        baseValue = Double.parseDouble(inputArray[i-1]);
                        replaceLocLow = i - 1;
                    }

                    try {
                        if (inputArray[i+1].compareTo("-")==0){
                            pow = Double.parseDouble(inputArray[i+1] + inputArray[i+2]);
                            replaceLocHigh = i + 2;
                        }
                    } catch (Exception e) {
                        pow = Double.parseDouble(inputArray[i+1]);
                        replaceLocLow = i + 1;
                    }

                    Double result = Math.pow(baseValue,pow);

                    inputArray = shortenInputArray(inputArray,result,replaceLocLow,replaceLocHigh);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    /**
     * Looks for and preforms the multiplication operation
     * Returns the input array after it has preformed the operation and shortened the array 
     * @param inputArray
     * @return inputArray
     */
    private String[] doMultiplication(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("*")==0){
                    Double valueOne = 0.0;
                    Double valueTwo = 0.0;

                    int replaceLocLow = 0;
                    int replaceLocHigh = 0;

                    try{
                        if (inputArray[i-2].compareTo("-")==0){
                            valueOne = Double.parseDouble(inputArray[i-2] + inputArray[i-1]);
                            replaceLocLow = i - 2;
                        }
                    } catch (Exception e){
                        valueOne = Double.parseDouble(inputArray[i-1]);
                        replaceLocHigh = i - 1;
                    }

                    try {
                        if (inputArray[i+1].compareTo("-")==0){
                            valueTwo = Double.parseDouble(inputArray[i+1] + inputArray[i+2]);
                            replaceLocHigh = i + 2;
                        }
                    } catch (Exception e) {
                        valueTwo = Double.parseDouble(inputArray[i+1]);
                        replaceLocHigh = i + 1;
                    }

                    Double result = valueOne * valueTwo;

                    inputArray = shortenInputArray(inputArray,result,replaceLocLow,replaceLocHigh);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    /**
     * Looks for and preforms the division operation
     * Returns the input array after it has preformed the operation and shortened the array
     * @param inputArray
     * @return inputArray
     */
    private String[] doDivision(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("/")==0){
                    Double valueOne = 0.0;
                    Double valueTwo = 0.0;

                    int replaceLocLow = 0;
                    int replaceLocHigh = 0;

                    try{
                        if (inputArray[i-2].compareTo("-")==0){
                            valueOne = Double.parseDouble(inputArray[i-2] + inputArray[i-1]);
                            replaceLocLow = i - 2;
                        }
                    } catch (Exception e){
                        valueOne = Double.parseDouble(inputArray[i-1]);
                        replaceLocLow = i -1; 
                    }

                    try {
                        if (inputArray[i+1].compareTo("-")==0){
                            valueTwo = Double.parseDouble(inputArray[i+1] + inputArray[i+2]);
                            replaceLocHigh = i + 2;
                        }
                    } catch (Exception e) {
                        valueTwo = Double.parseDouble(inputArray[i+1]);
                        replaceLocHigh = i + 1;
                    }

                    Double result = valueOne / valueTwo;

                    //rework shorten Functions to handle negative numbers (make more dynamic?)
                    inputArray = shortenInputArray(inputArray,result,replaceLocLow,replaceLocHigh);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    /**
     * Looks for and preforms the addition operation
     * Returns the input array after it has preformed the operation and shortened the array
     * @param inputArray
     * @return inputArray
     */
    private String[] doAddition(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("+")==0){
                    Double valueOne = 0.0;
                    Double valueTwo = 0.0;

                    int replaceLocLow = 0;
                    int replaceLocHigh = 0;

                    try{
                        if (inputArray[i-2].compareTo("-")==0){
                            valueOne = Double.parseDouble(inputArray[i-2] + inputArray[i-1]);
                            replaceLocLow = i - 2;
                        }
                    } catch (Exception e){
                        valueOne = Double.parseDouble(inputArray[i-1]);
                        replaceLocLow = i - 1;
                    }

                    try {
                        if (inputArray[i+1].compareTo("-")==0){
                            valueTwo = Double.parseDouble(inputArray[i+1] + inputArray[i+2]);
                            replaceLocHigh = i + 2;
                        }
                    } catch (Exception e) {
                        valueTwo = Double.parseDouble(inputArray[i+1]);
                        replaceLocHigh = i + 1;
                    }

                    /*if (!Character.isAlphabetic(inputArray[i-1].charAt(0))){
                        //issue is high value not properly breaking when out of range
                        int highValue = i+1;
                        while (!Character.isDigit(inputArray[highValue].charAt(0))){
                            highValue++;
                            if (highValue > inputArray.length-1){
                                return inputArray;
                            }
                        } */
                        Double result = valueOne + valueTwo;

                        inputArray = shortenInputArray(inputArray,result,replaceLocLow,replaceLocHigh);
                        break;

                    }
                    if (i == inputArray.length-1){
                        repeat = false;
                    }
                }
            }
            return inputArray;
        }

    /**
     * Looks for and preforms the subtraction operation
     * Returns the input array after it has preformed the operation and shortened the array
     * @param inputArray
     * @return inputArray
     */
    private String[] doSubtraction(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("-")==0){
                    Double valueOne = 0.0;
                    Double valueTwo = 0.0;

                    int replaceLocLow = 0;
                    int replaceLocHigh = 0;
                    try{
                        if (inputArray[i-2].compareTo("-")==0){
                            valueOne = Double.parseDouble(inputArray[i-2] + inputArray[i-1]);
                            replaceLocLow = i - 2;
                        }
                    } catch (Exception e){
                        valueOne = Double.parseDouble(inputArray[i-1]);
                        replaceLocLow = i - 1;
                    }

                    try {
                        if (inputArray[i+1].compareTo("-")==0){
                            valueTwo = Double.parseDouble(inputArray[i+1] + inputArray[i+2]);
                            replaceLocHigh = i + 2;
                        }
                    } catch (Exception e) {
                        valueTwo = Double.parseDouble(inputArray[i+1]);
                        replaceLocHigh = i + 1;
                    }

                    Double result = valueOne - valueTwo;

                    //rework shorten Functions to handle negative numbers (make more dynamic?)
                    inputArray = shortenInputArray(inputArray,result,replaceLocLow,replaceLocHigh);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    /**
     * Takes the input array and shortens it to the desired length based on the result
     * @param inputArray
     * @param resultNumber
     * @param replaceLocLow
     * @param replaceLocHigh
     * @return inputArray
     */
    private String[] shortenInputArray(String[] inputArray, Double resultNumber, int replaceLocLow, int replaceLocHigh) {
        int shortenAmount = 0;
        boolean isNeg = false;
        if (resultNumber < 0){
            shortenAmount = replaceLocHigh - replaceLocLow + 1;
            isNeg = true;
        } else {
            shortenAmount = replaceLocHigh - replaceLocLow;
        }
        String[] tempArray = new String[inputArray.length - shortenAmount];
        
        int tempHold = 0;
        int i = 0;
        while(i < tempArray.length){
            if (i == replaceLocLow){
                if (isNeg){
                    tempArray[i] = "-";
                    i++;
                    tempHold++;
                }
                tempArray[i] = String.valueOf(resultNumber);
            } else{
                tempArray[i] = inputArray[tempHold];
                tempHold++;
            }
            i++;
        }
        inputArray = tempArray;
        return inputArray;
    }

    /**
     * Takes the current input array and changes it from an array into a single string
     * @param inputArray
     * @return solution string
     */
    private String printArray(String[] inputArray){
        StringBuilder returnString = new StringBuilder();
        for (String x: inputArray){
            returnString.append(x);
        }
        return returnString.toString();
    }
}