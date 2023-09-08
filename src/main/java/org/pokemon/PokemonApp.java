package org.pokemon;

import org.pokemon.dal.PokemonFileDAO;

public class PokemonApp {
    public static void main(String[] args) {
        PokemonFileDAO pokemonFileDAO = new PokemonFileDAO("C:\\Users\\LENOVO\\Downloads\\pokemon.csv");
        System.out.println(pokemonFileDAO.getAll());
    }
}
