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

            Player player = (Player) e.getWhoClicked();

            switch(e.getCurrentItem().getType()){
                case BONE:
                    player.closeInventory();
            }

            e.setCancelled(true);

        }
    }

}
