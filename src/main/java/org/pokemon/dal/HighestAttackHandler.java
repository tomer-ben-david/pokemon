package org.pokemon.dal;

import org.pokemon.model.Pokemon;

public class HighestAttackHandler implements PokemonHandler<Integer> {

    private int highestAttack = 0;

    @Override
    public void accept(Pokemon pokemon) {
        if (this.highestAttack < pokemon.getAttack()) {
            this.highestAttack = pokemon.getAttack();
        }
    }

    @Override
    public Integer getResult() {
        return highestAttack;
    }
}
