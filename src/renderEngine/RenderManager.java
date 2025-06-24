package renderEngine;

import net.maker554.aMCclone.Client;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import renderEngine.models.Entity;
import renderEngine.models.Model;
import renderEngine.utils.Transformation;
import renderEngine.utils.Utils;

public class RenderManager {

    private final WindowManager window;
    private ShaderManager shaderManager;

    public RenderManager() {
        window = Client.getWindow();
    }

    public void init() throws Exception {

        //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

        shaderManager = new ShaderManager();
        shaderManager.createVertexShader(Utils.loadResource("resources/shaders/vertex.vert"));
        shaderManager.createFragmentShader(Utils.loadResource("resources/shaders/fragment.frag"));
        shaderManager.link();

        shaderManager.createUniform("textureSampler");
        shaderManager.createUniform("transformationMatrix");
        shaderManager.createUniform("projectionMatrix");
        shaderManager.createUniform("viewMatrix");

    }

    public void render(Entity entity, Camera camera) {
        // render a model onto screen
        //prep
        shaderManager.bind();

        shaderManager.setUniform("textureSampler", 0);
        shaderManager.setUniform("transformationMatrix", Transformation.createTransformationMatrix(entity));
        shaderManager.setUniform("projectionMatrix", window.updateProjectMatrix());
        shaderManager.setUniform("viewMatrix", Transformation.getViewMatrix(camera));

        // model
        GL30.glBindVertexArray(entity.getModel().getId());
        GL20.glEnableVertexAttribArray(0);

        // texture
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTexture().getId());

        // drawing
        GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        //cleaning
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shaderManager.unbind();
    }

    public void render(Entity entity, Camera camera, float pov) {
        // render a model onto screen
        //prep
        shaderManager.bind();

        shaderManager.setUniform("textureSampler", 0);
        shaderManager.setUniform("transformationMatrix", Transformation.createTransformationMatrix(entity));
        shaderManager.setUniform("projectionMatrix", window.updateProjectMatrix(pov));
        shaderManager.setUniform("viewMatrix", Transformation.getViewMatrix(camera));

        // model
        GL30.glBindVertexArray(entity.getModel().getId());
        GL20.glEnableVertexAttribArray(0);

        // texture
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTexture().getId());

        // drawing
        GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        //cleaning
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shaderManager.unbind();
    }



    public void renderGui(Entity entity) {
        render(entity, new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0)), (float) Math.toRadians(60));
    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanUp() {
        shaderManager.cleanUp();
    }
}
