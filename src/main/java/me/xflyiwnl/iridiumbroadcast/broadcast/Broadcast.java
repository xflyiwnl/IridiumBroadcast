package me.xflyiwnl.iridiumbroadcast.broadcast;

import me.xflyiwnl.iridiumbroadcast.chat.ChatMessages;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.gui.BroadcastGUI;
import me.xflyiwnl.iridiumbroadcast.utils.BroadcastUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Broadcast {

    private String broadcast;
    private Player player;


    public Broadcast(Player player, String broadcast) throws IOException {
        this.broadcast = broadcast;
        this.player = player;

        BroadcastUtil.saveBroadcasts(player, broadcast);
    }

    public String getBroadcast() {
        return broadcast;
    }

    public Player getPlayer() {
        return player;
    }

}
