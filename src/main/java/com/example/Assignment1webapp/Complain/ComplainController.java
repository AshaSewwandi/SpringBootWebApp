package com.example.Assignment1webapp.Complain;

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
public class ComplainController {
    @Autowired
    private ComplainService service;

    @GetMapping("/complains")
    public String showComplainList(Model model){
        List<Complain> listComplains = service.listAll();
        model.addAttribute("listComplains", listComplains);
        return "complains";
    }

    @GetMapping("/complains/new")
    public String showNewComplainForm(Model model){
        model.addAttribute("complain", new Complain());
        model.addAttribute("pageTitle", "Add New Complain");
        return "complain-form";
    }

    @PostMapping("/complains/save")
    public String saveComplain(Complain complain, RedirectAttributes ra){
        service.save(complain);
        ra.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/complains";
    }

    @GetMapping("/complains/edit/{id}")
    public String showEditComplainForm(@PathVariable("id") Integer id, Model model , RedirectAttributes ra){
        try {
            Complain complain = service.get(id);
            model.addAttribute("complain", complain);
            model.addAttribute("pageTitle", "Edit Complain(ID: " + id +")");
            ra.addFlashAttribute("message", "The Complain ID " + id + " has been updated.");
            return "complain-form";
        } catch (ComplainNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/complains";
        }
    }

    @GetMapping("/complains/delete/{id}")
    public String deleteComplain(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The complain ID " + id + " has been deleted.");
        } catch (ComplainNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/complains";
    }
}
