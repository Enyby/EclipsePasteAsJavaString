# EclipsePasteAsJavaString
Eclipse Plugin to paste text as Java string (with escape multi-line) when Ctrl+Shift+V pressed

# Install
Drop .jar file in Eclipse plugins folder and restart Eclipse.
https://github.com/Enyby/EclipsePasteAsJavaString/blob/master/plugins/com.pastejava_1.0.0.jar?raw=true

# Usage
Press Ctrl+Shift+V for insert text as Java string.

# Example
Paste as usual via Ctrl+V:
```
some text	with tabs
and new 
lines
```
Paste as Java string via Ctrl+Shift+V
```
"some text\twith tabs\r\n" +
"and new \r\n" +
"lines"
```
