package net.maker554.aMCclone.collision;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.assets.Square;
import net.maker554.aMCclone.terrain.Chunk;
import net.maker554.aMCclone.terrain.ChunkManager;
import net.maker554.aMCclone.utils.ArrayManager;
import net.maker554.aMCclone.utils.DirectionEnum;
import net.maker554.aMCclone.utils.VectorMath;
import net.maker554.aMCclone.utils.shapes.Plane;
import org.joml.Vector2i;
import org.joml.Vector3f;
import renderEngine.models.Entity;
import renderEngine.models.Model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class TerrainCollisionMap {

    private static final List<Plane> planesList = new ArrayList<>();
    private static final List<Entity> debugPlanesList = new ArrayList<>();


    public static void calculateMap(Vector3f position) {

        debugPlanesList.clear();

        for(int i = -Settings.REACH_RANGE; i < Settings.REACH_RANGE; i++) {
            for(int j = -Settings.REACH_RANGE; j < Settings.REACH_RANGE; j++) {
                for(int k = -Settings.REACH_RANGE; k < Settings.REACH_RANGE; k++) {
                    if(i*i+j*j+k*k < Settings.REACH_RANGE*Settings.REACH_RANGE) {

                        int curX = round(position.x) + i;
                        int curY = round(position.y) + j;
                        int curZ = round(position.z) + k;

                        Vector2i chunkCords = new Vector2i((int) Math.floor((double) curX / Settings.CHUNK_SIZE), (int) Math.floor((double) curZ / Settings.CHUNK_SIZE));

                        Chunk chunk = ChunkManager.getChunk(chunkCords.x, chunkCords.y);
                        Vector2i chunkPos = chunk.getChunkCordsFromGlobalCords(curX, curZ);
                        // if the block is NOT air
                        if(chunk.getBlock(chunkPos.x, curY, chunkPos.y) != 0) {

                            // if near faces are air
                            if(ArrayManager.getNearFace(chunk, chunkPos.x, curY, chunkPos.y, DirectionEnum.UP) == 0) {

                                Vector3f planePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.UP).mul(0.5f));
                                planesList.add(new Plane(planePos, DirectionEnum.DOWN));

                                //debug
                                Vector3f debugPlanePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.UP).mul(0.55f));
                                Square square = new Square(1, 1);
                                Model model = square.getModel();
                                debugPlanesList.add(new Entity(model, debugPlanePos, new Vector3f(90, 90, 0), 1f));
                            }

                            if(ArrayManager.getNearFace(chunk, chunkPos.x, curY, chunkPos.y, DirectionEnum.DOWN) == 0) {

                                Vector3f planePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.DOWN).mul(0.5f));
                                planesList.add(new Plane(planePos, DirectionEnum.UP));

                                //debug
                                Vector3f debugPlanePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.DOWN).mul(0.55f));
                                Square square = new Square(1, 1);
                                Model model = square.getModel();
                                debugPlanesList.add(new Entity(model, debugPlanePos, new Vector3f(90, 90, 0), 1f));
                            }

                            if(ArrayManager.getNearFace(chunk, chunkPos.x, curY, chunkPos.y, DirectionEnum.WEST) == 0) {

                                Vector3f planePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.WEST).mul(0.5f));
                                planesList.add(new Plane(planePos, DirectionEnum.EST));

                                //debug
                                Vector3f debugPlanePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.WEST).mul(0.55f));
                                Square square = new Square(1, 1);
                                Model model = square.getModel();
                                debugPlanesList.add(new Entity(model, debugPlanePos, new Vector3f(180, 90, 0), 1f));
                            }

                            if(ArrayManager.getNearFace(chunk, chunkPos.x, curY, chunkPos.y, DirectionEnum.EST) == 0) {

                                Vector3f planePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.EST).mul(0.5f));
                                planesList.add(new Plane(planePos, DirectionEnum.WEST));

                                //debug
                                Vector3f debugPlanePos = new Vector3f(curX, curY, curZ).add(VectorMath.getNormalf(DirectionEnum.EST).mul(0.55f));
                                Square square = new Square(1, 1);
                                Model model = square.getModel();
                                debugPlanesList.add(new Entity(model, debugPlanePos, new Vector3f(180, 90, 0), 1f));
                            }
                        }
                    }
                }
            }
        }
    }

    public static List<Entity> getDebugPlanesList() {
        return debugPlanesList;
    }

    public static List<Plane> getPlanesList() {
        return planesList;
    }
}
