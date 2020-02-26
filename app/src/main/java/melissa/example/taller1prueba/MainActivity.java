package melissa.example.taller1prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;
    Button login;
    EditText usuario, password;

    CheckBox showpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.edtUsuario);
        password = (EditText) findViewById(R.id.edtPassword);
        showpassword= findViewById(R.id.chkVisibility);
        login = findViewById(R.id.btnLogin);


        //CHECKBOX VISIBILITY


        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

//SIGN IN NORMALEISHON
/*
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"Boton Activado", Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(usuario.getText())){
                    usuario.setHint("Usuario Incorrecto");
                }
                else{
                    if(TextUtils.isEmpty(password.getText())){
                        password.setHint("Password Incorrecto");
                    }
                    else{
                        if(usuario.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {
                            //Toast.makeText(getApplicationContext(),"Usuario Correcto", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                            i.putExtra("user",usuario.getText().toString());
                            startActivity(i);

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
*/

//SIGN IN WITH GOOGLE
        signin = findViewById(R.id.sign_in_button);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }
//VALIDACION DE CAMPOS
    public void Login(View view) {
        String usu = usuario.getText().toString();
        String pass = password.getText().toString();
        if (usu.length() == 0) {

            Toast.makeText(this, "Ingrese Email", Toast.LENGTH_SHORT).show();
        }

        if (pass.length() == 0) {
            Toast.makeText(this, "Ingrese Password", Toast.LENGTH_SHORT).show();
        }

        if ( usu.length() != 0 && pass.length() != 0) {
            if(usuario.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {
                //Toast.makeText(getApplicationContext(),"Usuario Correcto", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                i.putExtra("user",usuario.getText().toString());
                startActivity(i);

            }else{
                Toast.makeText(getApplicationContext(),"Usuario Incorrecto", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //Metodo de boton Registration
    public void Registration (View view){
        Intent siguiente = new Intent(this, RegistrationActivity.class);
        startActivity(siguiente);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.v("Error", "signInResult:failed code=" + e.getStatusCode());//cambié el log.w por log.v para poder ooner un string de tag
        }
    }

}
