package input;

import java.util.ArrayList;

public class inputHandler {

    ArrayList<String> formatedArray;
    char[] potentialExpressions = {'(',')','*','/','+','-'};

    public inputHandler(){
    }

    public String[] takeInput(String inputString){
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
        formatedArray.add(tempChar);
        return arrayListToArray(formatedArray);
    }

    private static boolean doesItContain(char toTest,char[] storeArray){
        for (char temp:storeArray){
            if (Character.compare(toTest, temp) == 0){
                return true;
            }
        }
        return false;
    }

    private static String[] arrayListToArray(ArrayList<String> inputArrayList){
        String[] toReturn = new String[inputArrayList.size()];

        for (int i = 0; i < inputArrayList.size(); i++){
            toReturn[i] = inputArrayList.get(i);
        }

        return toReturn;
    } 
}