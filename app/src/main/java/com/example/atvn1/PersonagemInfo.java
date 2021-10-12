package com.example.atvn1;

import androidx.appcompat.app.AppCompatActivity;

public class PersonagemInfo extends AppCompatActivity {

    public int id;
    public String nome, level, classes;

    public PersonagemInfo(String nome, String level, String classes) {
        this.nome = nome;
        this.level = level;
        this.classes = classes;
    }

    public PersonagemInfo(int id, String nome, String level, String classes) {
        this.id = id;
        this.nome = nome;
        this.level = level;
        this.classes = classes;
    }

    public PersonagemInfo(String lista_vazia_, String s) {
    }


    @Override
    public String toString() {
        return  nome + "  |  "  + level + "  |  " + classes ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
