# Solution

## Description of the problem

## Solution

```
byte[] fullKey = keyStr.getBytes(); // Take input key string and covert it so array of bytes
byte[] digest = hash(fullKey); // Array of hashed data in form of bytes is returned

// Extracts 3 elements from the digest as key
byte[] key = {digest[0], digest[digest.length / 2], digest[digest.length - 1]};

// Run the key consisting of 3 bytes through the hash function again.
byte[] currKey = hash(key);

byte[] currPt = flagStr.getBytes(); // Turn flag string into bytes
```
