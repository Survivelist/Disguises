package itzshmulik.survivelist.survivelistdisguises.Events;

import itzshmulik.survivelist.survivelistdisguises.SurvivelistDisguises;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClickEvent implements Listener {

    private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());

    @EventHandler
    public void ClickEvent(InventoryClickEvent e){

        String title = "Choose a disguise";

        String prefix = plugin.getConfig().getString("Messages-prefix");
        String d_msg = plugin.getConfig().getString("Disguise-message");
        String und_msg = plugin.getConfig().getString("Undisguise-message");
        if(e.getView().getTitle().equals(title)){

            boolean isDisguised = false;

            Player player = (Player) e.getWhoClicked();

            switch(e.getCurrentItem().getType()){

                // Skelaton disguise
                case BONE:
                    if(player.hasPermission("disguise.skelaton")) {

                            player.closeInventory();
                            isDisguised = true;

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                            player.setInvisible(true);
                    }
                    break;

                // Zombie disguise
                case ROTTEN_FLESH:
                    if(player.hasPermission("disguise.zombie")){

                    player.closeInventory();
                    isDisguised = true;

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                    player.setInvisible(true);
                }
                    break;
                case STRING:
                    if(player.hasPermission("disguise.spider")){

                        player.closeInventory();
                        isDisguised = true;

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + d_msg));

                        player.setInvisible(true);
                    }
                    break;

                // Undis Item
                case RED_WOOL:
                    if(isDisguised == true){

                        player.closeInventory();
                        isDisguised = false;
                        player.setInvisible(false);

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " " + und_msg));

                    }else{

                        player.closeInventory();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " &cYou are not disguised!"));

                    }
                    break;
            }

            e.setCancelled(true);

        }
    }

}
