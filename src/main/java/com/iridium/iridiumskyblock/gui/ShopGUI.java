package com.iridium.iridiumskyblock.gui;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.configs.Shop;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShopGUI extends GUI implements Listener {

    public ShopGUI root;

    public Shop.ShopObject shop;

    public int page;

    public Map<Integer, ShopGUI> shops = new HashMap<>();

    public Map<Integer, Shop.ShopItem> items = new HashMap<>();

    public ShopGUI() {
        super(IridiumSkyblock.getInventories().shopGUISize, IridiumSkyblock.getInventories().shopGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);

        for (Shop.ShopObject shop : IridiumSkyblock.getShop().shop) {
            setItem(shop.slot, Utils.makeItem(shop.display, 1, shop.displayName));
            if (!shops.containsKey(shop.slot)) {
                shops.put(shop.slot, new ShopGUI(shop, this));
            }
        }
    }

    public ShopGUI(Shop.ShopObject shop, int page, ShopGUI root) {
        super(IridiumSkyblock.getInventories().shopGUISize, IridiumSkyblock.getInventories().shopGUITitle);
        this.shop = shop;
        this.page = page;
        this.root = root;
    }

    public ShopGUI(Shop.ShopObject shop, ShopGUI root) {
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), this::addPages, 0, 5);
        this.shop = shop;
        this.root = root;

    }

    public void addPages() {
        for (Shop.ShopItem item : shop.items) {
            if (item == null) continue;
            if (!shops.containsKey(item.page)) {
                shops.put(item.page, new ShopGUI(shop, item.page, this));
            }
        }
    }

    @Override
    public void addContent() {
        super.addContent();
        if (getInventory().getViewers().isEmpty()) return;
        if (!IridiumSkyblock.getConfiguration().islandShop) return;
        if (shop != null) {
            for (Shop.ShopItem item : shop.items) {
                if (item.page == page) {
                    items.put(item.slot, item);
                    setItem(item.slot, Utils.makeItem(item.material, item.amount, item.displayName,
                            Utils.color(Utils.processMultiplePlaceholders(IridiumSkyblock.getShop().lore, Arrays.asList(
                                    new Utils.Placeholder("buyvaultprice", Utils.NumberFormatter.format(item.buyVault)),
                                    new Utils.Placeholder("sellvaultprice", Utils.NumberFormatter.format(item.sellVault)),
                                    new Utils.Placeholder("buycrystalprice", Utils.NumberFormatter.format(item.buyCrystals)),
                                    new Utils.Placeholder("sellcrystalprice", Utils.NumberFormatter.format(item.sellCrystals)))))));
                }
            }
            setItem(getInventory().getSize() - 3, Utils.makeItem(IridiumSkyblock.getInventories().nextPage));
            setItem(getInventory().getSize() - 5, Utils.makeItem(IridiumSkyblock.getInventories().back));
            setItem(getInventory().getSize() - 7, Utils.makeItem(IridiumSkyblock.getInventories().previousPage));
        }
    }

    public int getAmount(Inventory inventory, XMaterial materials) {
        int total = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item == null) continue;
            if (materials.isSimilar(item)) {
                total += item.getAmount();
            }
        }
        return total;
    }

    public void removeAmount(Inventory inventory, XMaterial material, int amount) {
        int removed = 0;
        int index = 0;
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) {
                index++;
                continue;
            }
            if (removed >= amount) break;
            if (itemStack != null) {
                if (material.isSimilar(itemStack)) {
                    if (removed + itemStack.getAmount() <= amount) {
                        removed += itemStack.getAmount();
                        inventory.setItem(index, null);
                    } else {
                        itemStack.setAmount(itemStack.getAmount() - (amount - removed));
                        removed += amount;
                    }
                }
            }
            index++;
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getClickedInventory() == null || !e.getClickedInventory().equals(getInventory())) return;
            if (shop == null) {
                if (shops.containsKey(e.getSlot())) {
                    if (shops.get(e.getSlot()).shops.containsKey(1)) { // This should always be called, but just incase the user configured the plugin incorrectly
                        e.getWhoClicked().openInventory(shops.get(e.getSlot()).shops.get(1).getInventory());
                    }
                }
            } else {
                if (items.containsKey(e.getSlot())) {
                    Shop.ShopItem item = items.get(e.getSlot());
                    if (e.getClick().equals(ClickType.RIGHT)) {
                        sellItem((Player) e.getWhoClicked(), item, item.amount);
                    } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                        sellItem((Player) e.getWhoClicked(), item, item.material.parseMaterial().getMaxStackSize());
                    } else if (e.getClick().equals(ClickType.LEFT)) {
                        buyItem((Player) e.getWhoClicked(), item, item.amount);
                    } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                        buyItem((Player) e.getWhoClicked(), item, item.material.parseMaterial().getMaxStackSize());
                    }
                }
                if (e.getSlot() == getInventory().getSize() - 3) {
                    if (root.shops.containsKey(page + 1)) {
                        e.getWhoClicked().openInventory(root.shops.get(page + 1).getInventory());
                    }
                }
                if (e.getSlot() == getInventory().getSize() - 5) {
                    e.getWhoClicked().openInventory(root.root.getInventory());
                }
                if (e.getSlot() == getInventory().getSize() - 7) {
                    if (root.shops.containsKey(page - 1)) {
                        e.getWhoClicked().openInventory(root.shops.get(page - 1).getInventory());
                    }
                }
            }
        }
        for (ShopGUI gui : shops.values()) {
            gui.onInventoryClick(e);
        }
    }

    private void sellItem(Player player, Shop.ShopItem item, int amount) {
        int itemsInInventory = getAmount(player.getInventory(), item.material);
        IridiumSkyblock.getInstance().getLogger().info(amount + " " + itemsInInventory);
        if (itemsInInventory < amount) amount = itemsInInventory;
        if ((item.sellVault > 0 || item.sellCrystals > 0) && itemsInInventory > 0 && item.commands == null) {
            double sellVault = item.sellVault / item.amount * amount;
            int sellCrystals = (int) Math.floor((item.sellCrystals / (double) item.amount) * amount);

            removeAmount(player.getInventory(), item.material, amount);

            Utils.pay(player, sellVault, sellCrystals);
            player.sendMessage(Utils.color(IridiumSkyblock.getMessages().shopSoldMessage
                    .replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)
                    .replace("%item%", item.material + "")
                    .replace("%amount%", amount + "")
                    .replace("%crystals%", Utils.NumberFormatter.format(sellCrystals))
                    .replace("%money%", Utils.NumberFormatter.format(sellVault))));
        } else {
            player.sendMessage(Utils.color(IridiumSkyblock.getMessages().cannotSellItem.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    private void buyItem(Player player, Shop.ShopItem item, int amount) {
        if (item.commands != null) amount = 1;
        if (item.buyVault > 0 || item.buyCrystals > 0) {
            double buyVault = item.buyVault / item.amount * amount;
            int buyCrystals = (int) (item.buyCrystals / ((double) item.amount * amount));
            Utils.BuyResponse response = Utils.canBuy(player, buyVault, buyCrystals);
            if (response == Utils.BuyResponse.SUCCESS) {
                if (item.commands == null) {
                    ItemStack itemStack = item.material.parseItem();
                    itemStack.setAmount(amount);
                    if (Utils.hasOpenSlot(player.getInventory())) {
                        player.getInventory().addItem(itemStack);
                    } else {
                        player.getLocation().getWorld().dropItem(player.getLocation(), itemStack);
                    }
                } else {
                    for (String command : item.commands) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
                    }
                }
                player.sendMessage(Utils.color(IridiumSkyblock.getMessages().shopBoughtMessage
                        .replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)
                        .replace("%item%", item.material + "")
                        .replace("%amount%", amount + "")
                        .replace("%crystals%", Utils.NumberFormatter.format(buyCrystals))
                        .replace("%money%", Utils.NumberFormatter.format(buyVault))));
            } else {
                player.sendMessage(Utils.color(response == Utils.BuyResponse.NOT_ENOUGH_VAULT ? IridiumSkyblock.getMessages().cantBuy : IridiumSkyblock.getMessages().notEnoughCrystals.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            player.sendMessage(Utils.color(IridiumSkyblock.getMessages().cannotBuyItem.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }
}