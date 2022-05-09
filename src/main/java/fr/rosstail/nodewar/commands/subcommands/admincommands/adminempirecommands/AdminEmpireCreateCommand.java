package fr.rosstail.nodewar.commands.subcommands.admincommands.adminempirecommands;

import fr.rosstail.nodewar.commands.SubCommand;
import fr.rosstail.nodewar.empires.Empire;
import fr.rosstail.nodewar.empires.EmpireManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminEmpireCreateCommand extends SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create an empire";
    }

    @Override
    public String getSyntax() {
        return "nodewar admin empire create";
    }

    @Override
    public String getPermission() {
        return "nodewar.command.admin.empire.create";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage("You don't have permission " + getPermission());
            return;
        }

        if (args.length < 4) {
            sender.sendMessage("Not enough arguments !");
            return;
        }

        EmpireManager empireManager = EmpireManager.getEmpireManager();
        String empireID = args[3];
        if (empireManager.getEmpires().containsKey(empireID)) {
            sender.sendMessage("Empire already existing");
        } else {
            Empire empire = empireManager.getSet((Player) null, empireID);
            sender.sendMessage("Created " + empire.getDisplay() + " empire");
        }
    }

    @Override
    public List<String> getSubCommandsArguments(Player sender, String[] args) {
        return null;
    }

}
