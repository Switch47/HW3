package zork.commands;

import zork.Game;
import zork.object.item.Item;

import java.util.ArrayList;
import java.util.List;

public class InfoCommand implements Command{

    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        System.out.println("Now you are in " + game.currentRoom.name);
        System.out.println("------------------------------------------");
        System.out.println("               Player stats               ");
        System.out.println("HP : " + game.player.HP + " / " + game.player.MAX_HP);
        System.out.println("LEVEL : " + game.player.level);
        System.out.println("EXP : " + game.player.exp + " / " + game.player.MAX_EXP);
        if (game.player.weapon != null) {
            System.out.println("WEAPON : " + game.player.weapon.name);
            System.out.println("STRENGTH : " + (game.player.strength + game.player.weapon.strength));
            System.out.println("AGILITY : " + (game.player.agility + game.player.weapon.agility));
        }
        else {
            System.out.println("WEAPON : Empty" );
            System.out.println("STRENGTH : " + game.player.strength);
            System.out.println("AGILITY : " + game.player.agility);
        }
        if (game.player.items.size() <= 0) {
            System.out.println(" || INVENTORY || -> [ Empty ]");
        }
        else {
            String inventory = " || INVENTORY || -> [ " + game.player.items.get(0).name;
            for (int i = 1; i < game.player.items.size(); i++) {
                inventory += " , " + game.player.items.get(i).name;
            }
            System.out.println(inventory + " ]");
        }
        System.out.println("------------------------------------------");
        List<String> doorExist = new ArrayList<>();
        if (game.currentRoom.rightRoom != null) {
            doorExist.add("east");
        }
        if (game.currentRoom.leftRoom != null) {
            doorExist.add("west");
        }
        if (game.currentRoom.upRoom != null) {
            doorExist.add("north");
        }
        if (game.currentRoom.downRoom != null) {
            doorExist.add("south");
        }
        String result = "Existing Door : [ " + doorExist.get(0);
        for (int i = 1; i < doorExist.size(); i++) {
            result = result + " , " + doorExist.get(i);
        }
        System.out.println(result + " ]");
    }
}
