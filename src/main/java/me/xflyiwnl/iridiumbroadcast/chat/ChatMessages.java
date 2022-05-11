package me.xflyiwnl.iridiumbroadcast.chat;

import me.xflyiwnl.iridiumbroadcast.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatMessages {

    private static String PREFIX = Config.getLanguageYaml().getString("language.chat-prefix");

    public static void isNotPlayer(Player p) {

        for (String msg : Config.getLanguageYaml().
                getStringList("language.errors.is-not.player")) {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    msg.replace("{prefix}", PREFIX)));

        }

    }

    public static void args(Player p) {

        for (String msg : Config.getLanguageYaml().
                getStringList("language.args")) {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    msg.replace("{prefix}", PREFIX)));

        }

    }

    public static void alreadySent(Player p) {

        for (String msg : Config.getLanguageYaml().
                getStringList("language.errors.already-sent")) {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    msg.replace("{prefix}", PREFIX)));

        }

    }

}
