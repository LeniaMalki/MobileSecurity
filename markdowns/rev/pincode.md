# Solution

## Description of the problem
A pin has to be passed to the PinChecker.checkPin() function as below: 
````
boolean pinValid = PinChecker.checkPin(MainActivity.this, pin);
```` 
If the pin is valid, the flag is retrieved through getFlag().
The problem here is in the following inChecker.checkPin() method:
````
for (int i = 0; i < 25; i++) {
for (int j = 0; j < 400; j++)
````
The pin has to be of size 6 but it is hashed 25*400 times using MD5 message-digest algorithm which produces a 128-bit hash value.
It is then compared to `d04988522ddfed3133cc24fb6924eae9`.
              

## Solution
This time, the flag cannot be simply "read" out from the code. We have to use bruteforce in order to obtain the 
25*400 different combinations of a string of size 6. By running the script for some hours, the correct pin
is found and thus we can retrieve the flag. 