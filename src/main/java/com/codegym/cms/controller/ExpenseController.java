package com.codegym.cms.controller;


import com.codegym.cms.model.Expense;
import com.codegym.cms.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private IExpenseService expenseService;

    @GetMapping("/create-expense")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/expense/create");
        modelAndView.addObject("expense", new Expense());
        return modelAndView;
    }

    @PostMapping("/create-expense")
    public ModelAndView saveExpense(@ModelAttribute("expense") Expense expense) {
        expenseService.save(expense);
        ModelAndView modelAndView = new ModelAndView("/expense/create");
        modelAndView.addObject("expense", new Expense());
        modelAndView.addObject("message", "New expense created successfully");
        return modelAndView;
    }
    @GetMapping("/expenses")
    public ModelAndView listCustomers() {
        List<Expense> expenses = expenseService.findAll();
        ModelAndView modelAndView = new ModelAndView("/expense/list");
        modelAndView.addObject("expenses", expenses);
        return modelAndView;
    }
    @GetMapping("/edit-expense/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Expense expense = expenseService.findById(id);
        if (expense != null) {
            ModelAndView modelAndView = new ModelAndView("/expense/edit");
            modelAndView.addObject("expense", expense);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-expense")
    public ModelAndView updateExpense(@ModelAttribute("expense") Expense expense) {
        expenseService.save(expense);
        ModelAndView modelAndView = new ModelAndView("/expense/edit");
        modelAndView.addObject("expense", expense);
        modelAndView.addObject("message", "Expense updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-expense/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Expense expense = expenseService.findById(id);
        if (expense != null) {
            ModelAndView modelAndView = new ModelAndView("/expense/delete");
            modelAndView.addObject("expense", expense);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-expense")
    public String deleteExpense(@ModelAttribute("expense") Expense expense) {
        expenseService.remove(expense.getId());
        return "redirect:expenses";
    }
}