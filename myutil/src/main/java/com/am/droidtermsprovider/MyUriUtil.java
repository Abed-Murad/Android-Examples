package com.am.droidtermsprovider;

import android.net.Uri;
import android.util.Log;

public class MyUriUtil {

    private static final String TAG = "ttt";

    public static void main(String[] args) {
        Uri uri = Uri.parse("http://example.com/foo/bar/42?param=true");

        getUriParts(uri);
    }

    private static void getUriParts(Uri uri) {
        String token = uri.getLastPathSegment();
        String getEncodedPath = uri.getEncodedPath();
        String getClass = uri.getClass().toString();
        String getPath = uri.getPath();
        String getAuthority = uri.getAuthority();
        String getPathSegments = uri.getPathSegments().toString();

        Log.d(TAG, "onCreate:lastPathSegment:" + token);
        Log.d(TAG, "onCreate:getEncodedPath:" + getEncodedPath);
        Log.d(TAG, "onCreate:getClass:" + getClass);
        Log.d(TAG, "onCreate:getPath:" + getPath);
        Log.d(TAG, "onCreate:getAuthority:" + getAuthority);
        Log.d(TAG, "onCreate:getPathSegments:" + getPathSegments);
    }


/*      Output
        08-31 19:54:14.926 635-635/com.abdallahmurad.test0011 D/ttt: onCreate:lastPathSegment:42
        08-31 19:54:14.926 635-635/com.abdallahmurad.test0011 D/ttt: onCreate:getEncodedPath:/foo/bar/42
        08-31 19:54:14.926 635-635/com.abdallahmurad.test0011 D/ttt: onCreate:getClass:class android.net.Uri$StringUri
        08-31 19:54:14.926 635-635/com.abdallahmurad.test0011 D/ttt: onCreate:getPath:/foo/bar/42
        08-31 19:54:14.926 635-635/com.abdallahmurad.test0011 D/ttt: onCreate:getAuthority:example.com
        08-31 19:54:14.926 635-635/com.abdallahmurad.test0011 D/ttt: onCreate:getPathSegments:[foo, bar, 42]
*/

}


