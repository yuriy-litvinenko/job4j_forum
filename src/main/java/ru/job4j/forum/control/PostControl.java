package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/addPost")
    public String create(@ModelAttribute Post post) {
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/index";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postService.getById(id));
        return "post";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postService.getById(id));
        return "edit";
    }
}
