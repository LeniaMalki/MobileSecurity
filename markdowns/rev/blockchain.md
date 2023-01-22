# Solution

## Description of the problem

The app requires to be input of a KEY and FLAG. The goal is to find a combination of the KEY and FLAG that will result
in the
app displaying "Valid Flag". Once a valid FLAG is found, regardless of the associated KEY, it should be submitted to the
system to earn points. Brute force can be applied in order to retrieve the flag.

## Solution

By unpacking and decompiling, we can understand the following:

```
byte[] fullKey = keyStr.getBytes(); // Take input key string and covert it so array of bytes
byte[] digest = hash(fullKey); // Array of hashed data in form of bytes is returned

// Extracts 3 elements from the digest as key
byte[] key = {digest[0], digest[digest.length / 2], digest[digest.length - 1]};

// Run the key consisting of 3 bytes through the hash function again.
byte[] currKey = hash(key);

byte[] currPt = flagStr.getBytes(); // Turn flag string into bytes
```

In other words, instead of utilizing the entire key that is inputted, only three specific bytes (byte 0, byte length/2,
and byte length-1) are utilized. The decompiled code also shows that the key is hashed using the MD5 algorithm and
the resulting value is used as an encryption key in the AES algorithm.
We need to hash 3 bytes 10 times because the FlagChecker.checkFlag() only uses the 3 bytes of the key. In other words,
we need to produce 128 bites per byte. The encryption used in FlagChecker.java is AES which is a symmetric-key
encryption algorithm. We need thus to decrypt, using the inversion of the encryption algorithm.

And by using threads, we can eliminate the processing time of this brute force approach:

````
public static void main(String[] args) {
        IntStream.rangeClosed(-128, 127).parallel().forEach(i -> IntStream.rangeClosed(-128, 127).parallel().forEach(j -> IntStream.rangeClosed(-128, 127).parallel().forEach(k -> {
            byte[] currKey = new byte[]{(byte) k, (byte) j, (byte) i};
            try {
                getFlag(currKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        })));
    }
````

The final flag was converted to hex form, so we need to convert it back to bytes. 