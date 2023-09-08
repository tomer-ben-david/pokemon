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
                String[] line = sc.nextLine().split(",");
                pokemons.add(new Pokemon(Integer.parseInt(line[0]),
                        line[1], line[2], line[3],
                        Integer.parseInt(line[4]), Integer.parseInt(line[5]),
                        Integer.parseInt(line[6]), Integer.parseInt(line[7])));
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
