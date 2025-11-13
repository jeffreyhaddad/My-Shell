import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("$ ");
            input = sc.nextLine();
            if (input.equals("exit 0") || input.equals("exit 1")) {
                break;
            }
            if (input.startsWith("echo")) {
                System.out.println(input.substring(4).trim());
                continue;
            }
            System.out.println(input + ": command not found");
        }
    }
}
