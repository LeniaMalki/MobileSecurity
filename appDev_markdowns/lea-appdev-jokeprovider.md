# Solution

## Description of the problem

This problem requires you to access a predefined content provider which manages access to a repository of data. The name if this specific provider is "Joke". We are specifically looking for jokes authored by reyammer. A concatination of this jokes makes up the flag. 

## Solution

As we are dealing with a content provider, we can make use of a Cursor object in order to access and iterate through the data. We can query the content provider by specifying the URL variable, in this case content://"com.mobisec.provider.Joke"/jokes. Once having accessed the content provider, we can interate through it with the use of the cursor which was defined earlier. I took a short cut and chose to print everything of the content provider in a do-while loop and printing the values of the specified columns to the log. In each iteration of the loop, the code retrieves the values of the id, author, and joke columns from the current row of the Cursor and appends them to the StringBuilder. 

By looking at the log resut from the APK submission, I was able to contcat the flag manually. 
