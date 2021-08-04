package itzshmulik.survivelist.survivelistdisguises.commands;

import itzshmulik.survivelist.survivelistdisguises.model.Disguise;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
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
    static final String DEFAULT_COLOR = "&a";
    static final String DISABLED_COLOR = "&c";

    private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());
    final ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    final ItemStack head = new ItemStack(Material.PLAYER_HEAD);
    final ItemStack undis = new ItemStack(Material.RED_WOOL);

    // Items to show in the gui for each mob
    //style=enabled
    final ItemStack skeleton, zombie, spider, creeper, caveSpider, witch, slime, enderman, blaze;
    //style=disabled (copy type)
    final ItemStack skeletonDisabled, zombieDisabled, spiderDisabled, creeperDisabled, caveSpiderDisabled, witchDisabled, slimeDisabled, endermanDisabled, blazeDisabled;

    public DisguiseCommand() {
        // Names for the gui items

        // Glass Item
        nameItemColor(glass, "&r", "");

        // Undisguise Item
        nameItemColor(undis, "&c", "&lUndisguise");

        // Prepare toggleable items
        //skeleton
        this.skeleton = makeMenuItem(Disguise.SKELETON);
        skeletonDisabled = makeMenuItemDisabled(Disguise.SKELETON);
        //zombie
        this.zombie = makeMenuItem(Disguise.ZOMBIE);
        zombieDisabled = makeMenuItemDisabled(Disguise.ZOMBIE);
        //spider
        this.spider = makeMenuItem(Disguise.SPIDER);
        spiderDisabled = makeMenuItemDisabled(Disguise.SPIDER);
        //creeper
        this.creeper = makeMenuItem(Disguise.CREEPER);
        creeperDisabled = makeMenuItemDisabled(Disguise.CREEPER);
        //cave_spider
        this.caveSpider = makeMenuItem(Disguise.CAVE_SPIDER);
        caveSpiderDisabled = makeMenuItemDisabled(Disguise.CAVE_SPIDER);
        //witch
        this.witch = makeMenuItem(Disguise.WITCH);
        witchDisabled = makeMenuItemDisabled(Disguise.WITCH);
        //slime
        this.slime = makeMenuItem(Disguise.SLIME);
        slimeDisabled = makeMenuItemDisabled(Disguise.SLIME);
        //enderman
        this.enderman = makeMenuItem(Disguise.ENDERMAN);
        endermanDisabled = makeMenuItemDisabled(Disguise.ENDERMAN);
        //blaze
        this.blaze = makeMenuItem(Disguise.BLAZE);
        blazeDisabled = makeMenuItemDisabled(Disguise.BLAZE);
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

            if(args.length == 0) {
                // Exit if not player
                if (!(sender instanceof Player)) {
                    return false;
                }
                // Player
                Player player = (Player) sender;

                Inventory gui = Bukkit.createInventory(player, 45, TITLE);

                // Names for the gui items (common forms moved to constructor)

                // Head Item
                final ItemStack head = new ItemStack(this.head); // Make local copy
                SkullMeta head_meta = (SkullMeta) head.getItemMeta();
                head_meta.setOwningPlayer(player);
                head_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6" + player.getName()));
                head.setItemMeta(head_meta);

                // Skeleton Disguise
                final ItemStack skeleton = player.hasPermission("disguise.skelaton") ? this.skeleton : skeletonDisabled;

                // Zombie Disguise
                final ItemStack zombie = player.hasPermission("disguise.zombie") ? this.zombie : zombieDisabled;

                // Spider Disguise
                final ItemStack spider = player.hasPermission("disguise.spider") ? this.spider : spiderDisabled;

                // Creeper Disguise
                final ItemStack creeper = player.hasPermission("disguise.creeper") ? this.creeper : creeperDisabled;

                // CaveSpider Disguise
                final ItemStack cave_spider = player.hasPermission("disguise.cavespider") ? caveSpider : caveSpiderDisabled;

                // Witch Disguise
                final ItemStack witch = player.hasPermission("disguise.witch") ? this.witch : witchDisabled;

                // Slime Disguise
                final ItemStack slime = player.hasPermission("disguise.slime") ? this.slime : slimeDisabled;

                // EnderMan Disguise
                final ItemStack enderman = player.hasPermission("disguise.enderman") ? this.enderman : endermanDisabled;

                // Place items in the gui
                gui.setItem(10, skeleton);
                gui.setItem(11, zombie);
                gui.setItem(12, spider);
                gui.setItem(13, creeper);
                gui.setItem(14, cave_spider);
                gui.setItem(15, witch);
                gui.setItem(16, slime);
                gui.setItem(19, enderman);

                gui.setItem(36, undis);
                gui.setItem(40, head);

                // Moved filler operation to after placing our items in
                for (int i = 0; i < gui.getSize(); i++) { // < is exclusive, it'll still stop at 44 just making the code more dynamic
                    if (gui.getItem(i) == null) {
                        gui.setItem(i, glass);
                    }
                }

                player.openInventory(gui);
            }
        return true;
    }

    // makes item
    static ItemStack makeMenuItem(Disguise disguise) {
        return nameItemColor(new ItemStack(disguise.menuItem), DEFAULT_COLOR, disguise.menuText);
    }

    // makes item
    static ItemStack makeMenuItemDisabled(Disguise disguise) {
        return nameItemColor(new ItemStack(disguise.menuItem), DISABLED_COLOR, disguise.menuText);
    }

    // mutates item
    static ItemStack nameItemColor(ItemStack item, String color, String name) {
        final ItemMeta meta = item.getItemMeta();
        //noinspection deprecation
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', color + name));
        item.setItemMeta(meta);
        return item;
    }
}
