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
- **Output Redirection:** Support for `>` and `1>` redirection to files
- **Quote Parsing:** Support for single-quoted, double-quoted, and escaped strings in commands
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
$ echo "test" > output.txt
$ exit
```

## Implementation Details

- **Tokenization:** Handles whitespace-separated tokens with support for single-quoted, double-quoted, and escaped strings
- **Built-in Commands:** Implemented directly in Java
- **External Commands:** Executed using `ProcessBuilder` with proper I/O stream handling
- **Directory Navigation:** Uses Java's `File` API with canonical path resolution
- **PATH Lookup:** Searches system PATH to locate and execute external commands
- **Output Redirection:** Supports `>` and `1>` operators for file redirection
