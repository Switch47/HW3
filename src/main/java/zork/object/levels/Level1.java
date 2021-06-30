package zork.object.levels;


import zork.Game;
import zork.object.Player;
import zork.object.Room;
import zork.object.item.Item;
import zork.object.item.ItemFactory;
import zork.object.monster.Monster;
import zork.object.monster.MonsterFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class Level1 extends Level {



    public Level1() {
    }



    public void loadMap(String filename) {
        try {
            roomLevel = new HashMap<>();
            monsterNum = 0;
            Path filePath = new File(filename).toPath();
            Charset charset = Charset.defaultCharset();
            List<String> stringList = Files.readAllLines(filePath, charset);
            String[] stringArray = stringList.toArray(new String[]{});
            ItemFactory itemFactory = new ItemFactory();
            MonsterFactory monsterFactory = new MonsterFactory();
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i].split(" ")[0].equals("start")) {
                    Room x = new Room();
                    x.name = stringArray[i].split(" ")[1];
                    startRoom = x;
                    roomLevel.put(stringArray[i].split(" ")[1],x);
                }
                else if (stringArray[i].split(":")[0].equals("name")) {
                    name = stringArray[i].split(":")[1];
                }
                else if (stringArray[i].split(" ")[0].equals("object")) {
                    object = stringArray[i].split(" ")[1];
                    if (object.equals("weapon")) {
                        objective = "pick up a weapon" + "\n" + "You can choose only one weapon.";
                    }
                    else if (object.equals("monster")) {
                        objective = "kill every monster that exists in this map.";
                    }
                    else {
                        System.out.println("Your map has some problems");
                    }
                }
                else if (stringArray[i].split(" ")[0].equals("right")) {
                    String nameX = stringArray[i].split(" ")[1];
                    String nameY = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(nameX)) {
                        Room x = new Room();
                        x.name = nameX;
                        roomLevel.put(nameX,x);
                    }
                    if (!roomLevel.containsKey(nameY)) {
                        Room y = new Room();
                        y.name = nameY;
                        roomLevel.put(nameY,y);
                    }
                    roomLevel.get(nameX).setRightRoom(roomLevel.get(nameY));
                }
                else if (stringArray[i].split(" ")[0].equals("left")) {
                    String nameX = stringArray[i].split(" ")[1];
                    String nameY = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(nameX)) {
                        Room x = new Room();
                        x.name = nameX;
                        roomLevel.put(nameX,x);
                    }
                    if (!roomLevel.containsKey(nameY)) {
                        Room y = new Room();
                        y.name = nameY;
                        roomLevel.put(nameY,y);
                    }
                    roomLevel.get(nameX).setLeftRoom(roomLevel.get(nameY));
                }
                else if (stringArray[i].split(" ")[0].equals("up")) {
                    String nameX = stringArray[i].split(" ")[1];
                    String nameY = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(nameX)) {
                        Room x = new Room();
                        x.name = nameX;
                        roomLevel.put(nameX,x);
                    }
                    if (!roomLevel.containsKey(nameY)) {
                        Room y = new Room();
                        y.name = nameY;
                        roomLevel.put(nameY,y);
                    }
                    roomLevel.get(nameX).setUpRoom(roomLevel.get(nameY));
                }
                else if (stringArray[i].split(" ")[0].equals("down")) {
                    String nameX = stringArray[i].split(" ")[1];
                    String nameY = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(nameX)) {
                        Room x = new Room();
                        x.name = nameX;
                        roomLevel.put(nameX,x);
                    }
                    if (!roomLevel.containsKey(nameY)) {
                        Room y = new Room();
                        y.name = nameY;
                        roomLevel.put(nameY,y);
                    }
                    roomLevel.get(nameX).setDownRoom(roomLevel.get(nameY));
                }
                else if (stringArray[i].split(":")[0].equals("setItem")) {
                    String room = stringArray[i].split(":")[1];
                    for (int j = 2; j < stringArray[i].split(":").length; j++) {
                        String itemName = stringArray[i].split(":")[j];
                        Item item = null;
                        if (itemName.equals("long sword")){
                            item = itemFactory.createLongSword();
                        }
                        else if (itemName.equals("gun")) {
                            item = itemFactory.createGun();
                        }
                        else if (itemName.equals("sword")) {
                            item = itemFactory.createSword();
                        }
                        else if (itemName.equals("knife")) {
                            item = itemFactory.createKnife();
                        }
                        else if (itemName.equals("potion")) {
                            item = itemFactory.createPotion();
                        }
                        else if (itemName.equals("super potion")) {
                            item = itemFactory.createSuperPotion();
                        }
                        roomLevel.get(room).setItems(item);
                    }
                }
                else if (stringArray[i].split(":")[0].equals("setMonster")) {
                    String room  = stringArray[i].split(":")[1];
                    for (int j = 2; j < stringArray[i].split(":").length; j++) {
                        String monsterName = stringArray[i].split(":")[j];
                        Monster monster = null;
                        if (monsterName.equals("zombie")) {
                            monster = monsterFactory.makeZombie();
                        }
                        if (monster != null) {
                            roomLevel.get(room).setMonster(monster);
                            monsterNum += 1;
                        }
                    }
                }
                else if (stringArray[i].split(":")[0].equals("setPlayer")) {
                    String x = stringArray[i];
                    Game.player.HP = Integer.valueOf(x.split(":")[1]);
                    Game.player.MAX_EXP = Integer.valueOf(x.split(":")[2]);
                    Game.player.strength = Integer.valueOf(x.split(":")[3]);
                    Item item = null;
                    if (x.split(":")[4].equals("gun")) {
                        item = itemFactory.createGun();
                        Game.player.weapon = item;
                    }
                    else if (x.split(":")[4].equals("long sword")) {
                        item = itemFactory.createLongSword();
                        Game.player.weapon = item;
                    }
                    else if (x.split(":")[4].equals("sword")) {
                        item = itemFactory.createSword();
                        Game.player.weapon = item;
                    }
                    else if (x.split(":")[4].equals("knife")) {
                        item = itemFactory.createKnife();
                        Game.player.weapon = item;
                    }
                    Game.player.agility = Integer.valueOf(x.split(":")[5]);
                    Game.player.exp = Integer.valueOf(x.split(":")[6]);
                    Game.player.level = Integer.valueOf(x.split(":")[7]);
                    Game.player.MAX_EXP = Integer.valueOf(x.split(":")[8]);
                }
                else if (stringArray[i].split(":")[0].equals("setPlayerItems")) {
                    for (int j = 1; j < stringArray[i].split(":").length-1; i++) {
                        Item item = null;
                        if (stringArray[i].split(":")[j].equals("potion")) {
                            item = itemFactory.createPotion();
                            Game.player.items.add(item);
                        }
                        else if (stringArray[i].split(":")[j].equals("super potion")) {
                            item = itemFactory.createSuperPotion();
                            Game.player.items.add(item);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean objectiveCompleted() {
        if (object.equals("weapon")) {
            if (Game.player.weapon != null) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (object.equals("monster")) {
            if (Game.currentLevel.monsterNum <= 0) {
                return true;
            }
            else {
                return  false;
            }
        }
        return false;
    }

}

