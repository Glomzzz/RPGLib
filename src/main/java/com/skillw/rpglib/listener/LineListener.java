package com.skillw.rpglib.listener;

import com.skillw.rpglib.RPGLib;
import io.izzel.taboolib.module.db.sql.query.Where;
import io.izzel.taboolib.module.inject.TListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@TListener
public class LineListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        RPGLib.getName_uuid().delete(Where.is("uuid", uuid.toString())).run(RPGLib.getDatabase());
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (RPGLib.getEffectManager().hasKey(uuid)) {
            RPGLib.getEffectManager().get(uuid).getObjects().forEach(effects -> effects.forEach(effect -> effect.unRealize(player, true)));
        }
    }
}
