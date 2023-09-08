package org.pokemon.dal;

import lombok.Getter;
import org.pokemon.model.Pokemon;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PokemonStreamAcceptor {
    private int highestHp;
    private int totalHp;
    private int highestAttack;
    private int highestDefense;

    private int count;
    private int sumSpeed;
    private final Map<String, Integer> countByType = new HashMap<>();

    public void acceptPokemon(Pokemon pokemon) {
        if (highestHp < pokemon.getHp()) {
            highestHp = pokemon.getHp();
        }

        if (highestAttack < pokemon.getAttack()) {
            highestAttack = pokemon.getAttack();
        }

        if (highestDefense < pokemon.getDefense()) {
            highestDefense = pokemon.getDefense();
        }

        sumSpeed += pokemon.getSpeed();
        count += 1;

        totalHp += pokemon.getHp();

        int currentTypeCount = countByType.getOrDefault(pokemon.getType1(), 0);
        countByType.put(pokemon.getType1(), currentTypeCount + 1);
    }

    public double getAvgSpeed() {
        return sumSpeed / (count * 1.0);
    }


}
