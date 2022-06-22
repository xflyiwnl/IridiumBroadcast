package me.xflyiwnl.iridiumbroadcast.manager;

import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.chat.ChatMessages;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BroadcastManager {

    public static HashMap<Player, Broadcast> broadcasts = new HashMap<Player, Broadcast>();

    public static Broadcast getBroadcasts(Player player) {
        return broadcasts.get(player);
    }

    public static void broadcast(Player player, Player admin, String broadcast) {

        String PREFIX = Config.getLanguageYaml().getString("language.chat-prefix");

        for (String msg : Config.getSettingsYaml().getStringList("settings.broadcast-format")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        msg.replace("{prefix}", PREFIX).
                                replace("{player}", player.getName()).
                                replace("{admin}", admin.getName()).
                                replace("{broadcast}", broadcast)));
            }
        }

    }

    public static void notification() {

        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (!admin.hasPermission("iridiumbroadcast.admin")) {
                break;
            }

            ChatMessages.notification(admin);

        }

    }

}
