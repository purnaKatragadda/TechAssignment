package com.evaluation.choicesuper.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest extends JerseyTest {
	
	@Override
    protected Application configure() {
		
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	    context.refresh();
	    return new ResourceConfig(MemberService.class)
	    	    .property("contextConfig", context);
        
    }
	
	@Test
	public void testHello() {
	    Response response = target("/").request()
	        .get();
	 
	    assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	   	
	     response = target("/").request()
		        .post(null);
		 
		    assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		
		    response = target("/memberupload").request()
			        .get();
			 
			    assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
			
			  response = target("/memberupload").request()
				        .post(null);
				 
				    assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
				
	}

}
