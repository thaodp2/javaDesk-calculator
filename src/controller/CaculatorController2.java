/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.Calculator;

/**
 *
 * @Author Administrator
 */
public class CaculatorController2 {

    Calculator calculator;
    String number1 = null;
    String number2 = null;
    String btnCal = "";
    String result = "";
    String memory = "0";
    boolean isWriting = false;

    public CaculatorController2(Calculator calculator) {
        this.calculator = calculator;
        calculator.setVisible(true);
        JButton btnAllClear = calculator.getBtnAllClear();
        btnAllClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnAllClearActionPerformed(evt);
                JTextField txtDisplay = calculator.getTxtDisplay();
                txtDisplay.setText("0");
                clearAll();
            }
        });

        ArrayList<JButton> listBtnNum = getBtnNums();
        for (JButton btnNum : listBtnNum) {
            btnNum.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JTextField txtDisplay = calculator.getTxtDisplay();
                    String textNumDisplay = txtDisplay.getText();
                    String numInput = btnNum.getText();
                    String numDisplay = textNumDisplay;
                    if (textNumDisplay.startsWith("Er") || (isWriting == false)) {
                        numDisplay = numInput;
                        if (numDisplay.equals(".")) {
                            numDisplay = "0.";
                        }
                        isWriting = true;
                    } else {
                        numDisplay += numInput;
                    }
                    
                    if (numDisplay.length() > 1 && numDisplay.startsWith("0") && numDisplay.startsWith("0.") == false) {
                        numDisplay = numDisplay.substring(1);
                    }
                    if (numDisplay.length() > 18) {
                        numDisplay = numDisplay.substring(0, 18);
                    }
                    txtDisplay.setText(checkDot(numDisplay));
                }
            });
        }

        ArrayList<JButton> btnMemorys = getBtnMemory();
        for (JButton btnMemory : btnMemorys) {
            btnMemory.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    isWriting = false;
                    JTextField txtDisplay = calculator.getTxtDisplay();
                    String textDisplay = txtDisplay.getText();
                    String textBtnMemory = btnMemory.getText();
                    processMemory(textDisplay, textBtnMemory);

                }
            });
        }

        ArrayList<JButton> btnBinarys = getBtnBinarys();
        for (JButton btnBinary : btnBinarys) {
            btnBinary.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //check btnCal is null? -> if not null then calculate                 
                    JTextField txtDisplay = calculator.getTxtDisplay();
                    String textDisplay = txtDisplay.getText();
                    String textBtnBinary = btnBinary.getText();
                    if (textDisplay.startsWith("Er") == false) {
                        if (number1 == null) {
                            number1 = textDisplay;
                        } else if (number2 == null && isWriting == true) {
                            number2 = textDisplay;
                        }
                        if (btnCal.equals("") == false) {
                            calculateProcess();
                            txtDisplay.setText(result);
                            if (result.startsWith("Er") == false) {
                                if (number1 != null && number2 != null) {
                                    clearAll();
                                    number1 = calculator.getTxtDisplay().getText();
                                }
                            }
                        }
                    }
                     System.out.println("--------Binary------");
                    System.out.println("num1:  "+number1);
                    System.out.println("num2:  "+number2);
                    System.out.println("numbtn:  "+btnCal);
                    System.out.println("numréult:  "+result);
                    isWriting = false;
                    btnCal = textBtnBinary;
                    System.out.println("bin check:" + number1 + " - " + number2 + " - " + btnCal);
                }
            });
        }
        ArrayList<JButton> btnUnarys = getBtnUnarys();
        for (JButton btnUnary : btnUnarys) {
            btnUnary.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                     System.out.println("--------Unary1111------");
                    System.out.println("num1:  "+number1);
                    System.out.println("num2:  "+number2);
                    System.out.println("numbtn:  "+btnCal);
                    System.out.println("numréult:  "+result);
                    JTextField txtDisplay = calculator.getTxtDisplay();
                    String textDisplay = txtDisplay.getText();
                    String textBtnUnary = btnUnary.getText();
                    if (number1 == null) {
                        number1 = textDisplay;
                    } else if (btnCal.equals("") == false) {
                        boolean check = false;
                        for (JButton btnBinary : btnBinarys) {
                            if (btnBinary.getText().equals(btnCal)) {
                                check = true;
                                break;
                            }
                        }
                        System.out.println("bin check:" + isWriting + " - " + number2 + " - " + btnCal);
                        if (number2 == null && check == true && isWriting == true) {
                            number2 = textDisplay;
                        }
                    }
                    isWriting = false;
                    System.out.println("--------Unary------");
                    System.out.println("num1:  "+number1);
                    System.out.println("num2:  "+number2);
                    System.out.println("numbtn:  "+btnCal);
                    System.out.println("numréult:  "+result);
                    if (btnCal.equals("") == false) {
                        calculateProcess();
                    }
                    btnCal = textBtnUnary;
                    calculateProcess();
                    txtDisplay.setText(result);
                    if (result.startsWith("Er") == false) {
                        clearAll();
                    }
                }
            });
        }
    }

    private void calculateProcess() {
        if (number1 == null) {
            result = "Error!!";
            return;
        }
        BigDecimal numb1 = new BigDecimal(number1);
        BigDecimal numb2;
        if (number2 == null) {
            numb2 = BigDecimal.ZERO;
        } else {
            numb2 = new BigDecimal(number2);
        }
        System.out.println(numb1 + " - " + numb2 + " - " + btnCal);
        switch (btnCal) {
            case "+": {
                result = numb1.add(numb2).toString();
                break;
            }
            case "_": {
                result = numb1.subtract(numb2).toString();
                break;
            }
            case "x": {
                result = numb1.multiply(numb2).toString();
                break;
            }
            case "/": {
                if (numb2.equals(BigDecimal.ZERO)) {
                    result = "Error!!";
                    number2 = null;
                } else {
                    result = numb1.divide(numb2).toString();
                }
                break;
            }

            case "1/x": {
                System.out.println("NUM1:_________" + numb1);
                result = BigDecimal.ONE.divide(numb1).doubleValue() + "";
                break;
            }
            case "√": {
                if (numb1.compareTo(BigDecimal.ZERO) < 0) {
                    result = "Error!!";

                } else {
                    result = sqrtProcess(numb1).toString();
                }
                break;
                //return BigDecimal.ONE.pow(SQRT_Dig.intValue());
            }
            case "%": {
                result = numb1.divide(BigDecimal.valueOf(100)).toString();
                break;
            }
            case "+/-": {
                result = numb1.multiply(BigDecimal.valueOf(-1)).toString();
                break;
            }
            case "=": {
                if (result.equals("")) {
                    result = calculator.getTxtDisplay().getText();                                       
                }
                break;
            }
        }
        if (result.contains(".")) {
            while (result.endsWith("0")) {
                result = result.substring(0, result.length() - 1);
            }
            if (result.endsWith(".")) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return;
    }

    private static BigDecimal sqrtProcess(BigDecimal num) {
        BigDecimal value = new BigDecimal(Math.sqrt(num.doubleValue()));
        return value.add(new BigDecimal(num.subtract(value.multiply(value)).doubleValue() / (value.doubleValue() * 2.0)));
//        return x.add(new BigDecimal(numb1.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0));
    }

    private void processMemory(String value, String btnMemory) {
        BigDecimal result = new BigDecimal(value);
        BigDecimal memoryNumber = new BigDecimal(memory);
        switch (btnMemory) {
            case "MC": {
                memory = "0";
                break;
            }
            case "MR": {
                calculator.getTxtDisplay().setText(memory);
                break;

            }
            case "M+": {
                memory = memoryNumber.add(result).toString();
                break;
            }
            case "M-": {
                memory = memoryNumber.subtract(result).toString();
                break;
            }
        }
        clearAll();

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

    private void clearAll() {
        number1 = null;
        number2 = null;
        btnCal = "";
        result = "";
        isWriting = false;

    }

    private ArrayList<JButton> getBtnMemory() {
        ArrayList<JButton> listBtnMemory = new ArrayList<>();
        listBtnMemory.add(calculator.getBtnClearValueStored());
        listBtnMemory.add(calculator.getBtnDisplayMemory());
        listBtnMemory.add(calculator.getBtnAdd());
        listBtnMemory.add(calculator.getBtnSubtract());
        return listBtnMemory;
    }

    private ArrayList<JButton> getBtnUnarys() {
        ArrayList<JButton> listBtnUnary = new ArrayList<>();
        listBtnUnary.add(calculator.getBtnSquareRoot());
        listBtnUnary.add(calculator.getBtnPersent());
        listBtnUnary.add(calculator.getBtn1Divide());
        listBtnUnary.add(calculator.getBtnNegative());
        listBtnUnary.add(calculator.getBtnResult());

        return listBtnUnary;
    }

    private ArrayList<JButton> getBtnBinarys() {
        ArrayList<JButton> listBtnBinarys = new ArrayList<>();
        listBtnBinarys.add(calculator.getBtnDivide());
        listBtnBinarys.add(calculator.getBtnMultiply());
        listBtnBinarys.add(calculator.getBtnMinus());
        listBtnBinarys.add(calculator.getBtnTotal());
        return listBtnBinarys;

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

}
