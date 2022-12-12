# Solution

## Description of the problem

This problem requires you to read the Manifext.xml of the application and obtain the package- as well as class names of the different activities specified there. By starting each one of the activities, it is possible to obtain parts of the flag.

## Solution

For part one and part two of the flag, you need only to implement the startActivityForResult() method with intent composed of correct package name and class name of the two different activites. The startActivityForResult() enables you to getting a response fron an activirty. It is possible to distinguish between the different activities by passing a unique requestCode to each method call. The requestCode will be returned once onActivityResult is called upon. Moreover, an intent is also retured, containing result data. For part one and two, it was enough to just start the activites and ask for the result using Log.d("MOBISEC", intent.getStringExtra("flag")). 

For part three, the flag was contained in a Bundle which you had to pass through in order to get the flag. Part four required you to pass through nested bundles, each with its own "hint" on how to get to the flag. In order to retreive the flag, a for-loop was implemented, looping through each keyset of each of the bundles. 
