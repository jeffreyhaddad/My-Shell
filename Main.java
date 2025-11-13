import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("$ ");
            input = sc.nextLine();
            if (handleExit(input)) {
                break;
            }
            if (handleEcho(input)) {
                continue;
            }
            if (handleType(input)) {
                continue;
            }
            System.out.println(input + ": command not found");
        }
    }

    static boolean handleExit(String input) {
        if (input.equals("exit 0") || input.equals("exit 1")) {
            return true;
        }
        return false;
    }

    static boolean handleEcho(String input) {
        if (input.startsWith("echo")) {
            System.out.println(input.substring(4).trim());
            return true;
        }
        return false;
    }

    static boolean handleType(String input) {
        if (input.startsWith("type")) {
            String arg = input.length() > 5 ? input.substring(5).trim() : "";
            if (arg.startsWith("exit")) {
                System.out.println("exit is a shell builtin");
            } else if (arg.startsWith("echo")) {
                System.out.println("echo is a shell builtin");
            } else if (arg.startsWith("type")) {
                System.out.println("type is a shell builtin");
            } else if (!arg.isEmpty()) {
                System.out.println(arg + ": not found");
            }
            return true;
        }
        return false;
    }
}
