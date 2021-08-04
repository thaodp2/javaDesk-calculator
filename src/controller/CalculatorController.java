/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.Calculator;

/**
 *
 * @Author Admin
 */
public class CalculatorController {

    Calculator calculator;
    String num1 = "0";
    String num2 = "0";
    String butCal = "";
    int count = 0;
    String memory = "0";
    int process = 0;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
        calculator.setVisible(true);
        ArrayList<JButton> listBtnNums = getBtnNums();
        JButton btnAllClear = calculator.getBtnAllClear();
        btnAllClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnAllClearActionPerformed(evt);
                JTextField txtDisplay = calculator.getTxtDisplay();
                txtDisplay.setText("0");
                clearAll(0);
            }
        });
        for (JButton btnNum : listBtnNums) {
            btnNum.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    // int checkCount2 = checkCount;
                    JTextField txtDisplay = calculator.getTxtDisplay();
                    String textNumDisplay = txtDisplay.getText();
                    String numInput = btnNum.getText();
                    String numDisplay = "";

                    if (numDisplay.equals("00")) {
                        numDisplay = "0";
                    } else if ((textNumDisplay.equals("0") && !numInput.startsWith(".")) || textNumDisplay.equals("Error!")) {
                        numDisplay = numInput;
                    } else {
                        numDisplay = numDisplay + numInput;
                    }
                    process++;
                    if (num1.equals(textNumDisplay) && !num1.equals("0")) {
                        txtDisplay.setText(numInput);
                    } else if (butCal.equals("=") || butCal.startsWith("M")) {
                        txtDisplay.setText(numInput);
                    } else {
                        txtDisplay.setText(checkDot(numDisplay));
                    }
//                
                }
            });
            //count = 1;

        }

