# Solution

## Description of the problem
This problem requires you do develop and application which starts a SerialActivity in which you need to first analyse the code of the FlagContainer.java file and understand how to access the getFlag() method in it. 



## Solution

I used the startActivityForResult() and onActivityResult() in order to retrive the flag. A new intent is first created and its compontentName as the following: new ComponentName("com.mobisec.serialintent", "com.mobisec.serialintent.SerialActivity"). The activity is then started. In this case, the activity return a Flagcontainer object. This Flagcontainer object contains the flag and by inspecting the given java file, it is possible to see that we can get the flag my calling its "getFlag()" method. Hence, we need to create our own Flagcontainer class in with the same packakge name and class name as given. This is the cursial part. I put the FlagContainer file in a package named com.mobisec.serialintent and used the same file name as the already given one: FlagContainer.java. The system is only looking for mathces in the names of the class, package and serialVesionUID in order to determine if the classes are the same. I then use reflection to get the getFlag() method from the FlagContainer class using the getDeclaredMethod() method, and then set the setAccessible() flag to true to make the method accessible. Finally, the invoke() method is called on the Method object to invoke the getFlag() method on the flagContainer object. The result of the method call is then cast to a String and logged using the Log.d() method.
