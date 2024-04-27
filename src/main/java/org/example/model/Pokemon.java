package org.example.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id", "nome", "tipo", "hp", "habilidades", "fraqueza", "cor"})
public class Pokemon {

    public Integer id;
    public String nome;
    public String tipo;
    public Integer hp;
    public Integer habilidades;
    public String fraqueza;
    public String cor;

    public Pokemon() {
    }

    public Pokemon(Integer id, String nome, String tipo, Integer hp, Integer habilidades, String fraqueza, String cor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.hp = hp;
        this.habilidades = habilidades;
        this.fraqueza = fraqueza;
        this.cor = cor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Integer habilidades) {
        this.habilidades = habilidades;
    }

    public String getFraqueza() {
        return fraqueza;
    }

    public void setFraqueza(String fraqueza) {
        this.fraqueza = fraqueza;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
