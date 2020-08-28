package calculation;

import java.util.Arrays;

public class calculate {
    String[] globalInArray;
    char[] pemdasArray = {'p','e','m','d','a','s'};

    public calculate(String inputString){
        this.globalInArray = inputString.split(" ");
    }

    public int startSolution() {
        globalInArray = findSolution(globalInArray);
        return Integer.valueOf(globalInArray[0]);
    }

    private String[] findSolution(String[] inputArray){
        for (int i = 1; i < pemdasArray.length; i++){
            inputArray = parseArray(pemdasArray[i],inputArray);
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
        int endLoc = -1;

        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("(") == 0){
                    startLoc = i;
                } else if (inputArray[i].compareTo(")") == 0){
                    endLoc = i;

                    inputArray = findSolution(Arrays.copyOfRange(inputArray, startLoc, endLoc));
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
                    int result = (int)Math.pow(Double.valueOf(inputArray[i-1]),Double.valueOf(inputArray[i+1]));
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
                    int c = Integer.valueOf(inputArray[i-1]) * Integer.valueOf(inputArray[i+1]);
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
                    int c = Integer.valueOf(inputArray[i-1]) / Integer.valueOf(inputArray[i+1]);
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
                    int c = Integer.valueOf(inputArray[i-1]) + Integer.valueOf(inputArray[i+1]);
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
                    int c = Integer.valueOf(inputArray[i-1]) - Integer.valueOf(inputArray[i+1]);
                    shortenInputArray(inputArray,c,i-1);
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
}