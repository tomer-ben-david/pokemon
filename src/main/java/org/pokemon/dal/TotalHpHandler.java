package org.pokemon.dal;

import org.pokemon.model.Pokemon;

public class TotalHpHandler implements PokemonHandler<Integer> {
    private int totalHp = 0;

    @Override
    public void accept(Pokemon pokemon) {
        this.totalHp += pokemon.getHp();
    }

    @Override
    public Integer getResult() {
        return this.totalHp;
    }
}
