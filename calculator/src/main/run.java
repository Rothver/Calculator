package main;


import calculation.calculate;

public class run {

    calculate calculator;

    public run(){
        calculator = new calculate("1 * - 1");
    }

    public static void main (String args[]){
        run start = new run();
        System.out.println("final result: " + start.calculator.startSolution());
    }
}