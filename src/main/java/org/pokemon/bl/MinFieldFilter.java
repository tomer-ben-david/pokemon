package org.pokemon.bl;

import lombok.AllArgsConstructor;
import org.pokemon.model.Pokemon;

import java.util.Comparator;

@AllArgsConstructor
public class MinFieldFilter implements FilterTwo {
    private final Comparator<Pokemon> pokemonComparator;
    public Pokemon filter(Pokemon pokemon1, Pokemon pokemon2) {
        return pokemonComparator.compare(pokemon1, pokemon2) < 0 ? pokemon1 : pokemon2;
    }
}
