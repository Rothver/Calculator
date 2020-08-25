package calculation;

public class calculate {
    String[] globalInArray;
    char[] pemdasArray = {'p','e','m','d','a','s'};

    public calculate(String inputString){
        this.globalInArray = inputString.split(" ");
    }

    public int startSolution() {
        findSolution(globalInArray);
        return Integer.valueOf(globalInArray[0]);
    }

    private void findSolution(String[] inputArray){
        for (int i = 1; i < pemdasArray.length; i++){
            parseArray(pemdasArray[i],inputArray);
        }
    }

    private void parseArray(char symbol, String[] inputArray) {
        switch (symbol) {
            case 'p':
                //findParenthesis();
                break;
            case 'e':
                doExponents();
                break;
            case 'm':
                doMultiplication();
                break;
            case 'd':
                doDivision();
                break;
            case 'a':
                doAddition();
                break;
            case 's':
                doSubtraction();
                break;
            default:
                break;
        }
    }

    private void findParenthesis(String[] inputArray){
        boolean repeat = true;
        int startLoc = -1;
        int endLoc = -1;

        while(repeat){
            for(int i = 0; i < globalInArray.length; i++){
                if (globalInArray[i].compareTo("(") == 0){
                    startLoc = i;
                } else if (globalInArray[i].compareTo(")") == 0){
                    endLoc = i;
                }
                if (startLoc > -1 && endLoc > -1){
                    int result;
                    shortenInputArray(0, startLoc,endLoc-startLoc);
                    repeat = false;
                }
            }
        }
    }

    

    private void doExponents(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < globalInArray.length; i++){
                if (globalInArray[i].compareTo("^")==0){
                    int result = (int)Math.pow(Double.valueOf(globalInArray[i-1]),Double.valueOf(globalInArray[i+1]));
                    shortenInputArray(result,i-1);
                }
                if (i == globalInArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doMultiplication(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < globalInArray.length; i++){
                if (globalInArray[i].compareTo("*")==0){
                    int c = Integer.valueOf(globalInArray[i-1]) * Integer.valueOf(globalInArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == globalInArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doDivision(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < globalInArray.length; i++){
                if (globalInArray[i].compareTo("/")==0){
                    int c = Integer.valueOf(globalInArray[i-1]) / Integer.valueOf(globalInArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == globalInArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doAddition(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < globalInArray.length; i++){
                if (globalInArray[i].compareTo("+")==0){
                    int c = Integer.valueOf(globalInArray[i-1]) + Integer.valueOf(globalInArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == globalInArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doSubtraction(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < globalInArray.length; i++){
                if (globalInArray[i].compareTo("-")==0){
                    int c = Integer.valueOf(globalInArray[i-1]) - Integer.valueOf(globalInArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == globalInArray.length-1){
                    repeat = false;
                }
            }
        }
    }


    private void shortenInputArray(int resultNumber, int replaceLocation){
        String[] tempArray = new String[globalInArray.length-2];
        int tempHold = 0;
        for (int i = 0; i < tempArray.length; i++){
            if (i == replaceLocation){
                tempArray[i] = String.valueOf(resultNumber);
                tempHold+=2;
            } else {
                tempArray[i] = globalInArray[tempHold];
                tempHold++;
            }
        }
        globalInArray = tempArray;
    }

    private void shortenInputArray(int resultNumber, int replaceLocation, int shortenAmount){
        String[] tempArray = new String[globalInArray.length-shortenAmount];
        int tempHold = 0;
        for (int i = 0; i < tempArray.length; i++){
            if (i == replaceLocation){
                tempArray[i] = String.valueOf(resultNumber);
                tempHold+=shortenAmount;
            } else {
                tempArray[i] = globalInArray[tempHold];
                tempHold++;
            }
        }
        globalInArray = tempArray;
    }
}
