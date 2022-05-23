package com.graduation.styleguide.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "clothes")
@Getter
@NoArgsConstructor
public class UploadInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private long idx;

    @JoinColumn(name="id")
    @ManyToOne
    private UserInfo id;

    @Column(name = "product_name")
    private String product_name;
    @Column(name = "pic_name")
    private String pic_name;
    @Column(name = "product_price")
    private long product_price;
    @Column(name = "product_count")
    private int product_count;
    @Column(name = "product_intro")
    private String product_intro;

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public UserInfo getId() {
        return id;
    }

    public void setId(UserInfo id) {
        this.id = id;
    }

    public String getproduct_name() {
        return product_name;
    }

    public void setproduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public long getproduct_price() {
        return product_price;
    }

    public void setproduct_price(long product_price) {
        this.product_price = product_price;
    }

    public int getproduct_count() {
        return product_count;
    }

    public void setproduct_count(int product_count) {
        this.product_count = product_count;
    }

    public String getproduct_intro() {
        return product_intro;
    }

    public void setproduct_intro(String product_intro) {
        this.product_intro = product_intro;
    }

    @Builder
    public UploadInfo(UserInfo id, String product_name, Long product_price, int product_count, String product_intro, String pic_name){
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_count = product_count;
        this.product_intro = product_intro;
        this.pic_name = pic_name;
    }

}
