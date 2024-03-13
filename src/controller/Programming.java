package controller;

import view.Menu;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Programming extends Menu {
    ResourceBundle resourceBundle;

    static String[] mchoice = {
            "Vietnamese",
            "English",
            "Exit"};
    //protected Library week9;
    //ArrayList<Ebank> ebanklist = new ArrayList<>();

    public Programming() {
        super("Login Program", mchoice);
        //week9 = new Library();
        //ebanklist = new ArrayList<>();
    }
    @Override
    public void execute(int n) {
        if (n == mchoice.length) System.exit(0);

        switch (n) {
            case 1:
                setLocate("vi");
                login();
                break;

            case 2:
                setLocate("en");
                login();
                break;

            case 3:
                System.out.println("Bye~~~~");
                //System.exit(0);
        }
    }

    public void setLocate(String locate) {
        resourceBundle = ResourceBundle.getBundle(locate);
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(resourceBundle.getString("account_number_prompt"));
        String accountNumber = scanner.nextLine();
        String accountCheckResult = checkAccountNumber(accountNumber);
        while (!accountCheckResult.equals("valid")) {
            System.out.println(accountCheckResult);
            System.out.print(resourceBundle.getString("account_number_prompt"));
            accountNumber = scanner.nextLine();
            accountCheckResult = checkAccountNumber(accountNumber);
        }

        System.out.print(resourceBundle.getString("password_prompt"));
        String password = scanner.nextLine();
        String passwordCheckResult = checkPassword(password);
        while (!passwordCheckResult.equals("valid")) {
            System.out.println(passwordCheckResult);
            System.out.print(resourceBundle.getString("password_prompt"));
            password = scanner.nextLine();
            passwordCheckResult = checkPassword(password);
        }

        String captcha = generateCaptcha();
        System.out.println("Captcha: " + captcha);
        System.out.print(resourceBundle.getString("captcha_input_prompt"));
        String captchaInput = scanner.nextLine();
        String captchaCheckResult = checkCaptcha(captchaInput, captcha);
        while (!captchaCheckResult.equals("valid")) {
            System.out.println(captchaCheckResult);
            System.out.print(resourceBundle.getString("captcha_input_prompt"));
            captchaInput = scanner.nextLine();
            captchaCheckResult = checkCaptcha(captchaInput, captcha);
        }

        System.out.println("Login successful!");
    }

    public String checkAccountNumber(String accountNumber) {
        if (!Pattern.matches("\\d{10}", accountNumber)) {
            return resourceBundle.getString("invalid_account_number");
        }
        return "valid";
    }

    public String checkPassword(String password) {
        // Check if password length is between 8 and 31 characters
        if (password.length() < 8 || password.length() > 31) {
            return resourceBundle.getString("invalid_password");
        }

        // Check if password contains both letters and digits
        boolean containsLetter = false;
        boolean containsDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                containsLetter = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            }
        }

        if (!containsLetter || !containsDigit) {
            return resourceBundle.getString("invalid_password_content");
        }

        return "valid";
    }

    public String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char c = (char) (random.nextInt(26) + 'A');
            captcha.append(c);
        }
        return captcha.toString();
    }

    public String checkCaptcha(String captchaInput, String captchaGenerated) {
        if (!captchaInput.equalsIgnoreCase(captchaGenerated)) {
            return resourceBundle.getString("invalid_captcha");
        }
        return "valid";
    }
}
