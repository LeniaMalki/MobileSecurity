# Solution

## Description of the problem

Create an application which, based on change of location of the user, returns the current location of the user. 


## Solution
As we are working with GPS-locations, the following permissions have to be added to the AndroidManifest.xml file: ACCESS_COARSE_LOCATION and ACCESS_COARSE_LOCATION. Furthermore, an service is declared in the same file, with the intent filter for the action com.mobisec.intent.action.GIMMELOCATION. By using LocationManger, you can get access to the device's location information. It allows your app to obtain the device's current location, as well as to register for and receive location updates. By using the onStartCommand() method, which is called upon by the system every time a client start the service, we can register a LocationListener to receive location updates from the LocationManager. This LocationListener will be notified whenever the device's location changes. When the LocationListener receives a new location update, it creates a new Intent with the action "com.mobisec.intent.action.LOCATION_ANNOUNCEMENT" and puts the Location object as an extra with the key "location". The Intent is then broadcast to any interested receivers, using the sendBroadcast() method.
