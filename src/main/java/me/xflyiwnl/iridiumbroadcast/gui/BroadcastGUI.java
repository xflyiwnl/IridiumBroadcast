package me.xflyiwnl.iridiumbroadcast.gui;

import me.xflyiwnl.iridiumbroadcast.Main;
import me.xflyiwnl.iridiumbroadcast.config.Config;
import me.xflyiwnl.iridiumbroadcast.manager.BroadcastManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BroadcastGUI {

    private static Inventory broadcastGUI;
    public static HashMap<Integer, Player> slot = new HashMap<Integer, Player>();

    public static void createInventory(Player player) {
        broadcastGUI = Bukkit.createInventory(null, 54, Config.getGuiYaml().getString(ChatColor.translateAlternateColorCodes('&',
                "gui.broadcast.gui-title")));
        player.openInventory(broadcastGUI);
        setSlot();
    }

    private static void setSlot() {

        slot.clear();
        for (Player p : BroadcastManager.broadcasts.keySet()) {
            for (int i = 0; i < BroadcastManager.broadcasts.size(); i++) {

                if (p == null) {
                    break;
                }

                if (i == 55) {
                    break;
                }

                ItemStack broadcastItem = new ItemStack(Material.valueOf(Config.getGuiYaml().getString("gui.broadcast.item-format.display-item")), 1);
                ItemMeta broadcastMeta = broadcastItem.getItemMeta();
                broadcastMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        Config.getGuiYaml().getString("gui.broadcast.item-format.display-name").replace("{player}", p.getName())));
                List<String> broadcastLore = new ArrayList<>();

                for (String s : Config.getGuiYaml().getStringList("gui.broadcast.item-format.lore")) {
                    broadcastLore.add(ChatColor.translateAlternateColorCodes('&', s.
                            replace("{player}", p.getPlayer().getName()).
                            replace("{broadcast}", BroadcastManager.broadcasts.get(p).getBroadcast())));
                }

                broadcastMeta.setLore(broadcastLore);
                broadcastItem.setItemMeta(broadcastMeta);
                broadcastGUI.setItem(i, broadcastItem);
                slot.put(i, p);
            }
        }
    }

    public static Inventory getBroadcastGUI() {
        return broadcastGUI;
    }

    public static HashMap<Integer, Player> getSlot() {
        return slot;
    }
}
