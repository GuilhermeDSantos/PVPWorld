package dev.guilhermeds.builder;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder extends ItemStack {

    private ItemMeta itemMeta;

    public ItemBuilder(ItemStack stack) throws IllegalArgumentException {
        super(stack);
        this.itemMeta = this.getItemMeta();
    }

    public ItemBuilder(Material type, int amount) {
        super(type, amount);
        this.itemMeta = this.getItemMeta();
    }

    public ItemBuilder(Material type, short meta) {
        super(type, 1, meta);
        this.itemMeta = this.getItemMeta();
    }

    public ItemBuilder(Material type) {
        this(type, 1);
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.itemMeta.setDisplayName(displayName);
        this.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        this.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setGlowing() {
        return this.addTag("ench", new NBTTagList());
    }

    public ItemBuilder addTag(String key, String value) {
        return this.addTag(key, new NBTTagString(value));
    }

    private ItemBuilder addTag(String key, NBTBase tag) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);

        NBTTagCompound itemTag = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
        itemTag.set(key, tag);
        nmsItem.setTag(itemTag);

        return new ItemBuilder(CraftItemStack.asBukkitCopy(nmsItem));
    }

    public ItemBuilder setUnbreakable() {
        this.itemMeta.spigot().setUnbreakable(true);
        this.setItemMeta(this.itemMeta);
        return this;
    }
}
