package input;

import java.util.ArrayList;

public class inputHandler {

    ArrayList<String> formatedArray;
    char[] potentialExpressions = {'(',')','*','/','+','-'};

    public inputHandler(){
    }

    public void takeInput(String inputString){
        formatedArray = new ArrayList<String>();
        String tempChar = "";
        for (int i = 0; i < inputString.length(); i++){
            if (doesItContain(inputString.charAt(i),potentialExpressions)){
                formatedArray.add(tempChar);
                tempChar = "";
                formatedArray.add(String.valueOf(inputString.charAt(i)));
            } else {
                tempChar += inputString.charAt(i);
            }
        }
    }

    public static boolean doesItContain(char toTest,char[] storeArray){
        for (char temp:storeArray){
            if (Character.compare(toTest, temp) == 0){
                return true;
            }
        }
        return false;
    }
}
