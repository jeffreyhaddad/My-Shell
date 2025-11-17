import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            String[] words = input.split(" ");
            String command = words[0];
            String[] rest = Arrays.copyOfRange(words, 1, words.length);

            String result = String.join(" ", rest);

            if (Objects.equals(command, "exit")) {
                running = false;
            } else if (Objects.equals(command, "echo")) {
                System.out.println(result);
            } else if (Objects.equals(command, "pwd")) {
                System.out.println(System.getProperty("user.dir"));
            } else if (Objects.equals(command, "cd")) {
                changeDirectory(result);
            } else if (command.equals("type")) {
                System.out.println(type(result));
            } else {
                String typeResult = type(command);
                if (typeResult.endsWith(": not found")) {
                    System.out.println(typeResult);
                } else {
                    Process process = Runtime.getRuntime().exec(input.split(" "));
                    process.getInputStream().transferTo(System.out);
                }
            }

        }
        scanner.close();
    }

    public static String type(String command) {
        String[] commands = { "exit", "echo", "type", "pwd", "cd" };

        for (int i = 0; i < commands.length; i++) {
            if (Objects.equals(commands[i], command)) {
                return command + " is a shell builtin";
            }
        }

        String path_commands = System.getenv("PATH");
        if (path_commands != null && !path_commands.isEmpty()) {
            String[] path_command = path_commands.split(File.pathSeparator);
            for (int i = 0; i < path_command.length; i++) {
                File file = new File(path_command[i], command);
                if (file.isFile() && file.canExecute()) {
                    return command + " is " + file.getAbsolutePath();
                }
            }
        }

        return command + ": not found";
    }

    public static void changeDirectory(String destination) throws IOException {
        File target = new File(destination);

        if (!target.isAbsolute()) {
            target = new File(System.getProperty("user.dir"), destination);
        }
        target = new File(target.getAbsolutePath()).getCanonicalFile();

        if (target.isDirectory()) {
            System.setProperty("user.dir", target.getAbsolutePath());
        } else {
            System.out.println("cd: " + destination + ": No such file or directory");
        }
    }
}