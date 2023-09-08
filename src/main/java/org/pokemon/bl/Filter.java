package org.pokemon.bl;

import org.pokemon.model.Pokemon;

public interface Filter {
    boolean filter(Pokemon pokemon);
}
