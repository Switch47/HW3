package zork.object.levels;


import zork.Game;
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
import java.util.Map;

public class Level1 extends Level {

    public String object;
    public Map<String,Room> roomLevel;

    public Level1() {
        loadMap("C:\\Users\\USER\\IdeaProjects\\Software System\\hw3\\src\\main\\java\\zork\\object\\levels\\Map2.txt");
    }

    public void loadMap(String filename) {
        try {
            roomLevel = new HashMap<>();
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
                else if (stringArray[i].split(" ")[0].equals("name")) {
                    name = stringArray[i].split(" ")[1];
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
                    String namex = stringArray[i].split(" ")[1];
                    String namey = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(namex)) {
                        Room x = new Room();
                        x.name = namex;
                        roomLevel.put(namex,x);
                    }
                    if (!roomLevel.containsKey(namey)) {
                        Room y = new Room();
                        y.name = namey;
                        roomLevel.put(namey,y);
                    }
                    roomLevel.get(namex).setRightRoom(roomLevel.get(namey));
                }
                else if (stringArray[i].split(" ")[0].equals("left")) {
                    String namex = stringArray[i].split(" ")[1];
                    String namey = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(namex)) {
                        Room x = new Room();
                        x.name = namex;
                        roomLevel.put(namex,x);
                    }
                    if (!roomLevel.containsKey(namey)) {
                        Room y = new Room();
                        y.name = namey;
                        roomLevel.put(namey,y);
                    }
                    roomLevel.get(namex).setLeftRoom(roomLevel.get(namey));
                }
                else if (stringArray[i].split(" ")[0].equals("up")) {
                    String namex = stringArray[i].split(" ")[1];
                    String namey = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(namex)) {
                        Room x = new Room();
                        x.name = namex;
                        roomLevel.put(namex,x);
                    }
                    if (!roomLevel.containsKey(namey)) {
                        Room y = new Room();
                        y.name = namey;
                        roomLevel.put(namey,y);
                    }
                    roomLevel.get(namex).setUpRoom(roomLevel.get(namey));
                }
                else if (stringArray[i].split(" ")[0].equals("down")) {
                    String namex = stringArray[i].split(" ")[1];
                    String namey = stringArray[i].split(" ")[2];
                    if (!roomLevel.containsKey(namex)) {
                        Room x = new Room();
                        x.name = namex;
                        roomLevel.put(namex,x);
                    }
                    if (!roomLevel.containsKey(namey)) {
                        Room y = new Room();
                        y.name = namey;
                        roomLevel.put(namey,y);
                    }
                    roomLevel.get(namex).setDownRoom(roomLevel.get(namey));
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
