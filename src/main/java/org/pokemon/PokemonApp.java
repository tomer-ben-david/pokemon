package org.pokemon;

import org.pokemon.bl.MaxFieldFilter;
import org.pokemon.bl.PokemonBL;
import org.pokemon.dal.*;
import org.pokemon.model.Attribute;
import org.pokemon.model.Pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PokemonApp {
    public static void main(String[] args) {
        // Question 1 - Data Parsing + Pokemon object result.
        MaxHpHandler maxHpHandler = new MaxHpHandler();
        TotalHpHandler totalHpHandler = new TotalHpHandler();
        HighestAttackHandler highestAttackHandler = new HighestAttackHandler();
        PokemonStreamAcceptor pokemonStreamAcceptor = new PokemonStreamAcceptor();
        PokemonStreamHandler pokemonStreamHandler = new PokemonStreamHandler(
                List.of(maxHpHandler,
                        totalHpHandler,
                        highestAttackHandler)
        );
        PokemonFileDAO pokemonFileDAO = new PokemonFileDAO(
                pokemonStreamAcceptor,
                pokemonStreamHandler,
                "C:\\Users\\LENOVO\\Downloads\\pokemon.csv");
        PokemonBL pokemonBL = new PokemonBL(pokemonFileDAO);

        List<Pokemon> pokemons = pokemonBL.getAll();

        // Question 2 - Analyze Data.
        Pokemon pokemonHighestHp = pokemonBL.getFiltered(pokemons, new MaxFieldFilter(Comparator.comparingInt(Pokemon::getHp)));
        System.out.printf("Pokemon [%s] has highest HP [%d]\n",
                pokemonHighestHp.getName(), pokemonHighestHp.getHp());
        System.out.printf("Stream ver1: Pokemon [%s] has highest HP [%d]\n",
                pokemonHighestHp.getName(), pokemonStreamAcceptor.getHighestHp());
        System.out.printf("Stream ver2: Pokemon [%s] has highest HP [%d]\n",
                pokemonHighestHp.getName(), maxHpHandler.getResult());

        Pokemon pokemonHighestAttack = pokemonBL.getFiltered(pokemons, new MaxFieldFilter(Comparator.comparingInt(Pokemon::getAttack)));
        System.out.printf("Pokemon [%s] has highest Attack [%d]\n",
                pokemonHighestAttack.getName(), pokemonHighestAttack.getAttack());
        System.out.printf("Stream ver1: Pokemon [%s] has highest Attack [%d]\n",
                pokemonHighestHp.getName(), pokemonStreamAcceptor.getHighestAttack());
        System.out.printf("Stream ver2: Pokemon [%s] has highest Attack [%d]\n",
                pokemonHighestHp.getName(), highestAttackHandler.getResult());

        Pokemon pokemonHighestDefense = pokemonBL.getFiltered(pokemons, new MaxFieldFilter(Comparator.comparingInt(Pokemon::getDefense)));
        System.out.printf("Pokemon [%s] has highest Defense [%d]\n",
                pokemonHighestDefense.getName(), pokemonHighestDefense.getDefense());
        System.out.printf("Stream: Pokemon [%s] has highest Defense [%d]\n",
                pokemonHighestDefense.getName(), pokemonStreamAcceptor.getHighestDefense());

        double avgSpeed = pokemonBL.getAverageSpeed(pokemons);
        System.out.printf("Pokemons average speed [%.2f]%n", avgSpeed);
        System.out.printf("Stream: Pokemons average speed [%.2f]%n", pokemonStreamAcceptor.getAvgSpeed());

        Map<String, Integer> pokemonCountPerType = pokemonBL.countByType();
        System.out.printf("Pokemons count by type [%s]\n", pokemonCountPerType);
        System.out.printf("Stream: Pokemons count by type [%s]\n", pokemonStreamAcceptor.getCountByType());

        System.out.printf("Pokemons total HP [%d]\n", pokemonBL.getTotalHP(pokemons));
        System.out.printf("Stream ver1: Pokemons total HP [%d]\n", pokemonStreamAcceptor.getTotalHp());
        System.out.printf("Stream ver2: Pokemons total HP [%d]\n", totalHpHandler.getResult());

        // Question 3 - Unit tests
        // see -- PokemonAppTest.java -- Added few unit tests - added empty ones signature only when having time.

        // Question 4 - Additional features.

        // Filter pokemon by type and display results.
        List<Pokemon> darkTypePokemons = pokemonBL.filterByType(pokemons, "Dark");
        System.out.printf("Pokemons filter by type Dark [%s]\n", darkTypePokemons);

        // Question 4 - sort based on attribute.
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
