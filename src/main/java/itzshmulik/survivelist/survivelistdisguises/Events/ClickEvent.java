package itzshmulik.survivelist.survivelistdisguises.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public void ClickEvent(InventoryClickEvent e){

        String title = "Choose a disguise";

        if(e.getView().getTitle().equals(title)){

            boolean isDisguised = false;

            Player player = (Player) e.getWhoClicked();

            switch(e.getCurrentItem().getType()){
                case BONE:

                    if(player.hasPermission("disguise.skelaton")) {

                        player.closeInventory();
                        isDisguised = true;

                        player.setInvisible(true);

                    }
            }

            e.setCancelled(true);

        }
    }

}
