package renderEngine.utils;

import renderEngine.models.ObjectLoader;

public class test {

    public static void main(String[] args) {
        ObjectLoader loader = new ObjectLoader();

        float[] a = loader.getTerrainTextureCoords(12, 5);

        for(int i=0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

}
