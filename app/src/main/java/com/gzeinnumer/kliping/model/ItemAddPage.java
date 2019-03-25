package com.gzeinnumer.kliping.model;

public class ItemAddPage {
    private int pageNo;
    private String pathFoto;

    public ItemAddPage(int pageNo) {
        this.pageNo = pageNo;
    }

    public ItemAddPage(int pageNo, String pathFoto) {
        this.pageNo = pageNo;
        this.pathFoto = pathFoto;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }
}
