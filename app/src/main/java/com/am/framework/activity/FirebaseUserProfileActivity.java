package com.am.framework.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.am.framework.R;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.sdk.android.core.TwitterCore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseUserProfileActivity extends BaseActivity {

    private static final String TAG = FirebaseUserProfileActivity.class.getSimpleName();

    @BindView(R.id.tv_user_info)
    TextView mUserInfoTextView;
    @BindView(R.id.iv_user_image)
    ImageView userImageImageView;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth mFirebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_user_profile);
        ButterKnife.bind(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (not-null) and update UI accordingly.
        if (mCurrentUser == null) {
            FirebaseUserProfileActivity.this.finish();
            Toast.makeText(this, "User is Not Signed in [Firebasse]", Toast.LENGTH_SHORT).show();
        } else {
            mUserInfoTextView.append("Username: " + mCurrentUser.getDisplayName() + "\n\nEmail: " +
                    mCurrentUser.getEmail() + "\n\nIdToken: " +
                    mCurrentUser.getIdToken(true) + "\n\n"
                    + "\n\nPhotoUrl: " + mCurrentUser.getPhotoUrl()
                    + "\n\nLastSignInTimestamp: " + mCurrentUser.getMetadata().getLastSignInTimestamp());

            Log.d(TAG, "Uid:" + mCurrentUser.getUid());
            Log.d(TAG, "Email:" + mCurrentUser.getEmail());
            Log.d(TAG, "Token:" + mCurrentUser.getIdToken(true).toString());
            Log.d(TAG, "PhotoUrl:" + mCurrentUser.getPhotoUrl());
            Log.d(TAG, "UserName:" + mCurrentUser.getDisplayName());
            Log.d(TAG, "ProviderId:" + mCurrentUser.getProviderId());
            Log.d(TAG, "CreationTimestamp:" + mCurrentUser.getMetadata().getCreationTimestamp());
            Log.d(TAG, "LastSignInTimestamp:" + mCurrentUser.getMetadata().getLastSignInTimestamp());
            Glide.with(this).load(mCurrentUser.getPhotoUrl()).into(userImageImageView);
        }

        // [START Configure Google Sign In]
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
        // [END Configure Google Sign In]
    }

    //TODO: Add a condition to find the user provider and log out only form it
    @Override
    public void onBackPressed() {
        //Sign Out From Firebase
        mFirebaseAuth.signOut();
        //Sign Out From Google
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                task -> Toast.makeText(this, "Signed Out From Google",
                        Toast.LENGTH_SHORT).show());
        //Sign Out From Twitter
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        //Sign Out From Facebook
        LoginManager.getInstance().logOut();
        super.onBackPressed();
    }
}
