package net.maker554.aMCclone.terrain;

public class MeshData {
    public float[] vertices;
    public float[] textureCoords;
    public int[] indices;
    public float[] verticesT;
    public float[] textureCoordsT;
    public int[] indicesT;

    public MeshData(float[] vertices, float[] textureCoords, int[] indices, float[] verticesT, float[] textureCoordsT, int[] indicesT) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.indices = indices;
        this.verticesT = verticesT;
        this.textureCoordsT = textureCoordsT;
        this.indicesT = indicesT;
    }
}
