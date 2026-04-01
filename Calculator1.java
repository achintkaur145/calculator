import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class Calculator1 {
    int boardWidth = 360;
    int boardHeight = 540;
    
    Color customDarkPink = new Color(252,108,133);
    Color customLightPink = new Color(255,182,193);
    Color customBlush = new Color(255,119,130);

       String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};
    
    JFrame frame =new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttoPanel = new JPanel();

        String A = "0";
        String operator = null;
        String B= null;

    Calculator1(){
       // frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customLightPink);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial",Font.PLAIN,80) );
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        buttoPanel.setLayout(new GridLayout(5,4));
        buttoPanel.setBackground(customLightPink);
        frame.add(buttoPanel);

        for (int i=0; i<buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(Color.white));


            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customBlush);
                button.setForeground(Color.WHITE);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue) ){
                button.setBackground(customBlush);
                button.setForeground(Color.white);

            }
            else{
                button.setBackground(customDarkPink);
                button.setForeground(Color.white);
            }


            buttoPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button =(JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        if (buttonValue == "="){
                          if (A != null) {
                          B = displayLabel.getText();
                         double numA = Double.parseDouble(A);
                         double numB = Double.parseDouble(B);
                         if  (operator == "+"){
                            
                          displayLabel.setText(removeZeroDecimal(numA+numB));
                         }
                          else if (operator == "-"){
                          displayLabel.setText(removeZeroDecimal(numA-numB));
                          }
                         else if   (operator == "×"){
                          displayLabel.setText(removeZeroDecimal(numA*numB));  
                         }
                          
                          else if  (operator == "÷"){
                          displayLabel.setText(removeZeroDecimal(numA/numB));
                           }
                          
                          clearAll();


                        }
                    }
                        else if ("+-÷×".contains(buttonValue)){
                            if(operator == null ){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                           
                          operator = buttonValue;
                        }
                        

                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if (buttonValue =="AC"){
                            clearAll();
                            displayLabel.setText("0");


                        }
                        else if (buttonValue == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }
                        else if (buttonValue =="%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                             }

                    }
                    else{
                        if (buttonValue =="."){
                            if(!displayLabel.getText().contains(buttonValue)){
                                 displayLabel.setText(displayLabel.getText()+ buttonValue);
                            }

                        }
                        else if ("0123456789".contains(buttonValue)) {
                            if (displayLabel.getText()=="0"){
                                displayLabel.setText(buttonValue);
                            }
                            else{
                                displayLabel.setText(displayLabel.getText()+ buttonValue);
                            }
                        

                        }

                    }

                
                }
              
            });
            frame.setVisible(true);
        }
          
        
  }
  void clearAll(){
    A = "0";
    operator = null;
    B = null;

  }
  String removeZeroDecimal(double numDisplay) {
    if (numDisplay % 1 == 0){
        return Integer.toString((int) numDisplay);
    }
    return Double.toString(numDisplay);
    
  }
  public static void main(String[] args) {
    new Calculator();
    
  }

}
