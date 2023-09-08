package org.pokemon.dal;

import org.pokemon.model.Pokemon;

public interface PokemonHandler<T> {
    void accept(Pokemon pokemon);
    T getResult();
}
