package lab.webpost.services;

import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lab.webpost.domain.Post;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    // TODO: get all Posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> retrieveConcert(@PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/post/title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) {
        List<Post> posts = postRepository.findByTitle(title);

        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
        return ResponseEntity.ok(posts);
    }

    // TODO: adding new post
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created success!");
    }

    // TODO: delete post by id
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        Optional<Post> posOptional = postRepository.findById(id);

        if (posOptional.isPresent()) {
            postRepository.deleteById(id);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }
    }

    // TODO: delete all posts
    @DeleteMapping("/posts")
    public ResponseEntity<String> deleteAllPosts() {
        postRepository.deleteAll();
        return new ResponseEntity<>("All posts deleted successfully", HttpStatus.NO_CONTENT);
    }

}
