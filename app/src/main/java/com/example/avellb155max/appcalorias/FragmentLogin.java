package com.example.avellb155max.appcalorias;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.avellb155max.appcalorias.Classes.DadosUsuario;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by gustavo on 07/11/15.
 */
public class FragmentLogin extends Fragment {

    private CallbackManager callbackManager;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    Button loginOffline;
    private static final String TAG = "NOVA";

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code
                            Log.v("LoginActivity", response.toString());
                        }
                    });

            List<DadosUsuario> items = DadosUsuario.listAll(DadosUsuario.class);

            if (items.isEmpty()) {
                // redireciona para o cadastro de peso e altura
                Intent i = new Intent(getActivity(), CadastrarDadosPessoais.class);
                startActivity(i);
            } else {
                // redireciona para o app
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public FragmentLogin() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            Log.d(TAG, ">>>" + "Signed Out");
        } else {
            Log.d(TAG, ">>>" + "Signed In");
        }

        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());

        if (com.facebook.BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        callbackManager = CallbackManager.Factory.create();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmet_login, container, false);
        loginOffline = (Button) rootView.findViewById(R.id.login_offline);
        loginOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CadastrarDadosPessoais.class);
                startActivity(i);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);

        loginButton.setReadPermissions("user_friends, public_profile, email, user_birthday, user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void displayMessage(Profile profile) {
        if (profile != null) {
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }
}

/*
* private static final String TAG = "NOVA";
    //    private static final String TAG = ';
    private TextView info;
    private LoginButton loginButton;
    private Usuario user;
    //
//    private Facebook facebook;
//    private AsyncFacebookRunner mAsyncRunner;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        if (accessToken == null) {
//            Log.d(TAG, ">>>" + "Signed Out");
//        } else {
//            Log.d(TAG, ">>>" + "Signed In");
//        }

        FacebookSdk.sdkInitialize(getApplicationContext());

        if (com.facebook.BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_splash);

        final Intent intent = new Intent(this, Main2Activity.class);
        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);

//       loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                Log.v("LoginActivity", response.toString());
                            }
                        });
//                Bundle parameters = new Bundle();
//                parameters.putString(
//                        "fields", "id,name,email,gender, birthday");
//                request.setParameters(parameters);
//                request.executeAsync();
//                info.setText(parameters.toString());

            }

            @Override
            public void onCancel() {
                // App code
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        if(LoginResult)

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
*
* */