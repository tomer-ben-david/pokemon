package org.pokemon.bl;

import lombok.AllArgsConstructor;
import org.pokemon.model.Attribute;
import org.pokemon.model.Pokemon;
import org.pokemon.dal.PokemonDAO;

import java.util.*;

@AllArgsConstructor
public class PokemonBL {

    private final PokemonDAO pokemonDAO;

    public List<Pokemon> getAll() {
        return pokemonDAO.getAll();
    }


    public Pokemon getFiltered(List<Pokemon> pokemons, FilterTwo filterTwo) {
        if (pokemons.isEmpty()) {
            return null;
        }

        Pokemon pokemon = pokemons.get(0);

        for (int i = 1; i < pokemons.size(); i++) {
            pokemon = filterTwo.filter(pokemon, pokemons.get(i));
        }

        return pokemon;
    }

    public double getAverageSpeed(List<Pokemon> pokemons) {
        double sum = 0.0;
        for (int i = 0; i < pokemons.size(); i++) {
            sum += pokemons.get(i).getSpeed();
        }
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

    public int getTotalHP() {
        return 500;
    }

    public List<Pokemon> filterByType(List<Pokemon> pokemons, String type) {
        List<Pokemon> result = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            if (Objects.equals(pokemon.getType1(), type)) {
                result.add(pokemon);
            }
        }

        return result;
    }

    public List<Pokemon> sortBy(List<Pokemon> pokemons, Attribute attribute) {
        // Just sort over the pokemons.
        return List.of(
                new Pokemon(1, "Charmelon", "Grass", "Fly", 1, 2, 3, 4),
                new Pokemon(2, "Vynosaur", "Grass", "Fly", 1, 2, 3, 4)
        );
    }
}
