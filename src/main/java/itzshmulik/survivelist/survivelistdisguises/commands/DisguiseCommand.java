package itzshmulik.survivelist.survivelistdisguises.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DisguiseCommand implements CommandExecutor {

    private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;

                String title = "Choose a disguise";

                Inventory gui = Bukkit.createInventory(player, 45, title);

                ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                ItemStack undis = new ItemStack(Material.RED_WOOL);

                // Items to show in the gui for each mob
                ItemStack skelaton = new ItemStack(Material.BONE);
                ItemStack zombie = new ItemStack(Material.ROTTEN_FLESH);
                ItemStack spider = new ItemStack(Material.STRING);
                ItemStack creeper = new ItemStack(Material.GUNPOWDER);
                ItemStack cave_spider = new ItemStack(Material.CAVE_SPIDER_SPAWN_EGG);
                ItemStack witch = new ItemStack(Material.WITCH_SPAWN_EGG);
                ItemStack slime = new ItemStack(Material.SLIME_BALL);

                // Names for the gui items

                // Glass Item
                ItemMeta glass_meta = glass.getItemMeta();
                glass_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r"));
                glass.setItemMeta(glass_meta);

                // Head Item
                SkullMeta head_meta = (SkullMeta) head.getItemMeta();
                head_meta.setOwningPlayer(player);
                head_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6" + player.getName()));
                head.setItemMeta(head_meta);

                // Undisguise Item
                ItemMeta undis_meta = undis.getItemMeta();
                undis_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lUndisguise"));
                undis.setItemMeta(undis_meta);


                // Skelaton Disguise
                ItemMeta skelaton_meta = skelaton.getItemMeta();
                if (player.hasPermission("disguise.skelaton")) {

                    skelaton_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSkelaton Disguise"));

                } else {

                    skelaton_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSkelaton Disguise"));

                }
                skelaton.setItemMeta(skelaton_meta);

                // Zombie Disguise
                ItemMeta zombie_meta = zombie.getItemMeta();
                if (player.hasPermission("disguise.zombie")) {

                    zombie_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lZombie Disguise"));

                } else {

                    zombie_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZombie Disguise"));

                }
                zombie.setItemMeta(zombie_meta);

                // Spider Disguise
                ItemMeta spider_meta = spider.getItemMeta();
                if (player.hasPermission("disguise.spider")) {

                    spider_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSpider Disguise"));

                } else {

                    spider_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSpider Disguise"));

                }
                spider.setItemMeta(spider_meta);

                // Creeper Disguise
                ItemMeta creeper_meta = creeper.getItemMeta();
                if (player.hasPermission("disguise.creeper")) {

                    creeper_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCreeper Disguise"));

                } else {

                    creeper_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCreeper Disguise"));

                }
                creeper.setItemMeta(creeper_meta);

                // CaveSpider Disguise
                ItemMeta cave_meta = cave_spider.getItemMeta();
                if (player.hasPermission("disguise.cavespider")) {

                    cave_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCave Spider Disguise"));

                } else {

                    cave_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCave Spider Disguise"));

                }
                cave_spider.setItemMeta(cave_meta);

                // Witch Disguise
                ItemMeta witch_meta = witch.getItemMeta();
                if (player.hasPermission("disguise.witch")) {

                    witch_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lWitch Disguise"));

                } else {

                    witch_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lWitch Disguise"));

                }
                witch.setItemMeta(witch_meta);

                // Slime Disguise
                ItemMeta slime_meta = slime.getItemMeta();
                if (player.hasPermission("disguise.slime")) {

                    slime_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSlime Disguise"));

                } else {

                    slime_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSlime Disguise"));

                }
                slime.setItemMeta(slime_meta);


                for (int i = 0; i <= 44; i++) {
                    if (gui.getItem(i) == null) {
                        gui.setItem(i, glass);
                    }
                }

                // List of items to put in the gui
                gui.setItem(10, skelaton);
                gui.setItem(11, zombie);
                gui.setItem(12, spider);
                gui.setItem(13, creeper);
                gui.setItem(14, cave_spider);
                gui.setItem(15, witch);
                gui.setItem(16, slime);

                gui.setItem(36, undis);
                gui.setItem(40, head);
                player.openInventory(gui);


            }else {
                if(args[0].equalsIgnoreCase("reload")){
                    Player player = (Player) sender;
                    if(player.hasPermission("disguises.reload")) {
                        plugin.reloadConfig();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloading config!"));
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have no permission to use that command!"));
                    }
                }
            }
        }




        return true;
    }
}
