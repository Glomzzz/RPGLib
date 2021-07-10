package com.skillw.rpglib.util;

import io.izzel.taboolib.internal.xseries.messages.ActionBar;
import io.izzel.taboolib.module.hologram.Hologram;
import io.izzel.taboolib.module.hologram.THologram;
import io.izzel.taboolib.module.locale.type.TLocaleBossBar;
import io.izzel.taboolib.module.locale.type.TLocaleTitle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class PlayerUtils {
    private PlayerUtils() {

    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    public static void sendTitle(Player player, String title, int fadeIn, int stay, int fadeOut) {
        player.resetTitle();
        player.sendTitle(title, null, fadeIn, stay, fadeOut);
    }

    public static void sendTitle(Player player, String title, String subtitle, int stay) {
        player.sendTitle(title, subtitle, 0, stay, 0);
    }

    public static void sendTitle(Player player, String title, int stay) {
        player.resetTitle();
        player.sendTitle(title, null, 0, stay, 0);
    }

    public static void sendSubTitle(Player player, String subTitle, int stay) {
        player.resetTitle();
        player.sendTitle(null, subTitle, 0, stay, 0);
    }

    public static void resetTitle(Player player) {
        player.resetTitle();
    }

    public static void sendActionBar(Player player, String text) {
        ActionBar.sendActionBar(player, text);
    }

    public static void sendActionBar(Player player, String text, long stay, JavaPlugin javaPlugin) {

        ActionBar.sendActionBar(javaPlugin, player, text, stay);
    }

    public static void resetActionBar(Player player) {
        ActionBar.clearActionBar(player);
    }

    public static void createHologramInTime(Location location, String text, int sec, Player... viewer) {
        Hologram hologram = THologram.create(location, text, viewer);
        hologram.setViewDistance(6);
        hologram.deleteOn(sec);
    }

    public static void createHologramInTime(Location location, String text, int tick, int distance, Plugin plugin, Player... viewer) {
        Hologram hologram = THologram.create(location, text, viewer);
        hologram.setViewDistance(distance);
        hologram.deleteOn(tick);
        new BukkitRunnable() {
            @Override
            public void run() {
                hologram.setLocation(hologram.getLocation().add(0, 0.1, 0));
            }
        }.runTaskTimerAsynchronously(plugin, tick / 10, 0);
    }

    public static void sendBossBar(final Player player, String text, BarColor color, BarStyle style, float progress, int ticks) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 1) {
            progress = 1;
        }
        TLocaleBossBar bossBar = new TLocaleBossBar(text, color, style, progress, ticks, true, true);
        bossBar.sendTo(player);

    }

    public static void sendBossBar(Player player, String text, int ticks) {
        TLocaleBossBar bossBar = new TLocaleBossBar(text, BarColor.PURPLE, BarStyle.SEGMENTED_10, 1, ticks, true, true);
        bossBar.sendTo(player);
    }

    public static void setCooldown(Player player, Material material, int cooldown) {
        player.setCooldown(material, cooldown);
    }

    public static void getCooldown(Player player, Material material) {
        player.getCooldown(material);
    }

    public static boolean hasCooldown(Player player, Material material) {
        return player.hasCooldown(material);
    }

    public static void removeCooldown(Player player, Material material) {
        player.setCooldown(material, 0);
    }
}
