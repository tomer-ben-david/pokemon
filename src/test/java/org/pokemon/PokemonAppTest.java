package org.pokemon;


import org.junit.Assert;
import org.junit.Test;
import org.pokemon.bl.MaxFieldFilter;
import org.pokemon.bl.MinFieldFilter;
import org.pokemon.bl.PokemonBL;
import org.pokemon.dal.PokemonDAO;
import org.pokemon.dto.Pokemon;

import java.util.Comparator;
import java.util.List;

public class PokemonAppTest {
    List<Pokemon> testPokemons = List.of(
            new Pokemon(1, "mypok1", "type1-1", "type2-2", 1, 2)
            , new Pokemon(2, "mypok2", "type1-2", "type2-2", 2, 2)
            , new Pokemon(0, "mypok0", "type1-0", "type2-0", 0, 0)
    );

    PokemonDAO pokemonDAO = new PokemonInMemDAO(testPokemons);

    @Test
    public void testMaxHP() {
        PokemonBL pokemonBL = new PokemonBL(pokemonDAO);
        MaxFieldFilter maxHpFilter = new MaxFieldFilter(Comparator.comparingInt(Pokemon::getHp));
        Pokemon pokemon = pokemonBL.getFiltered(testPokemons, maxHpFilter);
        Assert.assertEquals(2, pokemon.getHp());
    }

    @Test
    public void testMinHP() {
        PokemonBL pokemonBL = new PokemonBL(pokemonDAO);
        MinFieldFilter minHpFilter = new MinFieldFilter(Comparator.comparingInt(Pokemon::getHp));
        Pokemon pokemon = pokemonBL.getFiltered(testPokemons, minHpFilter);
        Assert.assertEquals(0, pokemon.getHp());
    }
}
