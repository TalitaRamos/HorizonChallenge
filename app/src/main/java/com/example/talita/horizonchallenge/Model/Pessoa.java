package com.example.talita.horizonchallenge.Model;

public class Pessoa {
   // private String nome;
    private int cpf;
    private String sexo;
    //private String dataNascimento;

    public Pessoa() {
    }

    public Pessoa(int cpf, String sexo) {
        this.cpf = cpf;
        this.sexo = sexo;
    }

   /* public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }*/

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

   /* public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }*/
}
