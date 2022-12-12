# Solution

## Description of the problem

This problem involves writing an Android app that exports a function to compute the SHA-256 hash of
a given file. The app must define an activity with an intent filter for the
com.mobisec.intent.action.HASHFILE action. When the system starts the activity, it will provide a
file path in the intent's URI, which the app can access using the Intent.getData() method. The file
will be stored on the device's SD card, so the app will need to request the appropriate permissions.
The app must compute the hash of the file and return it in a result intent under the "hash" key. The
system will log the content of the file, the expected hash, and the hash computed by the app. If the
expected hash and the app's computed hash match, the flag will be printed in the logs.

## Solution

First of all, the following permisisons: MANAGE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE and
WRITE_EXTERNAL_STORAGE need to be added to the AndroidManifest.xml file in order to solve this lab
as we will be reading and writing to a file located on the SDCard. An activity with the intent
filter "com.mobisec.intent.action.HASHFILE" also needs to be declared in the AndroidManifest.xml
file in order to start the activity and ask for the hasing file. The path to the file is obtained by
calling the getIntent().getData().toString() which first gets the intent that started the activity,
gets the data associated with the intent and converts the data to a string. The byte[] data =
Files.readAllBytes(path) will then read the contents of a file and storing them in a byte array. In
this code, path is a Path object that specifies the file to be read. The byte[] array, data, is then
used to store the contents of the file.

In order to calculate the hasing value, the MessageDigest class is used. This allows for creating a
message digest object that can be used to generate a digital signature for a given piece of data by
specifying the type of algorightm we want to use, in this case digest = MessageDigest.getInstance("
SHA-256"). We then compute the digital signature by calling byte[] encodedhash = digest.digest(data)
. The calculated has is then put in a result intent in hexadecimal format in order to retreive the
flag using the usual Log command. 
