
import controller.CaculatorController2;
import controller.CalculatorController;
import view.Calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @Author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        // TODO code application logic here
        Calculator calculator = new Calculator();
////        CalculatorController calculatorController = new CalculatorController(calculator);
        CaculatorController2 caculatorController2 = new CaculatorController2(calculator);
       
    }

}
