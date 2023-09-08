package org.pokemon.bl;

import org.pokemon.dto.Pokemon;

public interface Filter {
    boolean filter(Pokemon pokemon);
}
