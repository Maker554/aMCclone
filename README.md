This is a 3D voxel engine inspired by Minecraft.

**Libraries**
- It has its own custom graphic engine built on open GL using lwjgl library for Java.
- joml to handle vectors and matrices.


**Generation**
- The data for the terrain follows a heighMap generated with Perlin Noise.
- After initial terrain trees are added.
- The terrain is divided into chunks to optimize memory access, each chunk stores data into an array of bytes (offering 256 different block types).
- All Chunks are stored into a List and loaded and unloaded based on the camera position.


**Rendering and Optimization**

Rending each block present is extremely inefficient, therefore only visible faces are rendered, moreover each face is not rendered individually: all faces of the blocks of a chunk are merged together into a single mesh rendered all at once.


**Player Interactions**

- The player is allowed to move and fly around the world, generating new terrain as he moves.
- He can open the inventory and select blocks from the menu placing them in the hotbar.
- Using numeric keys He can select blocks in hand.
- Using Mouse buttons the Player can place and break blocks.
- The player has collision with the blocks.


**Raycast - the Slab method**

1. To decide where to place or break blocks a line (vector definition: r = A + λB) is created following the camera orientations.
2. The program divide the space into "slabs" for each axis, the space between two round coordinates (edges of the voxel grid).
3. All blocks at the intersection of the slabs intersecting the line for every scalar under the reach limit is coliding with the raycast.
4. The smaller scalar (closest block) is selected and the block is selected as pointed block.


**Window and Mouse**

- Using Alt or by opening inventory the mouse is free, else it is kept hidden and stuck at the center of the screen.
- The window can by maximized and is resizable.
- No ticking system has been implemented yet, the speed of the program is dependent on the fps that are currently being kept constant using V-sync.
