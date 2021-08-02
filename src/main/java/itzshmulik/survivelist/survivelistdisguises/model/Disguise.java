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
    ;

    public final ItemStack menuItem;
    public final String menuText;

    Disguise(Material menuMaterial, String menuText) {
        this.menuItem = new ItemStack(menuMaterial);
        this.menuText = menuText;
    }
}
