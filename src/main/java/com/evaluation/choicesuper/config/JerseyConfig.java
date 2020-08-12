package com.evaluation.choicesuper.config;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.evaluation.choicesuper.service.MemberService;

@Configuration
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig() {
        
        register(MemberService.class);
        register(MultiPartFeature.class);
     
    }
}