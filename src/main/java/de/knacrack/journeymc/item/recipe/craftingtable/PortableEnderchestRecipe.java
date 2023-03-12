package de.knacrack.journeymc.item.recipe.craftingtable;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.ItemSingleton;
import de.knacrack.journeymc.item.list.PortableEnderchest;
import de.knacrack.journeymc.item.recipe.Recipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;

public class PortableEnderchestRecipe implements Recipe {
    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(getKey(), ItemSingleton.getInstance().getRegisteredItemsByClassName().get(PortableEnderchest.class.getSimpleName()).getItem());
        recipe.addIngredient(Material.ENDER_CHEST);
        recipe.addIngredient(Material.PAPER);
        recipe.addIngredient(Material.NETHER_STAR);
        return recipe;
    }

    @Override
    public String getLabel() {
        return "PortableEnderchestRecipe";
    }

    @Override
    public boolean register() {
        return true;
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(Main.getInstance(), getClass().getSimpleName());
    }
}
