# UMA Android Library

UMA Android Library is a library that implements the basic requests needed by a UMA Client
to communicate with the Resource Server and the Authorization Server.

## Project Structure
```bash
/app/src/  
├───androidTest  
│    └───java    
│        └───eu      
│            └───incognito  
│                └───umaandroid  
│                   └───util  
├───main  
│   ├───assets
│   ├───java
│   │   └───eu
│   │       └───incognito
│   │           └───umaandroid
│   │               ├───model
│   │               ├───op
│   │               └───util
│   └───res
│       ├───drawable
│       ├───drawable-v24
│       ├───layout
│       ├───mipmap-anydpi-v26
│       ├───mipmap-hdpi
│       ├───mipmap-mdpi
│       ├───mipmap-xhdpi
│       ├───mipmap-xxhdpi
│       ├───mipmap-xxxhdpi
│       └───values
└───test
    └───java
        └───eu
            └───incognito
                └───umaandroid
```
The source code can be found under /app/src/main/java/eu/incognito/umaandroid. Here we have the
UmaAndroidLibrary class which is a Singleton for the initialization of the library.
The **model** package contains the POJO classes mapped to the JSON response from
the server. The classes in the **util** package help build the logic of the code and
the classes in **op** package contain the HTTP requests. The UmaRequests class in this
package is a wrapper over the HttpRequestsHandler class since it implements specific
listeners for each type of request.

Under /app/src/main/assets can be found the JSON files containing the responses sent by the
server to the client. These JSON files are parsed in the UmaRequestsTest class 
(see package /src/app/androidTest/java/eu/incognito/umaandroid) and sent to the client as
responses to its requests. In order to test its functionality, the tests are leveraging 
an instance of Square's MockWebServer to simulate and API.
In /src/app/androidTest/java/eu/incognito/umaandroid/util, there is a JSON file parser used
to parse the json files into strings.
