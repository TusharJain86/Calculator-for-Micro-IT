import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TreeUI;

public class Calculator {
    int boardwidth = 360;
    int boardheight = 540;

    Color customLighGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "DEL", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%", "DEL"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displaypPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String A= "0";
    String operator = null;
    String B= null;

    Calculator(){
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //labels

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        //panel

        displaypPanel.setLayout(new BorderLayout());
        displaypPanel.add(displayLabel); //putting text label inside the panel
        frame.add(displayLabel, BorderLayout.NORTH); //pannel in a window which is a frame

        buttonsPanel.setLayout( new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for( int i =0; i< buttonValues.length; i++){
            JButton button= new JButton();
            String buttonValue = buttonValues[i];
            button.setFont( new Font("Airal", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customDarkGray);
                button.setForeground(customBlack);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
                
            }
            else{
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }

            buttonsPanel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue == "="){
                            if( A !=null){
                                B =displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if(operator == "+"){
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if(operator == "-"){
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if(operator == "×"){
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if(operator == "÷"){
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearAll();
                            }

                        }
                        else if("+-×÷".contains(buttonValue)){
                            if (operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }

                    }
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue == "AC"){
                            clearAll();
                            displayLabel.setText("0");

                        }
                        else if(buttonValue.equals("+/-")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }
                        else if(buttonValue == "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }
                        else if(buttonValue.equals("DEL")) {
                            String currentText = displayLabel.getText();
                            if (currentText.length() > 1) {
                                displayLabel.setText(currentText.substring(0, currentText.length() - 1));
                            }
                            else {
                                displayLabel.setText("0");
                            }
                        }
                    }
                    else{ 
                        if( buttonValue == "."){
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText()+buttonValue);
                            }
                        }
                        else if("0123456789".contains(buttonValue)){
                            if(displayLabel.getText()=="0"){
                                displayLabel.setText(buttonValue);
                            }
                            else{
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }

    }
    void clearAll(){
        A ="0";
        operator = null;
        B = null;
    }
    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

}
