package com.example.itemtracker;

public class ItemModel {

    private int id;
    private String itemName;
    private String itemId;
    private String itemModel;
    private String itemDesc;

    public ItemModel(int id, String itemName, String itemId, String itemModel, String itemDesc) {
        this.id = id;
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemModel = itemModel;
        this.itemDesc = itemDesc;
    }


    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemModel='" + itemModel + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemModel() {
        return itemModel;
    }

    public void setItemModel(String itemModel) {
        this.itemModel = itemModel;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
