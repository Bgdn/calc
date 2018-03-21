
//original code is from https://examples.javacodegeeks.com/desktop-java/swing/java-swing-calculator-example/
//The java Template Calculator TODO
package com.bpoklad.simplecalc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JavaCalculator implements ActionListener {
    JFrame guiFrame;
    JPanel buttonPanel;
    JTextField numberCalc;
    int calcOperation = 0;
    int currentCalc;

    public JavaCalculator() {
        guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Simple Calculator. V0.2");
        guiFrame.setSize(300, 300);
        guiFrame.setLocationRelativeTo(null);

        numberCalc = new JTextField();
        numberCalc.setHorizontalAlignment(JTextField.RIGHT);
        numberCalc.setEditable(false);

        guiFrame.add(numberCalc, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        guiFrame.add(buttonPanel, BorderLayout.CENTER);

        for (int i=0; i<10; i++) {
            addNumberButton(buttonPanel, String.valueOf(i));
        }

        addActionButton(buttonPanel, 1, "+");
        addActionButton(buttonPanel, 2, "-");
        addActionButton(buttonPanel, 3, "*");
        addActionButton(buttonPanel, 4, "/");
        addActionButton(buttonPanel, 5, "Clear"); // Square (^2) button -> Clear button

        JButton equalsButton = new JButton("=");
        equalsButton.setActionCommand("=");
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int calculate;
                if (!numberCalc.getText().isEmpty()) {
                    int number = Integer.parseInt(numberCalc.getText());
                    if (calcOperation == 1) {
                        int calculate = currentCalc + number;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                    else if (calcOperation == 2) {
                        int calculate = currentCalc - number;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                    else if (calcOperation == 3) {
                        int calculate = currentCalc * number;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                    else if (calcOperation == 4) {
                        int calculate = currentCalc / number;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                    else if (calcOperation == 5) { // Changed Square (^2) button to Clear button here.
                        int calculate = 0;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                }
            }
        });
        buttonPanel.add(equalsButton);
        guiFrame.setVisible(true);
    }
    private void addNumberButton(Container parent, String name) {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        but.addActionListener(this);
        parent.add(but);
    }

    private void addActionButton(Container parent, int action, String text) {
        JButton but = new JButton(text);
        but.setActionCommand(text);
        OperatorAction addAction = new OperatorAction(action);
        but.addActionListener(addAction);
        parent.add(but);
    }


    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        numberCalc.setText(numberCalc.getText() + action); // Here is enabler of multidigit input :)
    }

    private class OperatorAction implements ActionListener {
        private int operator;
        public OperatorAction(int operation) {
            operator = operation;
        }
        public void actionPerformed(ActionEvent event) {
            currentCalc = Integer.parseInt(numberCalc.getText());
            calcOperation = operator;
            numberCalc.setText(""); // After any operator input (+ - * /) textfield must be cleared for entering of next number. Also, Clear button become functional after including that statement.
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JavaCalculator();
            }
        });
    } // end of main method

} // end of JavaCalculator class
