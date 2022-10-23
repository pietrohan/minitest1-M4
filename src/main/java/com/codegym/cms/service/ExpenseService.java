package com.codegym.cms.service;


import com.codegym.cms.model.Expense;
import com.codegym.cms.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpenseService implements IExpenseService {
    @Autowired
    private IExpenseRepository expenseRepository;

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void remove(Long id) {
        expenseRepository.remove(id);
    }
}
