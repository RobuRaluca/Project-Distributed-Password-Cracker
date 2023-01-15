import java.security.MessageDigest;
import java.util.Scanner;

public class PasswordCracker {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        // check if password is valid
        if (!isValidPassword(password)) {
            System.out.println("Invalid password. Password must be 5-6 characters long and contain only numbers and lowercase letters.");
            return;
        }

        // combine password with system data and generate MD5 hash
        String systemData = "secret_data";
        String combined = password + systemData;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(combined.getBytes());

        // send password hash and range to client
        int start = 1;
        int end = 10000;
        ClientWorker client = new ClientWorker(hash, start, end);
        Thread t = new Thread(client);
        t.start();
    }

    public static boolean isValidPassword(String password) {
        // check length
        if (password.length() < 5 || password.length() > 6) {
            return false;
        }

        // check if only contains numbers and lowercase letters
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isDigit(c) && !Character.isLowerCase(c)) {
                return false;
            }
        }

        return true;
    }

}