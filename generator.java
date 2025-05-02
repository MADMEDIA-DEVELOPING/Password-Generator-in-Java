import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import javax.swing.border.EmptyBorder;
import java.util.Random;

public class generator {
    
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=<>?/{}[]|";
    
    private JTextField passwordField;
    private JSlider lengthSlider;
    private JCheckBox uppercaseCheckbox, lowercaseCheckbox, numbersCheckbox, specialCharsCheckbox;
    private JLabel strengthLabel;
    private JProgressBar strengthBar;
    
    public generator() {
        // Create the main frame
        JFrame frame = new JFrame("Generator de Parole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        
        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(40, 44, 52));
        
        // Title
        JLabel titleLabel = new JLabel("GENERATOR DE PAROLE");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Password field
        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setPreferredSize(new Dimension(400, 50));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        passwordField.setFont(new Font("Monospaced", Font.BOLD, 18));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(new Color(0, 255, 0));
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setBackground(new Color(40, 44, 52));
        
        JButton generateButton = createStyledButton("Generează Parolă", new Color(76, 175, 80));
        JButton copyButton = createStyledButton("Copiază", new Color(33, 150, 243));
        
        buttonPanel.add(generateButton);
        buttonPanel.add(copyButton);
        
        // Length settings
        JPanel lengthPanel = new JPanel();
        lengthPanel.setLayout(new BoxLayout(lengthPanel, BoxLayout.Y_AXIS));
        lengthPanel.setBackground(new Color(40, 44, 52));
        lengthPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lengthLabel = new JLabel("Lungimea parolei: 12");
        lengthLabel.setForeground(Color.WHITE);
        lengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lengthSlider = new JSlider(6, 30, 12);
        lengthSlider.setMajorTickSpacing(6);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setPaintLabels(true);
        lengthSlider.setBackground(new Color(40, 44, 52));
        lengthSlider.setForeground(Color.WHITE);
        
        lengthSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int length = lengthSlider.getValue();
                lengthLabel.setText("Lungimea parolei: " + length);
            }
        });
        
        lengthPanel.add(lengthLabel);
        lengthPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        lengthPanel.add(lengthSlider);
        
        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsPanel.setBackground(new Color(60, 63, 65));
        optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
        JLabel optionsTitle = new JLabel("Opțiuni");
        optionsTitle.setFont(new Font("Verdana", Font.BOLD, 16));
        optionsTitle.setForeground(Color.WHITE);
        optionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lowercaseCheckbox = createCheckBox("Litere mici (a-z)", true);
        uppercaseCheckbox = createCheckBox("Litere mari (A-Z)", true);
        numbersCheckbox = createCheckBox("Numere (0-9)", true);
        specialCharsCheckbox = createCheckBox("Caractere speciale (!@#$)", true);
        
        optionsPanel.add(optionsTitle);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        optionsPanel.add(lowercaseCheckbox);
        optionsPanel.add(uppercaseCheckbox);
        optionsPanel.add(numbersCheckbox);
        optionsPanel.add(specialCharsCheckbox);
        
        // Strength panel
        JPanel strengthPanel = new JPanel();
        strengthPanel.setLayout(new BoxLayout(strengthPanel, BoxLayout.Y_AXIS));
        strengthPanel.setBackground(new Color(40, 44, 52));
        strengthPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel strengthTitle = new JLabel("Puterea parolei:");
        strengthTitle.setForeground(Color.WHITE);
        strengthTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        strengthLabel = new JLabel("Medie");
        strengthLabel.setForeground(Color.YELLOW);
        strengthLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        strengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        strengthBar = new JProgressBar(0, 100);
        strengthBar.setValue(50);
        strengthBar.setForeground(Color.YELLOW);
        strengthBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        
        strengthPanel.add(strengthTitle);
        strengthPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        strengthPanel.add(strengthLabel);
        strengthPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        strengthPanel.add(strengthBar);
        
        // Add components to main panel with spacing
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(passwordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(lengthPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(optionsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(strengthPanel);
        
        // Button actions
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = generatePassword();
                passwordField.setText(password);
                evaluatePasswordStrength(password);
            }
        });
        
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(passwordField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        
        // Add panel to frame
        frame.add(mainPanel);
        frame.setVisible(true);
        
        // Generate initial password
        passwordField.setText(generatePassword());
        evaluatePasswordStrength(passwordField.getText());
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
    
    private JCheckBox createCheckBox(String text, boolean selected) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(selected);
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(new Color(60, 63, 65));
        return checkBox;
    }
    
    private String generatePassword() {
        StringBuilder chars = new StringBuilder();
        
        if (lowercaseCheckbox.isSelected()) chars.append(LOWERCASE_CHARS);
        if (uppercaseCheckbox.isSelected()) chars.append(UPPERCASE_CHARS);
        if (numbersCheckbox.isSelected()) chars.append(NUMBERS);
        if (specialCharsCheckbox.isSelected()) chars.append(SPECIAL_CHARS);
        
        // If no option is selected, use lowercase by default
        if (chars.length() == 0) chars.append(LOWERCASE_CHARS);
        
        int length = lengthSlider.getValue();
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        
        // Ensure password includes at least one character from each selected category
        if (lowercaseCheckbox.isSelected()) 
            password.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));
        
        if (uppercaseCheckbox.isSelected()) 
            password.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));
        
        if (numbersCheckbox.isSelected()) 
            password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        
        if (specialCharsCheckbox.isSelected()) 
            password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        
        // Fill the rest of the password
        for (int i = password.length(); i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        
        // Shuffle characters to avoid a predictable pattern
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int j = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        
        return new String(passwordArray);
    }
    
    private void evaluatePasswordStrength(String password) {
        double strength = 0.0;
        
        // Evaluate length
        if (password.length() >= 8) strength += 0.2;
        if (password.length() >= 12) strength += 0.2;
        if (password.length() >= 16) strength += 0.1;
        
        // Check character diversity
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        
        if (hasLower) strength += 0.1;
        if (hasUpper) strength += 0.1;
        if (hasDigit) strength += 0.1;
        if (hasSpecial) strength += 0.2;
        
        // Update strength indicator
        strengthBar.setValue((int)(strength * 100));
        
        if (strength < 0.4) {
            strengthLabel.setText("Slabă");
            strengthLabel.setForeground(Color.RED);
            strengthBar.setForeground(Color.RED);
        } else if (strength < 0.7) {
            strengthLabel.setText("Medie");
            strengthLabel.setForeground(Color.YELLOW);
            strengthBar.setForeground(Color.YELLOW);
        } else {
            strengthLabel.setText("Puternică");
            strengthLabel.setForeground(Color.GREEN);
            strengthBar.setForeground(Color.GREEN);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new generator();
            }
        });
    }
}