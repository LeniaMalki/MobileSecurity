# Solution

## Description of the problem
In this problem, we have to get access to the java classes in order to learn about the flag. Once we have access,
we have to analyze the different classes. This problem can easily be solved by reading the different conditions 
for the flag specified in the FlagChecker.java class. The flag is essentially given. 

## Solution
Decompile the apk file. Here, we notice that the Main function calls FlagChecker. It is enough to use mobsf.live
in order to obtain the code. In FlagChecker.java, we can put the together bits of the flag. 
The flag is given by the below condition in which several other methods are called. 
````
if (flag.startsWith("MOBISEC{") 
&& new StringBuilder(flag).reverse().toString().charAt(0) == '}' 
&& flag.length() == 35 
&& flag.toLowerCase().substring(8).startsWith("this_is_") 
&& new StringBuilder(flag).reverse().toString().toLowerCase().substring(1).startsWith(ctx.getString(R.string.last_part)) 
&& flag.charAt(17) == '_' 
&& flag.charAt((int) (getY() * Math.pow(getX(), getY()))) == flag.charAt(((int) Math.pow(Math.pow(2.0d, 2.0d), 2.0d)) + 1) 
&& bam(flag.toUpperCase().substring(getY() * getX() * getY(), (int) (Math.pow(getZ(), getX()) - 1.0d))).equals("ERNYYL") 
&& flag.toLowerCase().charAt(16) == 'a' 
&& flag.charAt(16) == flag.charAt(26) 
&& flag.toUpperCase().charAt(25) == flag.toUpperCase().charAt(26) + 1)
````

By reading these conditions, we can see that the flag starts with `MOBISEC{` and so on. `The getX(), getY() and getZ()`
are just returning constant values. The `bam()`function is basically applying a ROT13 (Caesar Cipher) encryption algorithm
to the input string. I.e. it replaces a letter with the letter thirteen spaces down the alphabet.
To get the `ctx.getString(R.string.last_part)` we must access the string.xml file. Putting all the conditions together,
we are able to get the flag. 