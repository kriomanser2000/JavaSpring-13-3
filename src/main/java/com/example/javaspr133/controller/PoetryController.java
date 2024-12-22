package com.example.javaspr133.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/poems")
public class PoetryController
{
    private final PoetryService poetryService;
    public PoetryController(PoetryService poetryService)
    {
        this.poetryService = poetryService;
    }
    @GetMapping("/biography/{poet}")
    public String getBiography(@PathVariable String poet)
    {
        return poetryService.getBiography(poet);
    }
    @GetMapping("/poem/{poet}/{title}")
    public String getPoem(@PathVariable String poet, @PathVariable String title)
    {
        return poetryService.getPoem(poet, title);
    }
    @GetMapping("/search")
    public List<String> searchPoems(@RequestParam String poet, @RequestParam String keyword)
    {
        return poetryService.searchPoems(poet, keyword);
    }
    @GetMapping("/famous/{poet}")
    public List<String> getFamousPoems(@PathVariable String poet)
    {
        return poetryService.getFamousPoems(poet);
    }
    @GetMapping("/random/{poet}")
    public String getRandomPoem(@PathVariable String poet)
    {
        return poetryService.getRandomPoem(poet);
    }
    @GetMapping("/categories")
    public List<String> getAllCategories()
    {
        return poetryService.getAllCategories();
    }
    @GetMapping("/categories/search")
    public List<String> searchCategories(@RequestParam String keyword)
    {
        return poetryService.searchCategories(keyword);
    }
    @GetMapping("/categories/top3/{category}")
    public List<String> getTop3PoemsInCategory(@PathVariable String category)
    {
        return poetryService.getTop3PoemsInCategory(category);
    }
    @GetMapping("/categories/popular")
    public List<String> getTop3PopularCategories()
    {
        return poetryService.getTop3PopularCategories();
    }
    @GetMapping("/categories/top3")
    public List<String> getTop3PoemsAcrossCategories()
    {
        return poetryService.getTop3PoemsAcrossCategories();
    }
    @GetMapping("/poets/popular")
    public List<String> getTop3PopularPoets()
    {
        return poetryService.getTop3PopularPoets();
    }
    @GetMapping("/categories/largest")
    public List<String> getTop3LargestCategories()
    {
        return poetryService.getTop3LargestCategories();
    }
    @GetMapping("/poets/by-country")
    public List<String> getTop3PoetsByCountry(@RequestParam String country)
    {
        return poetryService.getTop3PoetsByCountry(country);
    }
    @PostMapping("/categories/add")
    public String addCategory(@RequestParam String category, @RequestParam String username, @RequestParam String password)
    {
        return poetryService.addCategory(category, username, password);
    }
    @DeleteMapping("/categories/delete")
    public String deleteCategory(@RequestParam String category, @RequestParam String username, @RequestParam String password)
    {
        return poetryService.deleteCategory(category, username, password);
    }
    @PutMapping("/categories/update")
    public String updateCategory(@RequestParam String oldCategory, @RequestParam String newCategory, @RequestParam String username, @RequestParam String password)
    {
        return poetryService.updateCategory(oldCategory, newCategory, username, password);
    }
}
