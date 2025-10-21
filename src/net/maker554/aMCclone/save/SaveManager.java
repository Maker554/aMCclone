package net.maker554.aMCclone.save;

import net.maker554.aMCclone.player.Player;
import net.maker554.aMCclone.terrain.Chunk;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    public static void saveWorld(List<Chunk> chunkList) {

        List<byte[]> chunkDataList = new ArrayList<>();
        List<int[]> chunkIndexList = new ArrayList<>();

        for (Chunk chunk : chunkList) {
            chunkDataList.add(chunk.getData());
            chunkIndexList.add(new int[]{chunk.getX(), chunk.getZ()});
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("level.dat"))) {
            out.writeObject(chunkDataList);
        } catch (IOException e) {e.printStackTrace();}

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("map.dat"))) {
            out.writeObject(chunkIndexList);
        } catch (IOException e) {e.printStackTrace();}
    }

    public static List<Chunk> loadWorld() {

        List<Chunk> chunkList = new ArrayList<>();
        List<byte[]>chunkDataList = new ArrayList<>();
        List<int[]>chunkIndexList = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("level.dat"))) {
            chunkDataList = (List<byte[]>) in.readObject();
        } catch (IOException | ClassNotFoundException _) {}

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("map.dat"))) {
            chunkIndexList = (List<int[]>) in.readObject();
        } catch (IOException | ClassNotFoundException _) {}

        if (!chunkDataList.isEmpty() && chunkIndexList.size() == chunkDataList.size()) {
            for (int i = 0; i < chunkDataList.size(); i++) {
                int[] position = chunkIndexList.get(i);
                chunkList.add(new Chunk(position[0], position[1], chunkDataList.get(i)));
            }
            return chunkList;
        }
        return null;
    }

    public static void savePlayer(Player player) {

        List<Vector3f> pack = new ArrayList<>();
        pack.add(player.getPosition());
        pack.add(player.getCamera().getRotation());

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
            out.writeObject(pack);
        } catch (IOException e) {e.printStackTrace();}
    }

    public static Player loadPlayer() {

        Player player = new Player();
        List<Vector3f> pack = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.dat"))) {
            pack = (List<Vector3f>) in.readObject();
        } catch (IOException | ClassNotFoundException _) {}

        if (!pack.isEmpty()) {
            player.setPosition(pack.getFirst());
            player.getCamera().setRotation(pack.get(1));
        }
        return player;
    }
}
