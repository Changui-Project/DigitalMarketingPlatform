package com.example.sales_post.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;



@Entity
@Table(name = "SalesPost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesPostEntity { // 제품정보 엔티티 extends해야함

    // salesPost: 게시글
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postNumber;

    private String category;
    private String postTitle;
    private String postAuthor;
    private String postDate;
    private String postContents;
    private String postPicture;
    private long postHitCount;
    private long postLike;
    private String storeLocation;
}