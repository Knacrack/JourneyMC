package de.knacrack.journeymc.item.recipe.stonecutter;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.recipe.Recipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;

public class DirtToGravel implements Recipe {
    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        return new StonecuttingRecipe(getKey(), new ItemStack(Material.DIRT), Material.GRAVEL);
    }

    @Override
    public String getLabel() {
        return "DirtToGravel";
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
