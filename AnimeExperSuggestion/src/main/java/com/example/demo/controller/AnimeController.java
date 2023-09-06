package com.example.demo.controller;

import com.example.demo.entity.Anime;
import com.example.demo.service.AnimeService;
import lombok.AllArgsConstructor;
import org.jpl7.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api")
public class AnimeController {

    Anime animeQueryKategorie ;
    String zapytanie;


    //STRONA STARTOWA
    @GetMapping("/start-page")
    public String startPage() {
        zapytanie ="znajdzAnime(Tytuł, Gatunki, Odcinki, Ocena, ID, Format, Obejrzenia)";
        animeQueryKategorie  = new Anime("Gatunki"," "," "," ");
        return "startpage";
    }


    //GATUNKI
    @GetMapping("/gatunki")
    public String expertPageGatunki(Model model) {
        return "expertpagegatunki";
    }
    @PostMapping("/gatunki")
    public String processAnswerGatunki(@RequestParam("answer") String answer) {
        if (!answer.isEmpty()) {zapytanie = "znajdzDopasowaneAnime(Tytuł,"+answer+" , Odcinki, Ocena, ID, Format, Obejrzenia)";}
        return "redirect:/api/format";
    }


    //FORMAT
    @GetMapping("/format")
    public String expertPageFormat(Model model) {
        return "expertpageformat";
    }
    @PostMapping("/format")
    public String processAnswerFormat(@RequestParam("answer") String answer) {
        if (!answer.isEmpty()) {animeQueryKategorie.setFormat(",Format ="+answer);}
        return "redirect:/api/oceny";
    }


    //OCENA
    @GetMapping("/oceny")
    public String expertPageOceny(Model model) {
        return "expertpageoceny";
    }
    @PostMapping("/oceny")
    public String processAnswerOceny(@RequestParam("answer") String answer) {
        if (!answer.isEmpty()) {animeQueryKategorie.setRating(",Ocena"+answer);}
        return "redirect:/api/odcinki";
    }


    //ODCINKI
    @GetMapping("/odcinki")
    public String expertPageOdcinki(Model model) {
        return "expertpageodcinki";
    }
    @PostMapping("/odcinki")
    public String processAnswerOdcinki(@RequestParam("answer") String answer) {
        if (!answer.isEmpty()) {animeQueryKategorie.setEpisodes(", Odcinki" + answer);}
        return "redirect:/api/results";
    }


    //WYNIKI
    @GetMapping("/results")
    public String resultsPage(Model model) {

       AnimeService.getConnectionWithPrologFile();

        List<Map<String, Term>> solutions =  AnimeService.getSolutionsFromQueryWithAnime(
                zapytanie ,
                animeQueryKategorie.getEpisodes(),
                animeQueryKategorie.getRating(),
                animeQueryKategorie.getFormat());

        model.addAttribute("results", solutions);
        return "resultspage";
    }
}

