package com.example.Assignment1webapp.Fertilizer;

import com.example.Assignment1webapp.User.User;
import com.example.Assignment1webapp.User.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FertilizerController {
    @Autowired
    private FertilizerService service;

    @GetMapping("/fertilizers")
    public String showFertilizerList(Model model){
        List<Fertilizer> listFertilizers = service.listAll();
        model.addAttribute("listFertilizers", listFertilizers);
        return "fertilizers";
    }

    @GetMapping("/fertilizers/new")
    public String showNewFertilizerForm(Model model){
        model.addAttribute("fertilizer", new Fertilizer());
        model.addAttribute("pageTitle", "Add New Fertilizer");
        return "fertilizer-form";
    }

    @PostMapping("/fertilizers/save")
    public String saveFertilizer(Fertilizer fertilizer, RedirectAttributes ra){
        service.save(fertilizer);
        ra.addFlashAttribute("message", "The fertilizer has been saved successfully");
        return "redirect:/fertilizers";
    }

    @GetMapping("/fertilizers/edit/{id}")
    public String showEditFertilizerForm(@PathVariable("id") Integer id, Model model ,RedirectAttributes ra){
        try {
            Fertilizer fertilizer = service.get(id);
            model.addAttribute("fertilizer", fertilizer);
            model.addAttribute("pageTitle", "Edit Fertilizer(ID: " + id +")");
            ra.addFlashAttribute("message", "The fertilizer ID " + id + " has been updated.");
            return "fertilizer-form";
        } catch (FertilizerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/fertilizers";
        }
    }

    @GetMapping("/fertilizers/delete/{id}")
    public String deleteFertilizer(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (FertilizerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/fertilizers";
    }
}
