#version 400 core

in vec3 position;
in vec2 textureCord;

out vec2 fragTextureCord;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main() {
    gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
    fragTextureCord = textureCord;
}