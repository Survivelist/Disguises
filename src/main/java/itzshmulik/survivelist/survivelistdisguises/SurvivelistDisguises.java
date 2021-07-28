package itzshmulik.survivelist.survivelistdisguises;

import itzshmulik.survivelist.survivelistdisguises.Commands.DisguiseCommand;
import itzshmulik.survivelist.survivelistdisguises.Events.ClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivelistDisguises extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("disguise").setExecutor(new DisguiseCommand());

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
