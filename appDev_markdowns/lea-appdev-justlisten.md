# Solution

## Description of the problem
This problem requires you to make use of a broadcastreceiver which is to receive a predefined inent and action. The flag can be obtained under the "flag" tag. 


## Solution

A class named MyReceiver was created. This class extends Broadcastreceiver and overrides the function onReceive which takes as input a Context and Intent. The onReceive() method is called upon when whenever a Intent broadcast is to be received. By implementing the Log command in the onReceive() method and regestiering the broadcast receiver dynamically, it was possbile to obtain the flag. The intentFilter corresponding to com.mobisec.intent.action.FLAG_ANNOUNCEMENT had to be passed to the registerReceiver() method in order to capture this flag. 
