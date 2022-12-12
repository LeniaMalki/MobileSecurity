# Solution

## Description of the problem

This problem requires you to bind to a service but also register the client in order to retreive
nbindableService.java file.the flag. Specifications about how to communicate with the service are
specified in the given nbindableService.java file.

## Solution

I solved this problem by using two Messenger objects: one for outgoing messages and one for ingoing.
These are used to comunicate with the service in which we want to bind to. The provided
UnbindableService.java file reveals which commands to use in order to display a message. These are
MSG_REGISTER_CLIENT = 1, MSG_SET_VALUE = 3 and MSG_GET_FLAG = 4. First of all, a ServiceConnection
object is created. This is used to interact with the main interfact of the service. With this, we
can communicate with the service using our messenger object. The handleMessage() method in the
IncomingHandler class will send back a specific message depending on which command we pass. In this
case, I only passes the REGISTER, SET and GET FLAG commands. In order to retrive the flag, I defined
another class called mHandler which was used to initiate the incoming messeger object. Inside the
handleMessage() method, it checks the value of the what field of the Message object to determine the
type of message that was received. If the message is of the type MSG_GET_FLAG, the code retrieves
the flag value from the Bundle object contained in the obj field of the Message and logs it.
