# Solution

## Description of the problem

The flag is essentially given but the apk file has to be unpacked and the lib.so file has to be decompiled
in order to be able to retrieve the flag.

## Solution

I decompiled the code with Ghidra. By doing this, we can see the several condition of the FlagChecker function.
The `if ((((sVar2 == 12) && (param_4 == 31337)) && (*__s == 'n')) && (__s[11] == 'o'))` gives us the initial conditions.
Next, `iVar1 = strncmp("ative",local_2e,5);` checks whether the 5 fist characters of the string at local_2e is equal
to "ative".
Here, `if (((iVar1 == 0) && (__s[9] == '_')) && ((__s[6] == '_' && ((__s[7] == 'i' && (__s[8] == 's'))))))`, we obtain a
coiole
more characters. It is quite straightforward. And lastly, we check weather the last 2 letters of our string is equal
to "so" through
`iVar1 = strcmp("so",__s + 10);`. The first part of the flag is given by the __s string which gives us "native_is_so".
By combining this with the second part of the flag, which is 31337. However, we can see in the Java code that the length
has
to be of size 6. A 0 has been removed during decompilation which we have to add back. 

