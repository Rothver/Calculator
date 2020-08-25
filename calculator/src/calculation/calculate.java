package calculation;

public class calculate {
    String[] inputArray;
    char[] pemdasArray = {'p','e','m','d','a','s'};

    public calculate(String inputString){
        this.inputArray = inputString.split(" ");
    }

    public int findSolution() {
        for (int i = 1; i < pemdasArray.length; i++){
            parseArray(pemdasArray[i]);
        }
        return Integer.valueOf(inputArray[0]);
    }

    private void parseArray(char symbol) {
        switch (symbol) {
            case 'p':
                findParenthesis();
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

    private void findParenthesis(){
        boolean repeat = true;
        int startLoc = -1;
        int endLoc = -1;

        while(repeat == true){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("(") == 0){
                    startLoc = i;
                } else if (inputArray[i].compareTo(")") == 0){
                    endLoc = i;
                }
                if (startLoc > -1 && endLoc > -1){

                }
            }
        }
    }

    

    private void doExponents(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("^")==0){
                    int result = (int)Math.pow(Double.valueOf(inputArray[i-1]),Double.valueOf(inputArray[i+1]));
                    shortenInputArray(result,i-1);
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doMultiplication(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("*")==0){
                    int c = Integer.valueOf(inputArray[i-1]) * Integer.valueOf(inputArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doDivision(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("/")==0){
                    int c = Integer.valueOf(inputArray[i-1]) / Integer.valueOf(inputArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doAddition(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("+")==0){
                    int c = Integer.valueOf(inputArray[i-1]) + Integer.valueOf(inputArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
    }

    private void doSubtraction(){
        boolean repeat = true;
        while(repeat){
            for(int i = 0; i < inputArray.length; i++){
                if (inputArray[i].compareTo("-")==0){
                    int c = Integer.valueOf(inputArray[i-1]) - Integer.valueOf(inputArray[i+1]);
                    shortenInputArray(c,i-1);
                    break;
                }
                if (i == inputArray.length-1){
                    repeat = false;
                }
            }
        }
    }


    private void shortenInputArray(int resultNumber, int replaceLocation){
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
        inputArray = tempArray;
    }
}
