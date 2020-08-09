package com.url.shortcutter.controller;

import com.url.shortcutter.interfaces.LinkRepository;
import com.url.shortcutter.model.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LinkController {

    private final LinkRepository repository;

    public LinkController(LinkRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/link")
    public String createShortCutterLink(@ModelAttribute Link link, Model model,
                                        @RequestHeader(name = "Host", required = false) final String host) {
        Link existLink = repository.findLinkByOriginalLink(link.getOriginalLink());
        if(existLink == null){
            repository.save(link);
        }

        model.addAttribute("short", String.format("%s/%s", host,
                existLink == null ? repository.findLinkByOriginalLink(link.getOriginalLink()).getId() : existLink.getId()));
        model.addAttribute("newLink", new Link());

        return "index";
    }

    @GetMapping("/{id}")
    public String redirectLink(@PathVariable("id") String id) {
        Link link = repository.findLinkById(id);

        return String.format("redirect:%s", link.getOriginalLink());
    }

}
