package com.am.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUserMetadata;
import com.twitter.sdk.android.core.TwitterCore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseUserProfileActivity extends AppCompatActivity {
    private static final String TAG = "TestMainActivityTAG";
    FirebaseUser currentUser;
    FirebaseAuth firebaseAuth;
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.iv_test)
    ImageView ivTest;
    private GoogleSignInClient mGoogleSignInClientForApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_user_profile);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (currentUser == null) {
            FirebaseUserProfileActivity.this.finish();
        } else {
            tvTest.append("Username: " + currentUser.getDisplayName() + "\n\nEmail: " +
                    currentUser.getEmail() + "\n\nIdToken: " +
                    currentUser.getIdToken(true) + "\n\n"
                    + "\n\nPhotoUrl: " + currentUser.getPhotoUrl()
                    + "\n\nLastSignInTimestamp: " + currentUser.getMetadata().getLastSignInTimestamp());

            Log.d(TAG, "UserName:" + currentUser.getDisplayName());
            Log.d(TAG, "UserEmail:" + currentUser.getEmail());
            Log.d(TAG, "UserToken:" + currentUser.getIdToken(true).toString());
            Log.d(TAG, "PhotoUrl:" + currentUser.getPhotoUrl());
            Log.d(TAG, "getProviderId:" + currentUser.getProviderId());
            Log.d(TAG, "getUid:" + currentUser.getUid());
            Log.d(TAG, "CreationTimestamp:" + currentUser.getMetadata().getCreationTimestamp());
            Log.d(TAG, "LastSignInTimestamp:" + currentUser.getMetadata().getLastSignInTimestamp());
            Glide.with(this).load(currentUser.getPhotoUrl()).into(ivTest);
        }

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]
        mGoogleSignInClientForApi = GoogleSignIn.getClient(this, gso);


        FirebaseUserMetadata metadata = currentUser.getMetadata();
        if (metadata != null) {
            metadata.getCreationTimestamp();
            metadata.getLastSignInTimestamp();
        }


    }

    //TODO: Add a condition to find the user provider and log out only form it
    @Override
    public void onBackPressed() {
        firebaseAuth.signOut();
        //Google
        mGoogleSignInClientForApi.signOut().addOnCompleteListener(this,
                task -> {
                    Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
                });

        //Twitter
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        //Facebook
        LoginManager.getInstance().logOut();
        super.onBackPressed();

    }

}
