package de.knacrack.journeymc.item.recipe.furnace;

import de.knacrack.journeymc.Main;
import de.knacrack.journeymc.item.recipe.Recipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class RawGoldBlockToGoldBlock implements Recipe {
    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        return new FurnaceRecipe(getKey(), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 9, 200);
    }

    @Override
    public String getLabel() {
        return "RawGoldBlockToGoldBlock";
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
