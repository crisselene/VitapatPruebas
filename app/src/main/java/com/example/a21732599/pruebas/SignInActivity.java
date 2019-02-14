package com.example.a21732599.pruebas;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity {


    private Button reg;
    TextView titulo;


    //Authetication
    private FirebaseAuth fba;
    private FirebaseUser user;
    EditText etEmail;
    EditText etPassword;
    EditText etPassword2;
    EditText etUsuario;
    String strpwd;
    String stremail;
    String strpwd2;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //fuente
        Typeface face=Typeface.createFromAsset(getAssets(),"Lobster-Regular.ttf");

        //tutulo
        titulo = findViewById(R.id.titulo);
        titulo.setTypeface(face);

        //boton
        reg = findViewById(R.id.btnRegistrar);
        reg.getBackground().setAlpha(150);

        //AUTHENTICATION
        //la intancia al firebase:
        fba = FirebaseAuth.getInstance();

        //referencias a los componentes:
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etContraseña);
        etPassword2 = findViewById(R.id.etContraseña2);
        etUsuario = findViewById(R.id.etUsuario);


    }

    public void registrar(View v) {
        if(validarDatos()) {
            fba.createUserWithEmailAndPassword(stremail, strpwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = fba.getCurrentUser();
                                Toast.makeText(SignInActivity.this,
                                        getString(R.string.msj_registrado) + ": " +user.getEmail(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignInActivity.this, getString(R.string.msj_no_registrado), Toast.LENGTH_SHORT).show();

                            } } });

        }else{
            Toast.makeText(this,getString(R.string.msj_no_data), Toast.LENGTH_LONG).show();
        }

    }

    public void atras(View v) {
        Intent inte = new Intent(SignInActivity.this, LoginActivity.class);
        startActivity(inte);
    }

    private boolean validarDatos() {
        boolean datosOK = true;
        stremail = etEmail.getText().toString().trim();
        strpwd = etPassword.getText().toString().trim();

        //validación:
        if(stremail.isEmpty() || strpwd.isEmpty() || strpwd2.isEmpty() || usuario.isEmpty()) {
            datosOK = false;
        }

        if(strpwd!= strpwd2){
            datosOK = false;
        }
        return datosOK;
    }
}