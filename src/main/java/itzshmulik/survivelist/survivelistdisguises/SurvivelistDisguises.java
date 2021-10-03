package itzshmulik.survivelist.survivelistdisguises;

import itzshmulik.survivelist.survivelistdisguises.util.HeadGrabber;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import itzshmulik.survivelist.survivelistdisguises.commands.DisguiseCommand;
import itzshmulik.survivelist.survivelistdisguises.events.MenuListener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public final class SurvivelistDisguises extends JavaPlugin {

    private static SurvivelistDisguises instance;
    private final HashMap<String, ItemStack> headsByTexture = new HashMap<>();
    private final HashMap<UUID, ItemStack> headsByPlayer = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getCommand("disguise").setExecutor(new DisguiseCommand());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // clear caches
        headsByTexture.clear();
        headsByPlayer.clear();
    }

    /**
     * Get a player head item by its base64 texture value.
     *
     * @param textureValue the texture's Base64 string
     * @return a new player head item
     */
    public static ItemStack headByTexture(@NotNull String textureValue) {
        final ItemStack itemStack = instance.headsByTexture.get(textureValue);
        if (itemStack != null) return new ItemStack(itemStack);
        final ItemStack generated = new HeadGrabber.CustomTexture(textureValue).generate();
        synchronized (instance.headsByTexture) {
            instance.headsByTexture.put(textureValue, generated);
        }
        return new ItemStack(generated);
    }

    /**
     * Get a custom-named player head item by its base64 texture value.
     *
     * @param textureValue the texture's Base64 string
     * @param itemName a custom item name
     * @return a new player head item
     * @implNote If {@code itemName} is null, this implementation simply
     *           delegates to {@link #headByTexture(String)} where
     *           {@code textureValue} is passed as the String parameter.
     */
    public static ItemStack headByTextureWithName(@NotNull String textureValue, String itemName) {
        if (itemName == null) return headByTexture(textureValue);
        final String cacheKey = textureValue + ":" + itemName;
        final ItemStack itemStack = instance.headsByTexture.get(cacheKey);
        if (itemStack != null) return new ItemStack(itemStack);
        final ItemStack generated = new HeadGrabber.CustomTexture(textureValue, itemName).generate();
        synchronized (instance.headsByTexture) {
            instance.headsByTexture.put(cacheKey, generated);
        }
        return new ItemStack(generated);
    }

    /**
     * Get a player head item for a Bukkit player.
     *
     * @param player the player (may also be online)
     * @return a new player head item
     */
    public static ItemStack headByPlayer(@NotNull OfflinePlayer player) {
        final UUID uniqueId = player.getUniqueId();
        final ItemStack itemStack = instance.headsByPlayer.get(uniqueId);
        if (itemStack != null) return new ItemStack(itemStack);
        final ItemStack generated = new HeadGrabber.Player(player).generate();
        synchronized (instance.headsByTexture) {
            instance.headsByPlayer.put(uniqueId, generated);
        }
        return new ItemStack(generated);
    }
}
