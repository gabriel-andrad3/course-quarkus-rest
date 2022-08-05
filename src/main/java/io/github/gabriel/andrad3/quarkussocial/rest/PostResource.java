package io.github.gabriel.andrad3.quarkussocial.rest;

import io.github.gabriel.andrad3.quarkussocial.domain.model.Post;
import io.github.gabriel.andrad3.quarkussocial.domain.model.User;
import io.github.gabriel.andrad3.quarkussocial.domain.repository.PostRepository;
import io.github.gabriel.andrad3.quarkussocial.domain.repository.UserRepository;
import io.github.gabriel.andrad3.quarkussocial.rest.dto.CreatePostRequest;
import io.github.gabriel.andrad3.quarkussocial.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private UserRepository userRepository;
    private PostRepository postRepository;


    @Inject
    public PostResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest postRequest) {
        User user = userRepository.findById(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(user);

        postRepository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPost(@PathParam("userId") Long userId) {
        User user = userRepository.findById(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PanacheQuery<Post> query = postRepository.find("user", Sort.by("datetime", Sort.Direction.Descending), user);
        List<Post> list = query.list();

        List<PostResponse> postReponse = list.stream()
//                .map(post -> PostResponse.fromEntity(post))
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(postReponse).build();
    }
}
