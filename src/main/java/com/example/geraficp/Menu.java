package com.example.geraficp;

public class Menu {
    private String[] menuList;
    public Menu(){

    }

    public Menu(String[] items) {
        this.menuList = items;
    }

    public String[] getMenuList() {
        return menuList;
    }
}

