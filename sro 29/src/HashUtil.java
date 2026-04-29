import java.util.HashMap;
import java.util.Scanner;
import java.security.MessageDigest;

public class AuthSystem {

    static HashMap<String, String> users = new HashMap<>();

    // Хэш функция
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Тіркелу
    public static void register(String username, String password) {
        users.put(username, hashPassword(password));
        System.out.println("Тіркелді!");
    }

    // Кіру
    public static void login(String username, String password) {
        String hashed = hashPassword(password);

        if (users.containsKey(username) && users.get(username).equals(hashed)) {
            System.out.println("Кіру сәтті!");
        } else {
            System.out.println("Қате логин немесе пароль!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Тіркелу, 2 - Кіру");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Логин: ");
        String username = sc.nextLine();

        System.out.print("Пароль: ");
        String password = sc.nextLine();

        if (choice == 1) {
            register(username, password);
        } else {
            login(username, password);
        }
    }
}
