package de.knacrack.journeymc.item.recipe;

import de.knacrack.journeymc.utils.logging.Logger;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RecipeSingleton {

    private static final RecipeSingleton instance = new RecipeSingleton();

    private final List<Recipe> registeredRecipes = new ArrayList<>();

    private final List<Recipe> unregisteredRecipes = new ArrayList<>();

    RecipeSingleton() {
        Logger.info("Initiate Recipes.");
    }

    public void registerRecipe(Recipe recipe) {
        if (recipe.register()) {
            if (!registeredRecipes.contains(recipe)) {
                Logger.info("Register Recipe: " + recipe.getClass().getSimpleName());
                Bukkit.addRecipe(recipe.getRecipe());
                registeredRecipes.add(recipe);
            } else {
                Logger.error(recipe.getLabel() + " is already registered!");
            }
        } else {
            unregisteredRecipes.add(recipe);
        }
    }

    public void registerRecipes(Recipe... recipes) {
        Arrays.stream(recipes).forEach(this::registerRecipe);
    }

    public void registerRecipes(Set<Recipe> recipes) {
        recipes.stream().forEach(this::registerRecipe);
    }

    public void registerRecipes(List<Recipe> recipes) {
        recipes.stream().forEach(this::registerRecipe);
    }

    public List<Recipe> getRegisteredRecipes() {
        return registeredRecipes;
    }

    public List<Recipe> getUnregisteredRecipes() {
        return unregisteredRecipes;
    }

    public static RecipeSingleton getInstance() {
        return instance;
    }


}
