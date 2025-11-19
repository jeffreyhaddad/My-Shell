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

# My Shell

A simple shell implementation in Java that supports basic shell commands and external program execution.

## Features

### Built-in Commands
- `exit` - Exit the shell
- `echo [args]` - Print text to stdout
- `pwd` - Print working directory
- `cd [path]` - Change directory (supports `~` and relative paths)
- `type [command]` - Display command type (builtin or executable path)

### Additional Features
- **External Command Execution:** Run any command available in your system's PATH
- **Single Quote Parsing:** Support for single-quoted strings in commands
- **Path Resolution:** Proper handling of absolute, relative, and home directory paths
- **Error Handling:** Redirects stdout and stderr from external commands

## Usage

Compile and run the program:

```sh
javac Main.java
java Main
```

Example session:

```
$ echo Hello World
Hello World
$ pwd
/Users/jeffreyhaddad/Desktop/myProjects/My-Shell
$ cd ~
$ pwd
/Users/jeffreyhaddad
$ type echo
echo is a shell builtin
$ type ls
ls is /bin/ls
$ exit
```

## Implementation Details

- **Tokenization:** Handles whitespace-separated tokens with support for single-quoted strings
- **Built-in Commands:** Implemented directly in Java
- **External Commands:** Executed using `ProcessBuilder` with proper I/O stream handling
- **Directory Navigation:** Uses Java's `File` API with canonical path resolution
- **PATH Lookup:** Searches system PATH to locate and execute external commands

## Implementation Details

- **Command Parsing:** Splits user input into command and arguments
- **Built-in Commands:** Handled directly by the shell
- **External Commands:** Executed via `Runtime.getRuntime().exec()`
- **Directory Navigation:** Uses Java's `File` API for path resolution
