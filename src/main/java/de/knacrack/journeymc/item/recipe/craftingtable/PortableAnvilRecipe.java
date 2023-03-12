package de.knacrack.journeymc.item.recipe.craftingtable;

import de.knacrack.journeymc.item.ItemSingleton;
import de.knacrack.journeymc.item.list.PortableAnvil;
import de.knacrack.journeymc.item.recipe.Recipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;

public class PortableAnvilRecipe implements Recipe {
    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(getKey(), ItemSingleton.getInstance().getRegisteredItemsByClassName().get(PortableAnvil.class.getSimpleName()).getItem());
        recipe.addIngredient(Material.ANVIL);
        recipe.addIngredient(Material.PAPER);
        recipe.addIngredient(Material.NETHER_STAR);
        return recipe;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public NamespacedKey getKey() {
        return null;
    }
}
