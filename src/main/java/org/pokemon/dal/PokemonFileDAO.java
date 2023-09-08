package org.pokemon.dal;

import lombok.AllArgsConstructor;
import org.pokemon.dto.Pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class PokemonFileDAO implements PokemonDAO {
    final String filePath;
    @Override
    public List<Pokemon> getAll() {
        Scanner sc = null;
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try {
            sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            sc.nextLine();
            while (sc.hasNextLine()) {
                sc.nextLine();
                if (sc.hasNext()) {
                    String token = sc.next();
                    int id = Integer.parseInt(token);
                    pokemons.add(new Pokemon(id, "", "", "", 1, 1));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return pokemons;
    }
}
