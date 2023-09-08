package org.pokemon.bl;

import lombok.AllArgsConstructor;
import org.pokemon.dto.Pokemon;
import org.pokemon.dal.PokemonDAO;

import java.util.List;

@AllArgsConstructor
public class PokemonBL {

    private final PokemonDAO pokemonDAO;

    public List<Pokemon> getAll() {
        return pokemonDAO.getAll();
    }


    public Pokemon getFiltered(List<Pokemon> pokemons, FilterTwo filterTwo) {
        if (pokemons.isEmpty()) {
            return null; // Handle empty list case
        }

        Pokemon pokemon = pokemons.get(0); // Initialize with the first person

        for (int i = 1; i < pokemons.size(); i++) {
            pokemon = filterTwo.filter(pokemon, pokemons.get(i));
        }

        return pokemon;
    }
}
