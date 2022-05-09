package fr.rosstail.nodewar.commands.subcommands.admincommands.adminempirecommands.adminempireeditcommands;

import fr.rosstail.nodewar.commands.SubCommand;
import fr.rosstail.nodewar.datahandlers.PlayerInfo;
import fr.rosstail.nodewar.datahandlers.PlayerInfoManager;
import fr.rosstail.nodewar.empires.Empire;
import fr.rosstail.nodewar.empires.EmpireManager;
import fr.rosstail.nodewar.lang.AdaptMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminEmpireEditColorCommand extends SubCommand {
    @Override
    public String getName() {
        return "color";
    }

    @Override
    public String getDescription() {
        return "Edit your empire display name";
    }

    @Override
    public String getSyntax() {
        return "nodewar admin empire edit color";
    }

    @Override
    public String getPermission() {
        return "nodewar.command.empire.edit.color";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage("You don't have permission " + getPermission());
            return;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("By player only");
            return;
        }
        Player player = Objects.requireNonNull(((Player) sender).getPlayer());
        PlayerInfo playerInfo = PlayerInfoManager.getPlayerInfoManager().getSet(player);
        Empire playerEmpire = playerInfo.getEmpire();

        if (args.length < 4) {
            player.sendMessage("Not enough arguments !");
            return;
        }

        StringBuilder display = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            if (i > 3) {
                display.append(" ");
            }
            display.append(args[i]);
        }
        playerEmpire.setDisplay(AdaptMessage.playerMessage(player, display.toString()));
        sender.sendMessage("Edited display successfully !");
        playerEmpire.saveConfigFile();
    }

    @Override
    public List<String> getSubCommandsArguments(Player sender, String[] args) {
        if (args.length == 5){
            return new ArrayList<>(EmpireManager.getEmpireManager().getEmpires().keySet());
        }
        return null;
    }
}
