package main;


import calculation.calculate;
import input.inputHandler;

public class run {

    calculate calculator;
    inputHandler getInput = new inputHandler();

    public run(){
        String[] temp = getInput.takeInput("16/2*3-1+1");
        
        calculator = new calculate(temp);
    }

    public static void main (String args[]){
        run start = new run();
        System.out.println("final result: " + start.calculator.startSolution());
    }
}