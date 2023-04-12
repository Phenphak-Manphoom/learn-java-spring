package com.example.demo.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.backend.entity.Social;
import com.example.demo.backend.entity.User;
import com.example.demo.backend.repository.SocialRepository;

@Service
public class SocialService {
    private final SocialRepository socialRepository;

    public SocialService(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    public Optional<Social> findByUser(User user){
       return socialRepository.findByUser(user);
    }

    public Social createSocial(User user,String facebook,String line,String instagram){
        Social social= new Social();
        social.setUser(user);
        social.setFacebook(facebook);
        social.setLine(line);
        social.setInstagram(instagram);

        return socialRepository.save(social);
    }
}
