package model;

/**
 * Created by Zhukov on 13.11.2016.
 */
public enum TypePacking {
    PACKING("Пакет"), COMMISSION("Коммиссионная отгрузка"),PLACER("Россыпь");

    private   String nameTypePacking;
    private TypePacking(String nameTypePacking){
        this.nameTypePacking = nameTypePacking;
    }

    public  String getNameTypePacking(){
        return this.nameTypePacking;
    }
}
