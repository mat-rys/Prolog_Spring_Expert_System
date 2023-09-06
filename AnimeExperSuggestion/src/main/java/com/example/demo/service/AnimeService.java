package com.example.demo.service;

import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnimeService {

    public static void getConnectionWithPrologFile(){
        String[] initArgs = {"-q"};
        JPL.init(initArgs);

        Query query = new Query("consult('baza.pl')");
        if (query.hasSolution()) {
            System.out.println("Plik Prologu załadowany pomyślnie.");
        } else {
            System.out.println("Błąd podczas ładowania pliku Prologu.");

        }

    }

    public static List<Map<String, Term>> getSolutionsFromQueryWithAnime(String zapytanie , String epizody, String oceny, String format){
        Query goQuery = new Query(zapytanie + epizody+ oceny + format+ ".");
        List<Map<String, Term>> solutions = new ArrayList<>();

        if (goQuery.hasSolution()) {
            while (goQuery.hasNext()) {
                Map<String, Term> solution = goQuery.nextSolution();
                solutions.add(solution);
            }
        }
        return solutions;
    }

}
