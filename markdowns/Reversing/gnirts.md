# Solution

## Description of the problem

The Gnirts challenge involves an application that checks if the inputted flag is correct, but the catch is that the flag
is not provided and must be obtained from the application itself.

## Solution

By using Ghidra, we can get access to the FlagChecker class. The main method to notice here is checkFlag().
The function CheckFlag takes in two arguments, the context and a flag string, and returns a Boolean value of true or
false depending on whether the flag is valid or not. The process of checking the flag involves a series of steps. Each
step checks a specific aspect of the flag and, if the check is successful, it proceeds to the next step. If any check
fails, the process returns false. By examining the code and understanding the checks, it is possible to retrieve each
part of the flag. The initial checkFlag method starts by verifying that the flag begins with "MOBISEC{" and ends
with "}". The core of the flag, which is the string between the curly braces, is then extracted and its length is
compared to 0x20. Subsequent methods, such as foo, are used to extract the separator "-" used in the flag and to split
the core into separate tokens. The number of tokens must be exactly five, and certain tokens are matched against regular
expressions using methods such as bim(), bum(), and bam(). The algorithm employed in this process is the MD5 hash
function, except the final step where the entire flag is hashed using the SHA function. Code obfuscation
techniques, such as the use of reflective methods, are also employed. To retrieve each individual token, the MD5 hash
can be reversed using a dictionary or brute-force attack. The final hash, calculated on the entire flag, serves to
confirm the validity of the flag.