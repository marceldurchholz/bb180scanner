Contributors
------------
[@MarcelDurchholz](https://github.com/MarcelDurchholz/)


BB180 Plugin for Cordova
==========================
!!! ATTENTION !!!
!!! NOT FOR PRODUCTION - STILL IN DEVELOPMENT !!!

This plugin will make accessible the BB180 Scanner via cordova.
 

Current Version: 1.0.0
----------------

Requirements
-------------
- Android 4 or higher
- Cordova 3.0 or higher

Installation
-------------
    cordova plugin add bb180scanner
    
Usage
------
    cordova.plugins.bb180scanner.open(
        filePath, 
        fileMIMEType, 
        {
            error : function(){ }, 
            success : function(){ } 
        } 
    );

Examples
--------
Open an APK install dialog:

    cordova.plugins.bb180scanner.open(
        '/sdcard/Download/gmail.apk', 
        'application/vnd.android.package-archive'
    );
    
Open a PDF document with the default PDF reader and optional callback object:

    cordova.plugins.bb180scanner.open(
        '/sdcard/Download/starwars.pdf', // You can also use a Cordova-style file uri: cdvfile://localhost/persistent/Download/starwars.pdf
        'application/pdf', 
        { 
            error : function(e) { 
                console.log('Error status: ' + e.status + ' - Error message: ' + e.message);
            },
            success : function () {
                console.log('file opened successfully'); 				
            }
        }
    );

Notes
------

- For properly opening _any_ file, you must already have a suitable reader for that particular file type installed on your device. Otherwise this will not work.


- [It is reported](https://github.com/marceldurchholz/bb180scanner/issues/2#issuecomment-41295793) that in iOS, you might need to remove `<preference name="iosPersistentFileLocation" value="Library" />` from your `config.xml`

- If you are wondering what MIME-type should you pass as the second argument to `open` function, [here is a list of all known MIME-types](http://svn.apache.org/viewvc/httpd/httpd/trunk/docs/conf/mime.types?view=co)


Additional Android Functions
-----------------------------
####The following functions are available in Android platform

###.uninstall(_packageId, callbackContext_)
Uninstall a package with its id.

    cordova.plugins.bb180scanner.uninstall('com.zynga.FarmVille2CountryEscape', {
        error : function(e) {
            console.log('Error status: ' + e.status + ' - Error message: ' + e.message);    
        },
        success : function() {
            console.log('Uninstall intent activity started.');
        }
    });

###.appIsInstalled(_packageId, callbackContext_)
Check if an app is already installed.

    cordova.plugins.bb180scanner.appIsInstalled('com.adobe.reader', {
        success : function(res) {
            if (res.status === 0) {
                console.log('Adobe Reader is not installed.');
            } else {
                console.log('Adobe Reader is installed.')
            }
        }
    });

LICENSE
--------
The MIT License (MIT)

Copyright (c) 2013 marceldurchholz - info@digitalverve.de

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
