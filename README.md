# My Shell

A simple shell implementation in Java that supports basic shell commands.

## Features

- **Built-in Commands:**
  - `exit` - Exit the shell
  - `echo` - Print text to stdout
  - `pwd` - Print working directory
  - `cd` - Change directory
  - `type` - Display command type (builtin or executable path)

- **External Commands:** Execute any command available in your system's PATH

## Usage

Compile and run the program:

```sh
javac Main.java
java Main
```

Then use the shell prompt to enter commands:

```
$ echo Hello World
Hello World
$ pwd
/Users/jeffreyhaddad/Desktop/myProjects/My-Shell
$ cd ..
$ exit
```

## Implementation Details

- **Command Parsing:** Splits user input into command and arguments
- **Built-in Commands:** Handled directly by the shell
- **External Commands:** Executed via `Runtime.getRuntime().exec()`
- **Directory Navigation:** Uses Java's `File` API for path resolution
