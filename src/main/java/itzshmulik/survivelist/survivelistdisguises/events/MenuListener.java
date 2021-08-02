package itzshmulik.survivelist.survivelistdisguises.events;

import itzshmulik.survivelist.survivelistdisguises.commands.DisguiseCommand;
import itzshmulik.survivelist.survivelistdisguises.model.Disguise;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class MenuListener implements Listener {

    private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());
    private final HashMap<Player, Disguise> disguised = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler(ignoreCancelled = true)
    public void onDisguiseMenuClick(InventoryClickEvent e){
        // check view title; if !=, exit
        if (!e.getView().getTitle().equals(DisguiseCommand.TITLE)) {
            return;
        }
        // get item in the clicked slot
        final ItemStack currentItem = e.getCurrentItem();
        if (currentItem == null) return; // Ignore events where the slot is empty
        // get messages from config
        String prefix = plugin.getConfig().getString("Messages-prefix");
        String d_msg = plugin.getConfig().getString("Disguise-message");
        String und_msg = plugin.getConfig().getString("Undisguise-message");

        // cast the player
        final Player player = (Player) e.getWhoClicked();

        // clicked material type
        Material type = currentItem.getType();

        if (type == Disguise.SKELETON.menuMaterial) {
            if (player.hasPermission("disguise.skelaton")) {

                player.closeInventory();
                disguised.put(player, Disguise.SKELETON);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }

        } else if (type == Disguise.ZOMBIE.menuMaterial) {
            if (player.hasPermission("disguise.zombie")) {

                player.closeInventory();
                disguised.put(player, Disguise.ZOMBIE);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }
        } else if (type == Disguise.SPIDER.menuMaterial) {
            if (player.hasPermission("disguise.spider")) {

                player.closeInventory();
                disguised.put(player, Disguise.SPIDER);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }

            // Undis Item
        } else if (type == Material.RED_WOOL) {
            if (disguised.containsKey(player)) {

                player.closeInventory();
                disguised.remove(player);
                player.setInvisible(false);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + und_msg));

            } else {

                player.closeInventory();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " &cYou are not disguised!"));

            }
        }
    }

}
