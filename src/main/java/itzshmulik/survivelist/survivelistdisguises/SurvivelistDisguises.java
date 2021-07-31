package itzshmulik.survivelist.survivelistdisguises;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import itzshmulik.survivelist.survivelistdisguises.Commands.DisguiseCommand;
import itzshmulik.survivelist.survivelistdisguises.Events.ClickEvent;
import org.jetbrains.annotations.NotNull;

public final class SurvivelistDisguises extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("disguise").setExecutor(new DisguiseCommand());

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            if(command.getName().equalsIgnoreCase("dreload")){
                Player player = (Player) sender;

                if(player.hasPermission("disguises.reload")){
                    reloadConfig();

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloading config!"));
                }else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have no permission to use that command!"));
                }
            }
        }
        return false;
    }
}
