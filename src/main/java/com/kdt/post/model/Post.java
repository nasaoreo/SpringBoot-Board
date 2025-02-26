package com.kdt.post.model;

import com.kdt.common.model.BaseEntity;
import com.kdt.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void setUser(User user){
        if(Objects.nonNull(this.user)){
            user.getPosts().remove(this);
        }

        this.user = user;
        user.getPosts().add(this);
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;

        super.update();
    }
}
