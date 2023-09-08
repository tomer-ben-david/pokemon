package org.pokemon;

import org.pokemon.bl.MaxFieldFilter;
import org.pokemon.bl.PokemonBL;
import org.pokemon.dal.PokemonFileDAO;
import org.pokemon.dto.Attribute;
import org.pokemon.dto.Pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PokemonApp {
    public static void main(String[] args) {

        // Question 1 answer - Data Parsing + Pokemon object result.
        PokemonFileDAO pokemonFileDAO = new PokemonFileDAO("C:\\Users\\LENOVO\\Downloads\\pokemon.csv");
        PokemonBL pokemonBL = new PokemonBL(pokemonFileDAO);

        List<Pokemon> pokemons = pokemonBL.getAll();


        // Question 2 answer - Analyze Data.
        Pokemon pokemonHighestHp = pokemonBL.getFiltered(pokemons, new MaxFieldFilter(Comparator.comparingInt(Pokemon::getHp)));
        System.out.printf("Pokemon [%s] has highest HP [%d]\n",
                pokemonHighestHp.getName(), pokemonHighestHp.getHp());

        Pokemon pokemonHighestAttack = pokemonBL.getFiltered(pokemons, new MaxFieldFilter(Comparator.comparingInt(Pokemon::getAttack)));
        System.out.printf("Pokemon [%s] has highest Attack [%d]\n",
                pokemonHighestAttack.getName(), pokemonHighestAttack.getHp());

        Pokemon pokemonHighestDefense = pokemonBL.getFiltered(pokemons, new MaxFieldFilter(Comparator.comparingInt(Pokemon::getDefense)));
        System.out.printf("Pokemon [%s] has highest Defense [%d]\n",
                pokemonHighestDefense.getName(), pokemonHighestDefense.getDefense());

        double avgSpeed = pokemonBL.getAverageSpeed(pokemons);
        System.out.printf("Pokemons average speed [%.2f]%n\n", avgSpeed);

        Map<String, Integer> pokemonCountPerType = pokemonBL.countByType();
        System.out.printf("Pokemons count by type [%s]", pokemonCountPerType);

        System.out.printf("Pokemons total HP [%d]", pokemonBL.getTotalHP());

        // Question 3 answer - unit tests
        // see -- PokemonAppTest.java --

        // Question 4 answer - Additional features.

        // Filter pokemon by type and display results.
        List<Pokemon> grassTypePokemon = pokemonBL.filterByType("Grass");
        System.out.printf("Pokemons filter by type [%s]", grassTypePokemon);

        // Question 4 answer - sort based on attribute.
        List<Pokemon> sortByHp = pokemonBL.sortBy(pokemons, Attribute.HP);

        // Plan for big data.
        //
        // Ready made tools - Batch Map Reduce or Streaming.
        // Spark Batch Solution:
        //   spark = new SparkSession().
        //   DataFrame df = spark.sql("SELECT max('hp') FROM pokemons")
        //   df.get(0).


        // Custom programming solution:
        //  1. Do not store pokemons all in memory.
        //  2. While iterating file for each line:
        //  3.   result = max(cur, result)
        //  4. return result.

        // Spark streaming solution.

    }
}
