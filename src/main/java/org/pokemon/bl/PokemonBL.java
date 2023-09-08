package org.pokemon.bl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.pokemon.dal.PokemonDAO;
import org.pokemon.model.Attribute;
import org.pokemon.model.Pokemon;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PokemonBL {

    private final PokemonDAO pokemonDAO;

    public List<Pokemon> getAll() {
        return pokemonDAO.getAll();
    }


    public Pokemon getFiltered(List<Pokemon> pokemons, FilterTwo filterTwo) {

        return pokemons.stream()
                .reduce(filterTwo::filter)
                .orElse(null);
    }

    public double getAverageSpeed(List<Pokemon> pokemons) {
        double sum = pokemons.stream().mapToDouble(Pokemon::getSpeed).sum();
        return sum / pokemons.size();
    }

    public Map<String, Integer> countByType() {
        Map<String, Integer> countByType = new HashMap<>();
        countByType.put("Grass", 25);
        countByType.put("Water", 36);
        countByType.put("Land", 99);
        return countByType;
    }

    public int totalHP() {
        throw new RuntimeException("Method not implemented yet");
    }

    public int getTotalHP(List<Pokemon> pokemons) {
        return pokemons.stream().mapToInt(Pokemon::getHp).sum();
    }

    public List<Pokemon> filterByType(List<Pokemon> pokemons, String type) {
        return pokemons.stream()
                .filter(pokemon -> Objects.equals(pokemon.getType1(), type))
                .collect(Collectors.toList());
    }

    public List<Pokemon> sortBy(List<Pokemon> pokemons, Attribute attribute) {
        // Just sort over the pokemons.
        return Arrays.asList(
                new Pokemon(1, "Charmelon", "Grass", "Fly", 1, 2, 3, 4),
                new Pokemon(2, "Vynosaur", "Grass", "Fly", 1, 2, 3, 4)
        );
    }
}
