package org.pokemon.bl;

import lombok.AllArgsConstructor;
import org.pokemon.dto.Pokemon;

import java.util.Comparator;

@AllArgsConstructor
public class MinFieldFilter implements FilterTwo {
    private final Comparator<Pokemon> maxComparator;
    public Pokemon filter(Pokemon pokemon1, Pokemon pokemon2) {
        return maxComparator.compare(pokemon1, pokemon2) < 0 ? pokemon1 : pokemon2;
    }
}
