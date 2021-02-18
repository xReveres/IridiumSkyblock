package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.configs.Schematics;
import com.iridium.iridiumskyblock.gui.ConfirmationGUI;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegenCommand extends Command {

    public RegenCommand() {
        super(Arrays.asList("regen", "reset"), "Regenerate your island", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User user = User.getUser(p);
        if (user.getIsland() != null) {
            if (user.role.equals(Role.Owner)) {
                if (user.bypassing || user.getIsland().getPermissions(user.role).regen) {
                    long time = user.getIsland().canGenerate() / 1000;
                    if (time == 0 || user.bypassing) {
                        if (IridiumSkyblock.getInstance().getSchematics().schematicList.size() == 1) {
                            p.openInventory(new ConfirmationGUI(user.getIsland(), () -> {
                                for (Schematics.FakeSchematic schematic : IridiumSkyblock.getInstance().getSchematics().schematicList) {
                                    user.getIsland().schematic = schematic.overworldData.schematic;
                                    user.getIsland().netherschematic = schematic.netherData.schematic;
                                    user.getIsland().home = user.getIsland().home.add(schematic.x, schematic.y, schematic.z);
                                    break;
                                }
                                user.getIsland().pasteSchematic(true);
                                if (IridiumSkyblock.getInstance().getConfiguration().restartUpgradesOnRegen) {
                                    user.getIsland().resetMissions();
                                    user.getIsland().setSizeLevel(1);
                                    user.getIsland().setMemberLevel(1);
                                    user.getIsland().setWarpLevel(1);
                                    user.getIsland().setOreLevel(1);
                                }
                                user.getIsland().teleportPlayersHome();
                            }, IridiumSkyblock.getInstance().getMessages().resetAction).getInventory());
                        } else {
                            p.openInventory(user.getIsland().schematicSelectGUI.getInventory());
                        }
                    } else {
                        int day = (int) TimeUnit.SECONDS.toDays(time);
                        int hours = (int) Math.floor(TimeUnit.SECONDS.toHours(time - day * 86400L));
                        int minute = (int) Math.floor((time - day * 86400 - hours * 3600) / 60.00);
                        int second = (int) Math.floor((time - day * 86400 - hours * 3600) % 60.00);
                        p.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().regenCooldown.replace("%days%", day + "").replace("%hours%", hours + "").replace("%minutes%", minute + "").replace("%seconds%", second + "").replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                    }
                } else {
                    sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().mustBeIslandOwner.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
            }
        } else {
            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        }
    }

    @Override
    public void admin(CommandSender sender, String[] args, Island island) {
        Player p = (Player) sender;
        if (island != null) {
            if (IridiumSkyblock.getInstance().getSchematics().schematicList.size() == 1) {
                p.openInventory(new ConfirmationGUI(island, () -> {
                    for (Schematics.FakeSchematic schematic : IridiumSkyblock.getInstance().getSchematics().schematicList) {
                        island.schematic = schematic.overworldData.schematic;
                        island.netherschematic = schematic.netherData.schematic;
                        island.home = island.home.add(schematic.x, schematic.y, schematic.z);
                        break;
                    }
                    island.pasteSchematic(true);
                    if (IridiumSkyblock.getInstance().getConfiguration().restartUpgradesOnRegen) {
                        island.resetMissions();
                        island.setSizeLevel(1);
                        island.setMemberLevel(1);
                        island.setWarpLevel(1);
                        island.setOreLevel(1);
                    }
                    island.teleportPlayersHome();
                }, IridiumSkyblock.getInstance().getMessages().resetAction).getInventory());
            } else {
                p.openInventory(island.schematicSelectGUI.getInventory());
            }
        } else {
            sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
