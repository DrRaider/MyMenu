package com.github.drraider.mymenu.menu;

public class Dish {

    private String name;
    private String description;
    private String allergenes;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = eraseJSonCharacter(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = eraseJSonCharacter(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = eraseJSonCharacter(description);
    }

    public String getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(String allergenes) {
        this.allergenes = eraseJSonCharacter(allergenes);
    }

    public String eraseJSonCharacter (String str) {
        str = str.replace("\"", "");
        str = str.replace("[", "");
        str = str.replace("]", "");

        return str;
    }

}
