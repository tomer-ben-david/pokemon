package org.pokemon.dal;

import org.pokemon.model.Pokemon;

public class MaxHpHandler implements PokemonHandler<Integer> {
    private int maxHp = 0;

    @Override
    public void accept(Pokemon pokemon) {
        if (maxHp < pokemon.getHp()) {
            this.maxHp = pokemon.getHp();
        }
    }

    @Override
    public Integer getResult() {
        return this.maxHp;
    }
}
