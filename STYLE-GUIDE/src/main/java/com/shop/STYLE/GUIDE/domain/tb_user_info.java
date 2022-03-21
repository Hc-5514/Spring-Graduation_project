package com.shop.STYLE.GUIDE.domain;

import lombok.Data;

import javax.persistence.*;

@Data   // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode
@Entity
public class tb_user_info {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer USER_No;

    @Id
    private String USER_Id;

    @Column
    private String USER_Pw;

    @Column
    private String USER_Name;

//    @Column
//    private String User_AppendDate;
}

