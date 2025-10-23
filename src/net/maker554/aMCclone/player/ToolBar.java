package net.maker554.aMCclone.player;

public class ToolBar {

    private int current_itemID;
    private int current_slot;
    int[] itemsInToolBarIDs;

    public ToolBar() {
        this.itemsInToolBarIDs = new int[] {1, 2, 3, 4, 5, 6, 7, 10, 11};
        this.current_slot = 0;
        this.current_itemID = getId(current_slot);
    }

    public void changeSlot(int slot) {
        current_slot = slot;
        current_itemID = itemsInToolBarIDs[slot];
    }

    public int getId() {
        return current_itemID;
    }

    public int getSlot() {
        return current_slot;
    }

    public int getId(int slot) {
        return itemsInToolBarIDs[slot];
    }
}
