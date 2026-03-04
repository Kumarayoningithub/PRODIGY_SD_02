import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GuessingGame extends JFrame implements ActionListener {

    JTextField guessField;
    JButton guessButton, resetButton;
    JLabel messageLabel, attemptLabel;

    int randomNumber;
    int attempts = 0;

    GuessingGame(){

        setTitle("Number Guessing Game");

      
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1,10,10));

        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;

        JLabel title = new JLabel("Guess a number between 1 and 100", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Guess: "));
        inputPanel.add(guessField);

        add(inputPanel);

        guessButton = new JButton("Guess");
        resetButton = new JButton("Restart");

        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guessButton);
        buttonPanel.add(resetButton);

        add(buttonPanel);

        messageLabel = new JLabel("Start guessing!", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(messageLabel);

        attemptLabel = new JLabel("Attempts: 0", JLabel.CENTER);
        add(attemptLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == guessButton){

            try{

                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if(guess > randomNumber){
                    messageLabel.setText("Too High! Try again.");
                }
                else if(guess < randomNumber){
                    messageLabel.setText("Too Low! Try again.");
                }
                else{
                    JOptionPane.showMessageDialog(this,
                            "Correct! 🎉\nYou guessed in " + attempts + " attempts.");

                    messageLabel.setText("You won! Press Restart to play again.");
                }

                attemptLabel.setText("Attempts: " + attempts);

            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Enter a valid number");
            }
        }

        if(e.getSource() == resetButton){

            Random rand = new Random();
            randomNumber = rand.nextInt(100) + 1;

            attempts = 0;

            guessField.setText("");
            messageLabel.setText("Game restarted! Guess again.");
            attemptLabel.setText("Attempts: 0");
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}