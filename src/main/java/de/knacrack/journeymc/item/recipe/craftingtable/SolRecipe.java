package de.knacrack.journeymc.item.recipe.craftingtable;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.ItemSingleton;
import de.knacrack.journeymc.item.list.Sol;
import de.knacrack.journeymc.item.recipe.Recipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;

public class SolRecipe implements Recipe {
    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(getKey(), ItemSingleton.getInstance().getRegisteredItemsByClassName().get(Sol.class.getSimpleName()).getItem());
        recipe.addIngredient(Material.NETHERITE_SWORD);
        recipe.addIngredient(Material.NETHER_STAR);
        recipe.addIngredient(Material.GHAST_TEAR);
        recipe.addIngredient(Material.BLAZE_ROD);
        recipe.addIngredient(Material.LAVA_BUCKET);
        recipe.addIngredient(Material.WITHER_SKELETON_SKULL);
        return recipe;
    }

    @Override
    public String getLabel() {
        return "SolRecipe";
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