//        HashMap<String, JButton> getBtnOther = getBtnOther();
        ArrayList<JButton> getBtnOther = getBtnOther();
        for (JButton btnOther : getBtnOther) {

            btnOther.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String txtbtnCal = btnOther.getText();
                    JTextField txtDisplay = calculator.getTxtDisplay();
                    String textDisplay = txtDisplay.getText();
                    String checkCal = "";
                    System.out.println("----TRUOC KHI TINH");
                    System.out.println("num1: " + num1);
                    System.out.println("num2: " + num2);
                    System.out.println("btnCal" + butCal);
                    System.out.println("count: " + count);
                    System.out.println("btnNow: " + txtbtnCal);
                    if (count != 0 || txtbtnCal.equals("+/-") || txtbtnCal.equals("1/x") || txtbtnCal.equals("%") || txtbtnCal.startsWith("M")) {
                        if (process == 0) {
                            num2 = "0";
                            if(num1.equals("0")){
                                num1 = textDisplay;
                                System.out.println("sakjhdcbkjsuawhebfuilhbder;i");
                            }
                        } else if (!num1.equals("0")) {
                            num2 = textDisplay;
                        } else {
                            num1 = textDisplay;
                        }
                        BigDecimal calculate = BigDecimal.ONE;
                        String processMemory = "";

                        if (txtbtnCal.startsWith("M")) {
                            processMemory = processMemory(textDisplay, butCal);
                        } else if (txtbtnCal.equals("+/-") || txtbtnCal.equals("1/x") || txtbtnCal.equals("%")) {
                            calculate = calculateProcess(txtbtnCal, num1, num2);
                        } else {
                            calculate = calculateProcess(butCal, num1, num2);
                        }
                       
                        if (calculate == null) {
                            checkCal = null;
                        } else if (processMemory == null) {
                            checkCal = textDisplay;
                        } else {
                            checkCal = calculate.toString();
                            if (checkCal.length() > 14) {
                                checkCal = checkCal.substring(0, 14);
                            }
                        }
                        System.out.println("----SAU KHI TINH");
                        System.out.println("num1: " + num1);
                        System.out.println("num2: " + num2);
                        System.out.println("btnCal" + butCal);
                        System.out.println("count: " + count);
                        System.out.println("btnNow: " + txtbtnCal);
                        System.out.println("check call: " + checkCal);
                        System.out.println("caculate: " + calculate);
                        if (txtbtnCal.equals("MR")) {
                            txtDisplay.setText(processMemory);
                        } else if (checkCal == null) {
                            txtDisplay.setText("Error!");
                            String number1 = num1;
                                 System.out.println("NUMBNER :" + num1);
                            clearAll(1);
                            num1 = number1;

                        } else if (txtbtnCal.equals("=")) {
                            String number1 = checkCal;
                            txtDisplay.setText(checkCal);
                            clearAll(1);
                            num1 = number1;
                            count--;
                        } else if (butCal.equals("=") && txtbtnCal.startsWith("M")) {
                            txtDisplay.setText(textDisplay);
                            clearAll(1);
                            if (txtbtnCal.startsWith("M")) {
                                count--;
                            }
                        } else {
                            txtDisplay.setText(checkCal);
                            num1 = checkCal;
                        }
                    } else {
                        num1 = textDisplay;
                    }
                    butCal = txtbtnCal;
                    count++;
                    process = 0;
                    System.out.println("-------------------");
                    System.out.println("num1: " + num1);
                    System.out.println("num2: " + num2);
                    System.out.println("btnCal" + butCal);
                    System.out.println("count: " + count);

                }
            });

            //  count = 1;
        }
        //System.out.println(count+"sd");
    }

    private String processMemory(String value, String btnMemory) {
        BigDecimal result = new BigDecimal(value);
        BigDecimal memoryNumber = new BigDecimal(memory);
        switch (btnMemory) {
            case "MC": {
                memory = "0";
                return null;
            }
            case "MR": {
                return memoryNumber.toString();

            }
            case "M+": {
                memory = memoryNumber.add(result).toString();
                return null;
            }
            case "M-": {
                memory = memoryNumber.subtract(result).toString();
                return null;
            }
        }
        return null;
    }

    private void clearAll(int choice) {
        num1 = "";
        num2 = "";
        butCal = "";
        count = 0;
     

    }

    private String checkDot(String numberText) {
        String stringTextNum[] = numberText.split("");
        int count = 0;
        numberText = "";
        for (String text : stringTextNum) {
            if (text.equals(".")) {
                count++;
            }
            if (count > 1 && text.equals(".")) {
                text = "";
                System.out.println(count);
            }
            numberText = numberText + text;
        }
        return numberText;
    }

    private BigDecimal calculateProcess(String btnCal, String num1, String num2) {
        BigDecimal numb1 = new BigDecimal(num1);
        BigDecimal numb2 = new BigDecimal(num2);
        switch (btnCal) {
            case "+": {
                return numb1.add(numb2);
            }
            case "_": {
                return numb1.subtract(numb2);
            }
            case "x": {
                return numb1.multiply(numb2);
            }
            case "/": {
                if (num2.equals("0")) {
                    return null;
                }
                return numb1.divide(numb2);
            }

            case "1/x": {
                System.out.println("NUM1:_________"+numb1);
                return BigDecimal.ONE.divide(numb1);
            }
            case "√": {
                return sqrtProcess(numb1);
                //return BigDecimal.ONE.pow(SQRT_Dig.intValue());
            }
            case "%": {
                return numb1.divide(BigDecimal.valueOf(100));
            }
            case "+/-": {
                return numb1.multiply(BigDecimal.valueOf(-1));
            }
        }
        return null;
    }

    private static BigDecimal sqrtProcess(BigDecimal num) {
        BigDecimal value = new BigDecimal(Math.sqrt(num.doubleValue()));
        return value.add(new BigDecimal(num.subtract(value.multiply(value)).doubleValue() / (value.doubleValue() * 2.0)));
//        return x.add(new BigDecimal(numb1.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0));
    }

    private ArrayList<JButton> getBtnNums() {
        ArrayList<JButton> listNumbers = new ArrayList<>();
        listNumbers.add(calculator.getBtn0());
        listNumbers.add(calculator.getBtn1());
        listNumbers.add(calculator.getBtn2());
        listNumbers.add(calculator.getBtn3());
        listNumbers.add(calculator.getBtn4());
        listNumbers.add(calculator.getBtn5());
        listNumbers.add(calculator.getBtn6());
        listNumbers.add(calculator.getBtn7());
        listNumbers.add(calculator.getBtn8());
        listNumbers.add(calculator.getBtn9());
        listNumbers.add(calculator.getBtnDot());
        return listNumbers;
    }

    private ArrayList<JButton> getBtnOther() {
        ArrayList<JButton> btnCaculate = new ArrayList<>();
//        btnCaculate.add(calculator.getBtnClearValueStored());
//        btnCaculate.add(calculator.getBtnDisplayMemory());
//        btnCaculate.add(calculator.getBtnAdd());
//        btnCaculate.add(calculator.getBtnSubtract());


//        btnCaculate.add(calculator.getBtnSquareRoot());
        btnCaculate.add(calculator.getBtnDivide());
//        btnCaculate.add(calculator.getBtnPersent());
        btnCaculate.add(calculator.getBtnMultiply());
//        btnCaculate.add(calculator.getBtn1Divide());
        btnCaculate.add(calculator.getBtnMinus());
        btnCaculate.add(calculator.getBtnTotal());
        btnCaculate.add(calculator.getBtnResult());
//        btnCaculate.add(calculator.getBtnNegative());
        return btnCaculate;
    }

}
