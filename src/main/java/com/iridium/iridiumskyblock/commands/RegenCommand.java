package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.gui.ConfirmationGUI;
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
                        p.openInventory(new ConfirmationGUI(user.getIsland(), () -> {
                            Island island = user.getIsland();
                            island.generateIsland();
                            if (IridiumSkyblock.getConfiguration().restartUpgradesOnRegen) {
                                island.resetMissions();
                                island.setSizeLevel(1);
                                island.setMemberLevel(1);
                                island.setWarpLevel(1);
                                island.setOreLevel(1);
                                island.setFlightBooster(0);
                                island.setExpBooster(0);
                                island.setFarmingBooster(0);
                                island.setSpawnerBooster(0);
                            }
                        }, IridiumSkyblock.getMessages().resetAction).getInventory());
                    } else {
                        int day = (int) TimeUnit.SECONDS.toDays(time);
                        int hours = (int) Math.floor(TimeUnit.SECONDS.toHours(time - day * 86400));
                        int minute = (int) Math.floor((time - day * 86400 - hours * 3600) / 60.00);
                        int second = (int) Math.floor((time - day * 86400 - hours * 3600) % 60.00);
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().regenCooldown.replace("%days%", day + "").replace("%hours%", hours + "").replace("%minutes%", minute + "").replace("%seconds%", second + "").replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().mustBeIslandOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } else {
            sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
