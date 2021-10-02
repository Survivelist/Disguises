package itzshmulik.survivelist.survivelistdisguises.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Produces player head ItemStacks.
 */
public interface HeadGrabber {
    /**
     * Generate a new player head item.
     *
     * @return a new item
     */
    @NotNull ItemStack generate();

    /**
     * Produce a player head from an OfflinePlayer.
     * <p>
     * Works with {@code Player} as well.
     */
    class Player implements HeadGrabber {
        final OfflinePlayer player;

        /**
         * Produce heads for the given player.
         *
         * @param player a Bukkit player
         */
        public Player(@NotNull OfflinePlayer player) {
            this.player = player;
        }

        @Override
        public @NotNull ItemStack generate() {
            final ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            final SkullMeta meta = (SkullMeta) head.getItemMeta();
            // try to use paper's profile system for Bukkit (online) Player objects
            if (player instanceof org.bukkit.entity.Player) {
                meta.setPlayerProfile(((org.bukkit.entity.Player) player).getPlayerProfile());
            } else {
                meta.setOwningPlayer(player);
            }
            head.setItemMeta(meta);
            return head;
        }
    }

    /**
     * Produce custom-texture player heads from a base64 value.
     */
    class CustomTexture implements HeadGrabber {
        static final Method SKULL_META_SET_PROFILE;

        static {
            try {
                // MC>=1.14
                SKULL_META_SET_PROFILE = new ItemStack(Material.PLAYER_HEAD).getItemMeta().getClass().getDeclaredMethod("setProfile", GameProfile.class);
                SKULL_META_SET_PROFILE.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(e);
            }
        }

        final GameProfile gameProfile;

        /**
         * Produce heads with the given base64 texture value.
         *
         * @param textureValue a Base64 texture string
         */
        public CustomTexture(@Nullable String textureValue) {
            this(textureValue, "Player");
        }

        /**
         * Produce heads with the given base64 texture value.
         *
         * @param textureValue a Base64 texture string
         * @param headName a custom name for the item
         */
        public CustomTexture(@Nullable String textureValue, String headName) {
            this.gameProfile = new GameProfile(UUID.randomUUID(), headName);
            gameProfile.getProperties().put("textures", new Property("textures", textureValue));
        }

        @Override
        public @NotNull ItemStack generate() throws IllegalStateException {
            final ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            final SkullMeta meta = (SkullMeta) head.getItemMeta();
            try {
                SKULL_META_SET_PROFILE.invoke(meta, gameProfile);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            head.setItemMeta(meta);
            return head;
        }
    }
}
