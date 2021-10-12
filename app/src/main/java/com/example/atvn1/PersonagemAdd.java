package com.example.atvn1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonagemAdd extends AppCompatActivity {

    private EditText etNome;
    private Spinner spLevel;
    private Spinner spClasses;
    private Button btnSalvar;
    private String acao;
    private PersonagemInfo personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagensadd);

        etNome = findViewById(R.id.etNome);
        spLevel = findViewById(R.id.spLevel);
        spClasses = findViewById(R.id.spClasses);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            addPersonagens();
        }
    }

    private void addPersonagens(){
        int idChar = getIntent().getIntExtra("idChar", 0);
        personagem = PersonagemDAO.getPersonagemInfoById(this, idChar);

        etNome.setText( personagem.getNome() );
        String[] level = getResources().getStringArray(R.array.level);
        String[] classes = getResources().getStringArray(R.array.classes);

        for( int i = 0; i < level.length ; i++){
            if( personagem.getLevel().equals( level[i]  )){
                spLevel.setSelection( i );
                break;
            }
        }
        for( int i = 0; i < classes.length ; i++){
            if( personagem.getClasses().equals( classes[i]  )){
                spClasses.setSelection( i );
                break;
            }
        }
    }

    private void salvar(){
        String nome = etNome.getText().toString();

        if( nome.isEmpty() || spLevel.getSelectedItemPosition() == 0 || spClasses.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        }else {

            if(  acao.equals("inserir") ) {
                personagem = new PersonagemInfo("Lista Vazia ", "");
            }

            personagem.setNome( nome );
            personagem.setLevel( spLevel.getSelectedItem().toString()  );
            personagem.setClasses( spClasses.getSelectedItem().toString()  );

            if(  acao.equals("inserir") ) {
                PersonagemDAO.inserir(this, personagem);
                etNome.setText("");
                spLevel.setSelection(0, true);
                spClasses.setSelection(0, true);
            }else{
                PersonagemDAO.editar(this, personagem);
                finish();
            }
        }
    }

}
