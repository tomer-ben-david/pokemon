package org.pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.pokemon.dal.PokemonDAO;
import org.pokemon.dto.Pokemon;

import java.util.List;

@AllArgsConstructor
public class FakePokemonDAO implements PokemonDAO {

    @Getter
    private List<Pokemon> pokemons;

    @Override
    public List<Pokemon> getAll() {
        return pokemons;
    }
}
