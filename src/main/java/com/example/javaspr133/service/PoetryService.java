package com.example.javaspr133.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PoetryService
{
    private final Map<String, List<String>> poems = new HashMap<>();
    private final Map<String, Integer> categoryPopularity = new HashMap<>();
    private final Map<String, String> poetCountries = new HashMap<>();
    private final Set<String> administrators = Set.of("admin");
    private final Map<String, String> credentials = Map.of("admin", "password123");
    public PoetryService()
    {
        poems.put("Shevchenko", Arrays.asList(
                "Заповіт", "Сон", "Катерина"
        ));
        poems.put("Kostenko", Arrays.asList(
                "Дощ полив", "Кольорові миші", "Зелені очі орхідей"
        ));
        categoryPopularity.put("Classics", 50);
        categoryPopularity.put("Modern", 30);
        categoryPopularity.put("Romantic", 40);
        poetCountries.put("Shevchenko", "Ukraine");
        poetCountries.put("Kostenko", "Ukraine");
    }
    public String getBiography(String poet)
    {
        return poet.equalsIgnoreCase("Shevchenko") ? "Тарас Шевченко - український поет і художник." : "Біографія не знайдена";
    }
    public String getPoem(String poet, String title)
    {
        return poems.getOrDefault(poet, List.of()).contains(title) ? "Текст вірша: " + title : "Вірш не знайдено";
    }
    public List<String> searchPoems(String poet, String keyword)
    {
        return poems.getOrDefault(poet, List.of()).stream()
                .filter(poem -> poem.contains(keyword))
                .toList();
    }
    public List<String> getFamousPoems(String poet)
    {
        return poems.getOrDefault(poet, List.of());
    }
    public String getRandomPoem(String poet)
    {
        List<String> poetPoems = poems.getOrDefault(poet, List.of());
        return poetPoems.isEmpty() ? "Вірш не знайдено" : poetPoems.get(new Random().nextInt(poetPoems.size()));
    }
    public List<String> getAllCategories()
    {
        return new ArrayList<>(categoryPopularity.keySet());
    }
    public List<String> searchCategories(String keyword)
    {
        return categoryPopularity.keySet().stream()
                .filter(category -> category.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
    public List<String> getTop3PoemsInCategory(String category)
    {
        return poems.getOrDefault(category, List.of()).stream().limit(3).toList();
    }
    public List<String> getTop3PopularCategories()
    {
        return categoryPopularity.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
    public List<String> getTop3PoemsAcrossCategories()
    {
        return poems.values().stream()
                .flatMap(Collection::stream)
                .limit(3)
                .toList();
    }
    public List<String> getTop3PopularPoets()
    {
        return poems.entrySet().stream()
                .sorted((a, b) -> b.getValue().size() - a.getValue().size())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
    public List<String> getTop3LargestCategories()
    {
        return categoryPopularity.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
    public List<String> getTop3PoetsByCountry(String country)
    {
        return poetCountries.entrySet().stream()
                .filter(entry -> entry.getValue().equalsIgnoreCase(country))
                .map(Map.Entry::getKey)
                .limit(3)
                .toList();
    }
    public String addCategory(String category, String username, String password)
    {
        if (authenticateAdmin(username, password))
        {
            if (categoryPopularity.containsKey(category))
            {
                return "Категорія вже існує.";
            }
            categoryPopularity.put(category, 0);
            return "Категорія успішно додана.";
        }
        return "Недостатньо прав для виконання цієї операції.";
    }
    public String deleteCategory(String category, String username, String password)
    {
        if (authenticateAdmin(username, password))
        {
            if (categoryPopularity.containsKey(category))
            {
                categoryPopularity.remove(category);
                return "Категорія успішно видалена.";
            }
            return "Категорія не знайдена.";
        }
        return "Недостатньо прав для виконання цієї операції.";
    }
    public String updateCategory(String oldCategory, String newCategory, String username, String password)
    {
        if (authenticateAdmin(username, password))
        {
            if (categoryPopularity.containsKey(oldCategory))
            {
                categoryPopularity.remove(oldCategory);
                categoryPopularity.put(newCategory, 0);
                return "Категорія успішно оновлена.";
            }
            return "Стара категорія не знайдена.";
        }
        return "Недостатньо прав для виконання цієї операції.";
    }
    private boolean authenticateAdmin(String username, String password)
    {
        return administrators.contains(username) && credentials.getOrDefault(username, "").equals(password);
    }
}
