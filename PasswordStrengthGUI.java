import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthGUI extends JFrame {
    private JLabel label;
    private JPasswordField passwordField;
    private JButton checkButton;
    private JButton showPasswordButton;
    private JTextField showPasswordField;

    public PasswordStrengthGUI() {
        setTitle("Password Strength Estimator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Enter a password:");
        add(label);

        passwordField = new JPasswordField(20);
        add(passwordField);

        checkButton = new JButton("Check");
        add(checkButton);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String strength = estimatePasswordStrength(password);
                JOptionPane.showMessageDialog(null, "Password strength: " + strength);
            }
        });

        showPasswordButton = new JButton("Show Password");
        add(showPasswordButton);

        showPasswordField = new JTextField(20);
        showPasswordField.setEditable(false);
        add(showPasswordField);

        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                showPasswordField.setText(password);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String estimatePasswordStrength(String password) {
        double entropy = calculateEntropy(password);
        if (entropy >= 60) {
            return "Strong";
        } else if (entropy >= 40) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }

    private double calculateEntropy(String password) {
        int passwordLength = password.length();
        int characterSetSize = password.chars().distinct().toArray().length;
        double entropy = Math.log(characterSetSize) * passwordLength / Math.log(2);
        return entropy;
    }

    private void calculateAccuracy() {
        String[] testPasswords = {"abc@123", "password123", "hellO999", "783r2v43rbkqyf3", "P0p!^#42*^&GV%E&$",  "@*&Strong#123", "mysecret", "R!i1#23#ofweyiu#%*^63", "ROSE%!@873", "b7y35c4b3m", "O@R!Ntter%4#$*^", "1234567890", "qwertyuiopasdfghjkl", "qUick%$1(%$#@", "mofqw32864632xvdr673q", "fh3uh325#%^j6", "dekweq 45645", "guide%#@!&^^#%", "abyufb#!72tx76", "busybee", "QUICK", "players44432", "b7cfrtw8ufgmx", "G5466vn^^$vbfhd65^UIY2E3", "6vx67$%$v763g72386e6", "!8asgeyf26$#7$%$v763g", "FQ$Ffa34q4q4q^$f", "fifiz$&vd5639%!()*#", "break@321", "5419xcde","P@ssw0rd", "correct horse battery staple", "test", "pass", "12345678", "iloveyou", "letmein", "welcome123", "admin123", "password1" };
        String[] expectedStrengths = {"Weak", "Weak", "Weak", "Strong", "Strong", "Moderate", "Weak", "Strong", "Weak", "Weak", "Moderate", "Weak", "Strong", "Moderate", "Strong", "Moderate", "Weak", "Moderate", "Moderate", "Weak", "Weak", "Weak", "Moderate", "Strong", "Strong", "Strong", "Moderate", "Strong", "Weak", "Weak","Moderate", "Strong", "Weak", "Weak", "Weak", "Weak", "Weak", "Weak", "Weak", "Weak"};
        int totalTestCases = testPasswords.length;
        int correctPredictions = 0;

        for (int i = 0; i < totalTestCases; i++) {
            String password = testPasswords[i];
            String expectedStrength = expectedStrengths[i];
            String actualStrength = estimatePasswordStrength(password);

            if (actualStrength.equals(expectedStrength)) {
                correctPredictions++;
            }
        }

        double accuracy = (double) correctPredictions / totalTestCases * 100;
        System.out.println("Accuracy: " +  accuracy + "%");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PasswordStrengthGUI gui = new PasswordStrengthGUI();
                gui.calculateAccuracy();
            }
        });
    }
}
