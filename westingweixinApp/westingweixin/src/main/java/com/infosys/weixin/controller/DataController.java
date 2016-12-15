package com.infosys.weixin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.basic.entity.Words;
import com.infosys.basic.service.IWordsService;

@RequestMapping("/data")
@Controller
public class DataController {
    @Inject
    private IWordsService wordsService;

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable int id, Words words, Model model) {
        Words tu = wordsService.load(id);
        model.addAttribute("words", tu);
        return "data/detail";
    }

    @RequestMapping(value = "/konw", method = RequestMethod.GET)
    public String konw(Model model) {
        Words tu = wordsService.konw();
        model.addAttribute("words", tu);
        return "data/detail";
    }
}
