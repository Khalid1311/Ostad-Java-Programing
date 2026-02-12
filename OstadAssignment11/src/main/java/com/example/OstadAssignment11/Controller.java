package com.example.OstadAssignment11;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class Controller {

    List<Transaction> transactions = new ArrayList<>();

    @GetMapping
    public List<Transaction> GetAllList(){
        return transactions;
    }

    @GetMapping("/{id}")
    public List<Transaction> GetIdList(@PathVariable int id){
        return transactions.stream().filter(t-> t.getId() == id).toList();
    }

    @PostMapping
    public String add(@RequestBody Transaction t) {
        transactions.add(t);
        return "Added";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        transactions.removeIf(t -> t.getId() == id);
        return "Deleted";
    }
}
