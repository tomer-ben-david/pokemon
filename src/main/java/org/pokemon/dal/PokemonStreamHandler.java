package org.pokemon.dal;

import lombok.AllArgsConstructor;
import org.pokemon.model.Pokemon;

import java.util.List;

@AllArgsConstructor
public class PokemonStreamHandler {

    private final List<PokemonHandler> handlers;

    public void acceptPokemon(Pokemon pokemon) {
        handlers.forEach(handler -> handler.accept(pokemon));
    }

}
