package io.github.gabriel.andrad3.quarkussocial.rest.dto;

import io.github.gabriel.andrad3.quarkussocial.domain.model.Follower;
import lombok.Data;

import java.util.List;

@Data
public class FollowerResponse {
    private Long id;
    private String name;

    public FollowerResponse() {
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FollowerResponse(Follower follower) {
        this(follower.getId(), follower.getFollower().getName());
    }
}
