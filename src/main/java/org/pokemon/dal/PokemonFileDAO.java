package org.pokemon.dal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.pokemon.model.Pokemon;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PokemonFileDAO implements PokemonDAO {
    final PokemonStreamAcceptor pokemonStreamAcceptor;
    final PokemonStreamHandler pokemonStreamHandler;
    final String filePath;
    @Override
    public List<Pokemon> getAll() {
        Scanner sc = null;
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            sc = new Scanner(inputStream);
            sc.useDelimiter(",");
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Pokemon pokemon = buildPokemon(line);
                if (pokemon != null) {
                    pokemons.add(pokemon);
                    pokemonStreamAcceptor.acceptPokemon(pokemon);
                    pokemonStreamHandler.acceptPokemon(pokemon);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return pokemons;
    }

    private Pokemon buildPokemon(String line) {
        try {
            String[] lineSplit = line.split(",");
            int index = 0;
            return new Pokemon(
                    Integer.parseInt(lineSplit[index++]),
                    lineSplit[index++],
                    lineSplit[index++],
                    lineSplit[index++],
                    Integer.parseInt(lineSplit[index++]),
                    Integer.parseInt(lineSplit[index++]),
                    Integer.parseInt(lineSplit[index++]),
                    Integer.parseInt(lineSplit[index++]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
