package org.pokemon.dal;

import org.pokemon.model.Pokemon;

import java.util.List;

public interface PokemonDAO {
    List<Pokemon> getAll();

}
