import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            String[] words = tokenize(input);
            if (words.length == 0)
                continue; // ignore empty input
            String command = words[0];
            String[] rest = Arrays.copyOfRange(words, 1, words.length);
            String result = String.join(" ", rest);

            if (Objects.equals(command, "exit")) {
                running = false;
            } else if (Objects.equals(command, "echo")) {
                System.out.println(String.join(" ", rest));
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
                    String[] parts = new String[rest.length + 1];
                    parts[0] = command;
                    System.arraycopy(rest, 0, parts, 1, rest.length);
                    ProcessBuilder pb = new ProcessBuilder(parts);
                    pb.directory(new File(System.getProperty("user.dir")));
                    Process process = pb.start();
                    process.getInputStream().transferTo(System.out);
                    process.getErrorStream().transferTo(System.err);
                    process.waitFor();
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
        if (destination.equals("~")) {
            destination = System.getenv("HOME");
        } else if (destination.startsWith("~/")) {
            destination = System.getenv("HOME") + destination.substring(1);
        }

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

    public static String[] tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        int n = input == null ? 0 : input.length();

        while (i < n) {
            while (i < n && Character.isWhitespace(input.charAt(i))) {
                i++;
            }
            if (i >= n)
                break;

            StringBuilder token = new StringBuilder();
            while (i < n && !Character.isWhitespace(input.charAt(i))) {
                if (input.charAt(i) == '\'') {
                    int start = i + 1;
                    int end = input.indexOf('\'', start);
                    if (end == -1) {
                        token.append(input.substring(start));
                        i = n;
                    } else {
                        token.append(input.substring(start, end));
                        i = end + 1;
                    }
                } else if (input.charAt(i) == '"') {
                    int start = i + 1;
                    int end = input.indexOf('"', start);
                    if (end == -1) {
                        token.append(input.substring(start));
                        i = n;
                    } else {
                        token.append(input.substring(start, end));
                        i = end + 1;
                    }
                } else {
                    token.append(input.charAt(i));
                    i++;
                }
            }
            if (token.length() > 0) {
                tokens.add(token.toString());
            }
        }
        return tokens.toArray(new String[0]);
    }

}