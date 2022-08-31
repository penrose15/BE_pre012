package com.codestates.pre012.member.entity;


import com.codestates.pre012.baseEntity.BaseEntity;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.reply.Reply;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String roles; // User, MANAGER, ADMIN


    private String provider;
    private String providerId;

    @Builder // 추가
    public Member(String username, String password, String email, String roles, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.provider = provider;
        this.providerId = providerId;
    }



    @OneToMany(mappedBy = "member")
    private List<Posts> posts;

    @OneToMany(mappedBy = "posts",fetch = FetchType.EAGER)
    private List<Reply> reply;

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }


}