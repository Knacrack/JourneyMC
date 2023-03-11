package de.knacrack.journeymc.item.recipe;

import de.knacrack.journeymc.listener.Listener;
import de.knacrack.journeymc.utils.logging.Logger;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeSingleton {

    private static final RecipeSingleton instance = new RecipeSingleton();

    private final Set<Recipe> registeredRecipes = new HashSet<>();

    private final Set<Recipe> unregisteredRecipes = new HashSet<>();

    RecipeSingleton() {
        Logger.info("Initiate Recipes.");
    }

    public void registerRecipe(Recipe recipe) {
        if(recipe.register()) {
            if(!registeredRecipes.contains(recipe)) {
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

    public void registerListeners(Recipe...recipes) {
        Arrays.stream(recipes).forEach(this::registerRecipe);
    }

    public void registerListeners(Set<Recipe> recipes) {
        recipes.stream().forEach(this::registerRecipe);
    }

    public void registerListeners(List<Recipe> recipes) {
        recipes.stream().forEach(this::registerRecipe);
    }

    public Set<Recipe> getRegisteredRecipes() {
        return registeredRecipes;
    }

    public Set<Recipe> getUnregisteredRecipes() {
        return unregisteredRecipes;
    }

    public static RecipeSingleton getInstance() {
        return instance;
    }


}
