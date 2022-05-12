package me.xflyiwnl.iridiumbroadcast;

import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import me.xflyiwnl.iridiumbroadcast.commands.BroadcastCommand;
import me.xflyiwnl.iridiumbroadcast.commands.TabCompleter;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.manager.CooldownManager;
import me.xflyiwnl.iridiumbroadcast.manager.EventManager;
import me.xflyiwnl.iridiumbroadcast.utils.BroadcastUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    public static HashMap<Player, Broadcast> broadcasts = new HashMap<Player, Broadcast>();

    private static Main main;

    @Override
    public void onEnable() {

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Vault не найден, плагин был выключен!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        main = this;

        new Config();
        Bukkit.getPluginCommand("iridiumbroadcast").setExecutor(new BroadcastCommand());
        Bukkit.getPluginCommand("iridiumbroadcast").setTabCompleter(new TabCompleter());
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);
        Metrics metrics = new Metrics(this, 15174);
        new CooldownManager();

        try {
            BroadcastUtil.loadBroadcasts();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Main getMain() {
        return main;
    }

    public static Broadcast getBroadcasts(Player player) {
        return broadcasts.get(player);
    }

    public static Economy getEconomy() {
        return econ;
    }

}
