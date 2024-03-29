# Solution

## Description of the problem

Make use of both dynamical and static decompilation in order to get the flag. The flag is contained
in the application itself but a lot of different functions are called, encrypted files and strings
are decrypted in multiple ways.

## Solution

In the Manifest.xml:
`<uses-permission android:name="android.permission.INTERNET" />`

In `setOnClickListener` of MainActivity.java
`result = doStuff.start(MainActivity.this, flag);`
This returns a boolean which states if the flag is correct or not. With this information, we can
assume that the flag is already contained in the code. Let's look
at `doStuff.start(MainActivity.this, flag)`:

````
public boolean start(Context ctx, String flag) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContext(ctx);
        setUserInput(flag);
        String path = dc(gu());
        da(path);
        return lc(path);
    }
````

The dc functions creates a HTTP connection. It then creates a new File object, which represents a
file or directory in the file system. The file is located in the code cache directory of the current
context, and its name is determined by the result of calling the gf() method. Data is continuously
read from the URl and returned is the absolute path of the file. The gf() method just returns a
string my calling the ds method with the argument `QLrdlqkhOkxIe5FEfpCLWw`. The ds method
essentially tries to decrypt the string that has been encoded using AES encryption and returns the
decrypted string in a byte array which is then converted to a string and returned by the method. All
of gu(), gf(), gm() and gc() calls ds() with different input strings and return strings themselves.

The absolute file path returned is passed as a parameter to the da() function which in turn attempts
to decrypt the file that has been encoded using a simple XOR encryption. It creates a new File
object with the same path, and writes an array containing the decrypted bytes to the file.

Finally, the lc(path) loads the class `com.mobisec.stage1.LoadImage` by using a `DexClassLoader`.
The class path/name is not explicitly specified but is retrieved by the gc(). It then gets the
declared method `load` from the loaded class. The load(flag) function decrypts a file by the name
logo.png which in turn produces another apk which, when dynamically decompiled, gives us the flag. 