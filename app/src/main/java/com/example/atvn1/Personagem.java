package com.example.atvn1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Personagem extends AppCompatActivity {

    private ListView lvPersonagens;
    private List<PersonagemInfo> Personagens;
    private ArrayAdapter adapter;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagens);

        FloatingActionButton fabP = findViewById(R.id.fabP);
        fabP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Personagem.this, PersonagemAdd.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvPersonagens = findViewById(R.id.lvPersonagens);
        lvPersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Personagem.this, PersonagemAdd.class);
                intent.putExtra("acao", "editar");
                int idPersonagem = Personagens.get(i).getId();
                intent.putExtra("idPersonagem", idPersonagem);
                startActivity(intent);
            }
        });

        lvPersonagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir(i);
                return true;
            }
        });
        carregarPersonagens();
    }

    private void carregarPersonagens() {

        Personagens = PersonagemDAO.getPersonagemInfo(this);

        if (Personagens.size() == 0){
            PersonagemInfo fake = new PersonagemInfo("Lista Vazia ", "");
            Personagens.add(fake);
            lvPersonagens.setEnabled(false);
        }
        else {
            lvPersonagens.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Personagens);
        lvPersonagens.setAdapter(adapter);

    }

    private void excluir(int i) {

        PersonagemInfo selecPersonagem = Personagens.get(i);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Quer mesmo deletar este item?" + selecPersonagem.getNome() + "? ");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PersonagemDAO.excluir(Personagem.this, selecPersonagem.getId());
                carregarPersonagens();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarPersonagens();
    }
}
