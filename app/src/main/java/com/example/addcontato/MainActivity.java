package com.example.addcontato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtTelefone;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtNome.getText().toString().isEmpty() &&
                        !edtEmail.getText().toString().isEmpty() &&
                        !edtTelefone.getText().toString().isEmpty()) {

                    //configurar a intencao
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                    //inserir nome
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, edtNome.getText().toString());
                    //inserir Email
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, edtEmail.getText());
                    //inserir telefone
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, edtTelefone.getText());

                    //enviar a intent
                    if(intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Nenhum aplicativo que suporta essa accao!", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(),"Por favor preencha os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}