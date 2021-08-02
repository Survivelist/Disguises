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

        if (Disguise.SKELETON.testItem(currentItem)) {
            if (player.hasPermission("disguise.skelaton")) {

                player.closeInventory();
                disguised.put(player, Disguise.SKELETON);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }

        } else if (Disguise.ZOMBIE.testItem(currentItem)) {
            if (player.hasPermission("disguise.zombie")) {

                player.closeInventory();
                disguised.put(player, Disguise.ZOMBIE);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }
        } else if (Disguise.SPIDER.testItem(currentItem)) {
            if (player.hasPermission("disguise.spider")) {

                player.closeInventory();
                disguised.put(player, Disguise.SPIDER);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }
        }else if(Disguise.CREEPER.testItem(currentItem)) {
            if(player.hasPermission("disguise.creeper")){

                player.closeInventory();
                disguised.put(player, Disguise.CREEPER);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }
        } else if (Disguise.CAVE_SPIDER.testItem(currentItem)) {
            if(player.hasPermission("disguise.cavespider")){

                player.closeInventory();
                disguised.put(player, Disguise.CAVE_SPIDER);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                player.setInvisible(true);
            }else if(Disguise.WITCH.testItem(currentItem)){
                if(player.hasPermission("disguise.witch")){

                    player.closeInventory();
                    disguised.put(player, Disguise.WITCH);

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                    player.setInvisible(true);
                }
            }else if(Disguise.SLIME.testItem(currentItem)){
                if(player.hasPermission("disguise.slime")){

                    player.closeInventory();
                    disguised.put(player, Disguise.SLIME);

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                    player.setInvisible(true);
                }
            }else if(Disguise.ENDERMAN.testItem(currentItem)){
                if(player.hasPermission("disguise.enderman")){

                    player.closeInventory();
                    disguised.put(player, Disguise.ENDERMAN);

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                    player.setInvisible(true);
                }
            }
        }else if (currentItem.getType() == Material.RED_WOOL) { // Undis item
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
