#README
To add to an existing project, run the following command when  inside the root directory of the project:
```
cordova plugin add https://github.com/nixplay/crypto.git
```

To use simply call from inside any javascript code block:
```
Crypto.md5(`filePath`,` hashSuccess`, `hashError`);
```

where `filePath` is the path of the file, `hashSuccess` is the callback function that needs to be called when the method runs successfully and `hashError` is the method to run in case of error.

The `hashSuccess` function will be passed a parameter which is the md5 of the file.
