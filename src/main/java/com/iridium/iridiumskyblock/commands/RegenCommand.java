package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.gui.ConfirmationGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

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
