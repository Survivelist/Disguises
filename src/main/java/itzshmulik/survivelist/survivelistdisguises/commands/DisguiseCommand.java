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
    public static final String TITLE = "Choose a disguise";

    private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());
    final ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    final ItemStack head = new ItemStack(Material.PLAYER_HEAD);
    final ItemStack undis = new ItemStack(Material.RED_WOOL);

    // Items to show in the gui for each mob
    final ItemStack skeleton = new ItemStack(Material.BONE);
    final ItemStack zombie = new ItemStack(Material.ROTTEN_FLESH);
    final ItemStack spider = new ItemStack(Material.STRING);
    final ItemStack creeper = new ItemStack(Material.GUNPOWDER);
    final ItemStack cave_spider = new ItemStack(Material.CAVE_SPIDER_SPAWN_EGG);
    final ItemStack witch = new ItemStack(Material.WITCH_SPAWN_EGG);
    final ItemStack slime = new ItemStack(Material.SLIME_BALL);

    public DisguiseCommand() {
        // Names for the gui items

        // Glass Item
        ItemMeta glass_meta = glass.getItemMeta();
        glass_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r"));
        glass.setItemMeta(glass_meta);

        // Undisguise Item
        ItemMeta undis_meta = undis.getItemMeta();
        undis_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lUndisguise"));
        undis.setItemMeta(undis_meta);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Moved reload section up above player check so console can reload the plugin too
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            Player player = (Player) sender;
            if (player.hasPermission("disguises.reload")) {
                plugin.reloadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloading config!"));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have no permission to use that command!"));
            }
        }
        // Exit if not player
        if (!(sender instanceof Player)) {
            return false;
        }
        // Player
        Player player = (Player) sender;

        Inventory gui = Bukkit.createInventory(player, 45, TITLE);

        // Names for the gui items (common forms moved to constructor)

        // Head Item
        final ItemStack head = new ItemStack(this.head); // Makes local copy
        SkullMeta head_meta = (SkullMeta) head.getItemMeta();
        head_meta.setOwningPlayer(player);
        head_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6" + player.getName()));
        head.setItemMeta(head_meta);

        // Skeleton Disguise
        final ItemStack skeleton = new ItemStack(this.skeleton); // local copy
        ItemMeta skeleton_meta = skeleton.getItemMeta();
        if (player.hasPermission("disguise.skelaton")) {

            skeleton_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSkelaton Disguise"));

        } else {

            skeleton_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSkelaton Disguise"));

        }
        skeleton.setItemMeta(skeleton_meta);

        // Zombie Disguise
        final ItemStack zombie = new ItemStack(this.zombie); // local copy
        ItemMeta zombie_meta = zombie.getItemMeta();
        if (player.hasPermission("disguise.zombie")) {

            zombie_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lZombie Disguise"));

        } else {

            zombie_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lZombie Disguise"));

        }
        zombie.setItemMeta(zombie_meta);

        // Spider Disguise
        final ItemStack spider = new ItemStack(this.spider); // local copy
        ItemMeta spider_meta = spider.getItemMeta();
        if (player.hasPermission("disguise.spider")) {

            spider_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSpider Disguise"));

        } else {

            spider_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSpider Disguise"));

        }
        spider.setItemMeta(spider_meta);

        // Creeper Disguise
        final ItemStack creeper = new ItemStack(this.creeper); // local copy
        ItemMeta creeper_meta = creeper.getItemMeta();
        if (player.hasPermission("disguise.creeper")) {

            creeper_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCreeper Disguise"));

        } else {

            creeper_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCreeper Disguise"));

        }
        creeper.setItemMeta(creeper_meta);

        // CaveSpider Disguise
        final ItemStack cave_spider = new ItemStack(this.cave_spider); // local copy
        ItemMeta cave_meta = cave_spider.getItemMeta();
        if (player.hasPermission("disguise.cavespider")) {

            cave_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCave Spider Disguise"));

        } else {

            cave_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCave Spider Disguise"));

        }
        cave_spider.setItemMeta(cave_meta);

        // Witch Disguise
        final ItemStack witch = new ItemStack(this.witch); // local copy
        ItemMeta witch_meta = witch.getItemMeta();
        if (player.hasPermission("disguise.witch")) {

            witch_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lWitch Disguise"));

        } else {

            witch_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lWitch Disguise"));

        }
        witch.setItemMeta(witch_meta);

        // Slime Disguise
        final ItemStack slime = new ItemStack(this.slime); // local copy
        ItemMeta slime_meta = slime.getItemMeta();
        if (player.hasPermission("disguise.slime")) {

            slime_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSlime Disguise"));

        } else {

            slime_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lSlime Disguise"));

        }
        slime.setItemMeta(slime_meta);

        // List of items to put in the gui
        gui.setItem(10, skeleton);
        gui.setItem(11, zombie);
        gui.setItem(12, spider);
        gui.setItem(13, creeper);
        gui.setItem(14, cave_spider);
        gui.setItem(15, witch);
        gui.setItem(16, slime);

        gui.setItem(36, undis);
        gui.setItem(40, head);

        // Moved filler operation to after placing our items in
        for (int i = 0; i < gui.getSize(); i++) { // < is exclusive, it'll still stop at 44 just making the code more dynamic
            if (gui.getItem(i) == null) {
                gui.setItem(i, glass);
            }
        }

        player.openInventory(gui);
        return true;
    }
}
