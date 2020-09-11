package calculation;

import java.util.Arrays;

public class calculate {
    String[] globalInArray;
    char[] pemdasArray = {'p','e','m','d','a','s'};

    public calculate(String inputString){
        this.globalInArray = inputString.split(" ");
    }

    public String startSolution() {
        globalInArray = findSolution(globalInArray);
        return printArray(globalInArray);
    }

    private String[] findSolution(String[] inputArray){
        for (char c : pemdasArray) {
            inputArray = parseArray(c, inputArray);
        }
        return inputArray;
    }

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
                    inputArray = shortenInputArray(inputArray,Integer.parseInt(temp),startLoc,endLoc-startLoc);
                    break;
                }

                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    

    private String[] doExponents(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("^")==0){
                    int result = (int)Math.pow(Double.parseDouble(inputArray[i-1]),Double.parseDouble(inputArray[i+1]));
                    inputArray = shortenInputArray(inputArray, result,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    private String[] doMultiplication(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("*")==0){
                    int c = Integer.parseInt(inputArray[i-1]) * Integer.parseInt(inputArray[i+1]);
                    inputArray = shortenInputArray(inputArray,c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    private String[] doDivision(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("/")==0){
                    int c = Integer.parseInt(inputArray[i-1]) / Integer.parseInt(inputArray[i+1]);
                    inputArray = shortenInputArray(inputArray,c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    private String[] doAddition(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("+")==0){
                    int tempLow = i;
                    int tempHigh = i;

                    while (!Character.isDigit(inputArray[tempLow].charAt(0))){
                        tempLow--;
                        if (tempLow < 0){
                            return inputArray;
                        }
                    }
                    while (!Character.isDigit(inputArray[tempHigh].charAt(0))){
                        tempHigh++;
                        if (tempHigh > inputArray.length){
                            return inputArray;
                        }
                    }
                    int c = Integer.parseInt(inputArray[tempLow]) + Integer.parseInt(inputArray[tempHigh]);
                    System.out.println(c);
                    inputArray = shortenInputArray(inputArray,c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }

    private String[] doSubtraction(String[] inputArray){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("-")==0){
                    int c = Integer.parseInt(inputArray[i-1]) - Integer.parseInt(inputArray[i+1]);
                    inputArray = shortenInputArray(inputArray,c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
        return inputArray;
    }


    private String[] shortenInputArray(String[] inputArray,int resultNumber, int replaceLocation){
        String[] tempArray = new String[inputArray.length-2];
        int tempHold = 0;
        for (int i = 0; i < tempArray.length; i++){
            if (i == replaceLocation){
                tempArray[i] = String.valueOf(resultNumber);
                tempHold+=2;
            } else {
                tempArray[i] = inputArray[tempHold];
                tempHold++;
            }
        }
        return tempArray;
    }

    private String[] shortenInputArray(String[] inputArray,int resultNumber, int replaceLocation, int shortenAmount){
        String[] tempArray = new String[inputArray.length-shortenAmount];
        shortenAmount++;
        int tempHold = 0;
        for (int i = 0; i < tempArray.length; i++){
            if (i == replaceLocation){
                tempArray[i] = String.valueOf(resultNumber);
                tempHold+=shortenAmount;
            } else {
                tempArray[i] = inputArray[tempHold];
                tempHold++;
            }
        }
        inputArray = tempArray;
        return inputArray;
    }

    private String printArray(String[] inputArray){
        StringBuilder returnString = new StringBuilder();
        for (String x: inputArray){
            returnString.append(x);
        }
        return returnString.toString();
    }
}