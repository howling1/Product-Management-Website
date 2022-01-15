package org.example.pojo.vo;

public class ProductSearchVo {

    private String pname;

    private Integer typeid;

    // lowest price
    private Integer lprice;

    // highest price
    private Integer hprice;

    private Integer pageNum = 1;

    public ProductSearchVo(){

    }

    public ProductSearchVo(String pname, Integer typeid, Integer lprice, Integer hprice, Integer pageNum) {
        this.pname = pname;
        this.typeid = typeid;
        this.lprice = lprice;
        this.hprice = hprice;
        this.pageNum = pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getHprice() {
        return hprice;
    }

    public Integer getLprice() {
        return lprice;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public String getPname() {
        return pname;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    @Override
    public String toString() {
        return "ProductSearchVo{" +
                "pname='" + pname + '\'' +
                ", typeid=" + typeid +
                ", lprice=" + lprice +
                ", hprice=" + hprice +
                ", pageNum=" + pageNum +
                '}';
    }
}
