
package com.example.applicationchatfirebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applicationchatfirebase.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class NovoUsuarioActivity extends AppCompatActivity {

    private EditText loginNovoUsuarioEditText;
    private EditText senhaNovoUsuario;
    private FirebaseAuth firebaseAuth; // é só um padrão do google esse mAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        loginNovoUsuarioEditText = findViewById(R.id.loginNovoUsuarioEditText);
        senhaNovoUsuario = findViewById(R.id.senhaNovoUsuario);
        firebaseAuth = FirebaseAuth.getInstance();  // ele tem internamente as informações de acesso a nuvem.

    }

    public void criarNovoUsuario(View v) {
        String login = loginNovoUsuarioEditText.getEditableText().toString();
        String senha = senhaNovoUsuario.getEditableText().toString();

        firebaseAuth.createUserWithEmailAndPassword(
                login,
                senha ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(
                        NovoUsuarioActivity.this,
                        getString(R.string.cadastro_funcionou,
                                authResult.getUser().toString()
                                ), Toast.LENGTH_SHORT
                ).show();

                finish();

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(
                        NovoUsuarioActivity.this,
                        getString(R.string.erro_inesperado
                        ),
                                 Toast.LENGTH_SHORT
                ).show();

                finish();

            }

        });




    }
}
