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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    private static Logger logger = Bukkit.getLogger();

    private static Main main;

    @Override
    public void onEnable() {

        setupEconomy();

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

        logo();

    }

    public void logo() {

        logger.info(" ");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    ██╗██████╗░██╗██████╗░██╗██╗░░░██╗███╗░░░███╗");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    ██║██╔══██╗██║██╔══██╗██║██║░░░██║████╗░████║");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    ██║██████╔╝██║██║░░██║██║██║░░░██║██╔████╔██║");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    ██║██╔══██╗██║██║░░██║██║██║░░░██║██║╚██╔╝██║");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    ██║██║░░██║██║██████╔╝██║╚██████╔╝██║░╚═╝░██║");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    ╚═╝╚═╝░░╚═╝╚═╝╚═════╝░╚═╝░╚═════╝░╚═╝░░░░░╚═╝");
        logger.info(" ");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    Plugin is enabled!");
        logger.info(this.getName() + ChatColor.DARK_GREEN + "   |    Author: Iridium Studio");
        logger.info(" ");
    }

    private boolean setupEconomy() {
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

    public static Economy getEconomy() {
        return econ;
    }

}
