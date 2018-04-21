package org.viciousanddelicious.viciousdelicious;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by rahul on 21/4/18.
 */

public class FireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG ="MyFirebaseIIDService";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
//Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
