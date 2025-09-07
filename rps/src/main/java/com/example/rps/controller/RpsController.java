package com.example.rps.controller;

import com.example.rps.model.Result;
import com.example.rps.service.RpsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RpsController {

    private final RpsService rpsService;

    public RpsController(RpsService rpsService) {
        this.rpsService = rpsService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("score", rpsService.getOrCreateScore());
        return "index";
    }

    @GetMapping("/play")
    public String play(@RequestParam String move, Model model) {
        Result result = rpsService.play(move);
        model.addAttribute("result", result);
        model.addAttribute("score", rpsService.getOrCreateScore());
        return "index";
    }

    @GetMapping("/reset")
    public String reset(Model model) {
        rpsService.resetScoreboard();
        model.addAttribute("score", rpsService.getOrCreateScore());
        model.addAttribute("message", "Scoreboard has been reset!");
        return "index";
    }
}
