package me.xflyiwnl.iridiumbroadcast.gui;

import me.xflyiwnl.iridiumbroadcast.Main;
import me.xflyiwnl.iridiumbroadcast.broadcast.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class BroadcastGUI {

    private static Inventory broadcastGUI;
    public static HashMap<Integer, Player> slot = new HashMap<Integer, Player>();

    public BroadcastGUI(Player player) {
        broadcastGUI = Bukkit.createInventory(null, 54, "Список объявлений:");
        setSlot();
    }

    public void setSlot() {

        slot.clear();
        for (Player p : Main.broadcasts.keySet()) {
            for (int i = 0; i == Main.broadcasts.size(); i++) {

                ItemStack broadcastItem = new ItemStack(Material.BOOK, 1);
                ItemMeta broadcastMeta = broadcastItem.getItemMeta();


                broadcastGUI.setItem(i, broadcastItem);
            }
        }
    }

    public static Inventory getBroadcastGUI() {
        return broadcastGUI;
    }

}
