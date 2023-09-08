package org.pokemon.dal;

import org.pokemon.dto.Pokemon;

import java.util.List;

public interface PokemonDAO {
    List<Pokemon> getAll();

}
