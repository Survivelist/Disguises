package itzshmulik.survivelist.survivelistdisguises;

import org.bukkit.plugin.java.JavaPlugin;
import itzshmulik.survivelist.survivelistdisguises.commands.DisguiseCommand;
import itzshmulik.survivelist.survivelistdisguises.events.MenuListener;
public final class SurvivelistDisguises extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("disguise").setExecutor(new DisguiseCommand());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }
}
