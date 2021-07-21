package zork.commands;

import zork.Game;
import zork.object.item.Item;
import zork.object.item.weapons.Weapon;

import java.util.List;
import java.util.Scanner;

public class TakeCommand implements Command{


    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "take";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (game.getGameStatus() == game.PLAY_STATUS) {
            Scanner in = new Scanner(System.in);
            String item;
            System.out.println("Which items you want to pick??");
            if (game.currentRoom.items.size() > 0) {
                String x = "Items in this room : [ " + game.currentRoom.items.get(0).name;
                for (int i = 1; i < game.currentRoom.items.size(); i++) {
                    x += " , " + game.currentRoom.items.get(i).name;
                }
                System.out.println(x + " ]");
                item = in.nextLine();
                int check = 0;
                for (Item it : game.currentRoom.items) {
                    if (it.name.equals(item)) {
                        check = check + 1;

                        if (it instanceof Weapon) {
                            if (game.player.weapon == null) {
                                game.player.pickWeapon(it);
                                System.out.println("you receive [ " + it.name + " ].");
                                game.currentRoom.items.remove(it);
                                break;
                            } else {
                                System.out.println("You must drop current weapon first.");
                                break;
                            }
                        } else {
                            game.player.takeItems(it);
                            System.out.println("you receive [ " + it.name + " ].");
                            game.currentRoom.items.remove(it);
                            break;
                        }
                    }
                }
                if (check == 0) {
                    System.out.println("There is no [ " + item + " ] in this room.");
                }
            }
            else {
                System.out.println("There is no any item in this room.");
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }
}
