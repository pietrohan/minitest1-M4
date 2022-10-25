package com.codegym.cms.controller;


import com.codegym.cms.model.Expense;
import com.codegym.cms.model.ExpenseForm;
import com.codegym.cms.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private IExpenseService expenseService;
    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("/create-expense")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/expense/create");
        modelAndView.addObject("expenseForm", new ExpenseForm());
        return modelAndView;
    }

    @PostMapping("/create-expense")
    public ModelAndView saveSpending(@ModelAttribute("expenseForm") ExpenseForm expenseForm) {
        MultipartFile multipartFile = expenseForm.getPicture();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(expenseForm.getPicture().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Expense expense = new Expense(expenseForm.getId(), expenseForm.getName(),expenseForm.getPrice(),expenseForm.getDescription(),expenseForm.getListSpending(), fileName);


        expenseService.save(expense);
        ModelAndView modelAndView = new ModelAndView("/expense/create");
        modelAndView.addObject("expenseForm", expenseForm);
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
    public ModelAndView updateExpense(@ModelAttribute("expenseForm") ExpenseForm expenseForm) {
        MultipartFile multipartFile = expenseForm.getPicture();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(expenseForm.getPicture().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Expense expense = new Expense(expenseForm.getId(), expenseForm.getName(),expenseForm.getPrice(),expenseForm.getDescription(),expenseForm.getListSpending(), fileName);

        expenseService.save(expense);
        ModelAndView modelAndView = new ModelAndView("/expense/edit");
        modelAndView.addObject("expense", expense);
        modelAndView.addObject("message", "expense updated successfully");
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
//    @PostMapping("/expense/save")
//    public ModelAndView saveExpense(@ModelAttribute ExpenseForm expenseForm) {
//        MultipartFile multipartFile = expenseForm.getPicture();
//        String fileName = multipartFile.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(expenseForm.getPicture().getBytes(), new File(fileUpload + fileName));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        Expense expense = new Expense(expenseForm.getId(), expenseForm.getName(),
//                expenseForm.getPrice(),expenseForm.getDescription(),expenseForm.getListSpending(), fileName);
//        expenseService.save(expense);
//        ModelAndView modelAndView = new ModelAndView("/expense/create");
//        modelAndView.addObject("expenseForm", expenseForm);
//        modelAndView.addObject("message", "Created new expense successfully !");
//        return modelAndView;
//    }
}