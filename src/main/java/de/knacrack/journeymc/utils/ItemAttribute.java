package de.knacrack.journeymc.utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class ItemAttribute {

    Attribute attribute;
    EquipmentSlot equipmentSlot;
    double amount;

    public ItemAttribute(Attribute attribute, EquipmentSlot equipmentSlot, double amount) {
        this.attribute = attribute;
        this.equipmentSlot = equipmentSlot;
        this.amount = amount;
    }

    public ItemAttribute(Attribute attribute, double amount) {
        this.attribute = attribute;
        this.equipmentSlot = null;
        this.amount = amount;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public EquipmentSlot getEquipmentSlot() {
        return this.equipmentSlot;
    }

    public double getAmount() {
        return this.amount;
    }

    public AttributeModifier get() {
        if (getEquipmentSlot() == null) {
            return new AttributeModifier(UUID.randomUUID(), attribute.name(), amount, AttributeModifier.Operation.ADD_NUMBER);
        } else {
            return new AttributeModifier(UUID.randomUUID(), attribute.name(), amount, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
        }
    }


}
