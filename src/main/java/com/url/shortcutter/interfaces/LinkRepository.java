package com.url.shortcutter.interfaces;

import com.url.shortcutter.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {
    Link findLinkByOriginalLink(String link);

    Link findLinkById(String id);
}
