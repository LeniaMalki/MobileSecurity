# Solution

## Description of the problem
This problem requires you to connect to a HTTP server listening on 10.0.2.2:31337. Once you connect to the server and is succesful in receiving a response, it is required to make a Http request with the correct input format and content is required in order to solve this problem. 



## Solution

First of all, the android permissions INTERNET and ACCESS_NETWORK_STATE had to be added to the manifest in order to solve this problem. In order to connect to the server, a Request was sent using a OkHttpClient. When you first connect, you will get a new URL indicating where the flag is: "http://10.0.2.2:31337/flag">here</a>". When you pass the new URL to the newCall() method of the client, it responds with a JSON form, defining how to get the flag. By passing a RequestBody composed of the the inputs va;1, oper, val2 and answer to the post() method of the request, it it possible to retrive the flag once in the onResponse() method. In order to prin the flag, the Log command is used with the ResponseBody of the request. 
