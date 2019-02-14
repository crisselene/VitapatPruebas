package com.example.a21732599.pruebas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView titulo;

    //Authetication
    private FirebaseAuth fba;
    private FirebaseUser user;
    EditText etEmail;
    EditText etpassword;
    String strpwd;
    String stremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //DISEÑO
        //no barra full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Typeface face=Typeface.createFromAsset(getAssets(),"Lobster-Regular.ttf");

        //botón entrar
        Button btn = (Button) findViewById(R.id.btnRegistrar);
        btn.getBackground().setAlpha(150);

        //botón ayuda
        final TextView txtSub = (TextView)findViewById(R.id.textViewSubrayado);
        txtSub.setText(Html.fromHtml("<u>Ayuda</u>"));
        txtSub.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //Aqui el codigo que queremos que ejecute al ser pulsado
            }
        });

        //titulo aplicación
        titulo = findViewById(R.id.titulo);
        titulo.setTypeface(face);



        //AUTHENTICATION
        //la intancia al firebase:
        fba = FirebaseAuth.getInstance();

        //referencias a los componentes:
        etEmail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.password);




    }

    public void acceder(View v) {
        if(validarDatos()) {
            fba.signInWithEmailAndPassword(stremail, strpwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = fba.getCurrentUser();
                                Intent i = new Intent(LoginActivity.this, ActivityPrueba.class);
                                i.putExtra("USER", user.getEmail());
                                startActivity(i);
                                Toast.makeText(LoginActivity.this, getString(R.string.msj_logado),Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this, getString(R.string.msj_no_accede),Toast.LENGTH_SHORT).show();
                            } } });

        }else{
            Toast.makeText(this,getString(R.string.msj_no_data), Toast.LENGTH_LONG).show();
        }
    }

    public void crearCuenta(View v) {
        Intent inte = new Intent(LoginActivity.this,SignInActivity.class);
        startActivity(inte);
    }

    private boolean validarDatos() {
        boolean datosOK = true;
        stremail = etEmail.getText().toString().trim();
        strpwd = etpassword.getText().toString().trim();

        //validación:
        if(stremail.isEmpty() || strpwd.isEmpty()) {
            datosOK = false;
        }
        return datosOK;
    }
}