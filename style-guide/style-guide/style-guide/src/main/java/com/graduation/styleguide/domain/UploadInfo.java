package com.graduation.styleguide.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "clothes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private long idx;

    @JoinColumn(name = "id")
    @ManyToOne
    private UserInfo id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name="pic_name")
    private String pic_name;

    @Column(name = "product_price")
    private long product_price;

    @Column(name = "product_count")
    private int product_count;

    @Column(name ="product_intro")
    private String product_intro;

    @Builder
    public UploadInfo(UserInfo id, String product_name, String pic_name, long product_price, int product_count, String product_intro){
        this.id = id;
        this.product_name = product_name;
        this.pic_name = pic_name;
        this.product_price = product_price;
        this.product_count = product_count;
        this.product_intro = product_intro;
    }
}
