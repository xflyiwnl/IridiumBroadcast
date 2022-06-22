package me.xflyiwnl.iridiumbroadcast.utils;

import me.xflyiwnl.iridiumbroadcast.Main;
import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.manager.BroadcastManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;

public class BroadcastUtil {

    public static void saveBroadcasts(Player player, String broadcast) throws IOException {

        if (player == null) {
            return;
        }

        Config.getDatabaseYaml().set("database." + player.getName(), broadcast);
        Config.getDatabaseYaml().save(Config.getDatabaseFile());

    }

    public static void loadBroadcasts() throws IOException {

        if (Config.getDatabaseYaml().getConfigurationSection("database") == null) {
            return;
        }

        for (String key : Config.getDatabaseYaml().getConfigurationSection("database").getKeys(false)) {

            Player player = Bukkit.getPlayer(key);
            String broadcast = Config.getDatabaseYaml().getString("database." + key);

            BroadcastManager.broadcasts.put(player, new Broadcast(player, broadcast));

        }
    }

}
