package itzshmulik.survivelist.survivelistdisguises.model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Disguise {
    SKELETON(Material.BONE, "&lSkelaton Disguise"),
    ZOMBIE(Material.ROTTEN_FLESH, "&lZombie Disguise"),
    SPIDER(Material.STRING, "&lSpider Disguise"),
    CREEPER(Material.GUNPOWDER, "&lCreeper Disguise"),
    CAVE_SPIDER(Material.CAVE_SPIDER_SPAWN_EGG, "&lCave Spider Disguise"),
    WITCH(Material.WITCH_SPAWN_EGG, "&lWitch Disguise"),
    SLIME(Material.SLIME_BALL, "&lSlime Disguise"),
    ENDERMAN(Material.ENDER_PEARL, "&lEnderman Disguise"),
    BLAZE(Material.BLAZE_ROD, "&lBlaze Disguise"),
    ;

    public final ItemStack menuItem;
    public final Material menuMaterial;
    public final String menuText;

    Disguise(Material menuMaterial, String menuText) {
        this.menuItem = new ItemStack(menuMaterial);
        this.menuMaterial = menuMaterial;
        this.menuText = menuText;
    }

    public boolean testItem(ItemStack itemStack) {
        return itemStack != null && itemStack.getType() == menuMaterial;
    }
}
