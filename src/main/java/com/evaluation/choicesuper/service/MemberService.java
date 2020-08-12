package com.evaluation.choicesuper.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.evaluation.choicesuper.beans.MemberRegistrationTransactions;
import com.evaluation.choicesuper.logic.CSVTransformer;
import com.evaluation.choicesuper.logic.JsonProcess;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;




/**
 * REST Service to get the POST  Request 
 *
 */
@Service
@Path("/")
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Path("/")
    @GET
    @Produces("text/plain")
    public Response  index() {
        return Response
        	      .status(Response.Status.BAD_REQUEST)
        	      .entity("Hello from Upload,please use POST with CSV or XML File")
        	      .build();
    }
	
	
	
	@Path("/memberupload")
    @GET
    @Produces("text/plain")
    public Response memberUploadGET() {
      
  
        return Response
      	      .status(Response.Status.BAD_REQUEST)
      	      .entity("Hello from Upload,please use POST with CSV or XML File")
      	      .build();
    }
	
	@Path("/memberupload")
    @POST
    @Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces("text/plain")
    public Response memberUpload() {	
		 return Response
	      	      .status(Response.Status.BAD_REQUEST)
	      	      .entity("Hello from Upload,please use  CSV or XML File in POST,This seems like a empty POST and send with file param ")
	      	      .build();
	}
	
	/**
	 * @param fiStream
	 * @param fdcd
	 * @return
	 * @throws Exception
	 */
	@Path("/memberupload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/plain")
    public Response memberUploadFile(@FormDataParam("file") InputStream fiStream,
            @FormDataParam("file") FormDataContentDisposition fdcd) throws Exception {
    
		String strStatus=null;
		String strContent=null;
		MemberRegistrationTransactions memTransactionsObj=null;
		
		try {
			//Check Request is Present
			if(fiStream!=null) {
				strContent=	convertStreamToString(fiStream);
				
				if(strContent!=null&&strContent.length()>0) {					
					memTransactionsObj=getObjectFromXML(strContent);
					//If XML is In-Valid try getting CSV
					if(memTransactionsObj==null) {
						memTransactionsObj=new CSVTransformer().getObjectFromCSV(strContent);	
					}
					//If the Object is not null try the JSON Process
					if(memTransactionsObj!=null) {
					strStatus=new JsonProcess().processToJson(memTransactionsObj);
					}
					else {
						strStatus="The Content is neither a Valid CSV or XML";
					}
				}else {
					strStatus="No Content Found";
				}
				
			}else {
				strStatus="Request File  is Null or Zero Length";
			}		
			
		}
		catch (NullPointerException nullExcpetion) {
			logger.error(nullExcpetion.getLocalizedMessage());
			strStatus="NullPointerException in XML or CSV ->"+nullExcpetion.getLocalizedMessage();
			throw nullExcpetion;
		}catch (UnsupportedEncodingException unspportedEnciding) {
			logger.error(unspportedEnciding.getLocalizedMessage());
			strStatus="UnsupportedEncodingException in XML or CSV ->"+unspportedEnciding.getLocalizedMessage();
			throw unspportedEnciding;
		}catch (IOException ioException) {
			logger.error(ioException.getLocalizedMessage());
			strStatus="IOException in XML or CSV ->"+ioException.getLocalizedMessage();
			throw ioException;
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			strStatus="Generic Exception in XML or CSV ->"+e.getLocalizedMessage();
		}
	    return Response
	      	      .status(Response.Status.OK)
	      	      .entity(strStatus)
	      	      .build();
    }
	
	/**
	 * @param strContent
	 * @return Does not throw exception as It could be CSV
	 */
	private final MemberRegistrationTransactions getObjectFromXML(final String strContent) {
		
		MemberRegistrationTransactions transactionsObject=null;
		XmlMapper xmlMapper=new XmlMapper();
		try {
			transactionsObject=xmlMapper.readValue(strContent, MemberRegistrationTransactions.class);
			
		}catch (JsonParseException jsonParseException) {
			logger.error(jsonParseException.getLocalizedMessage());
			
		 }catch (JsonMappingException jsonMappingException) {
			 logger.error(jsonMappingException.getLocalizedMessage());
			 
		}
		catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			
		}
		
		return transactionsObject;
		
	}
	
	/**
	 * 
	 * @param fiStream
	 * @return
	 */
	private final String convertStreamToString(final InputStream fiStream)throws IOException,UnsupportedEncodingException,NullPointerException {
		String strResult=null;
		ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		
		try {
		while ((length = fiStream.read(buffer)) != -1) {
			resultStream.write(buffer, 0, length);
		}
		strResult=resultStream.toString("UTF-8");
		}catch (NullPointerException nullExcpetion) {
			throw nullExcpetion;
		}catch (UnsupportedEncodingException unspportedEnciding) {
			throw unspportedEnciding;
		}catch (IOException ioException) {
			throw ioException;
		}
		
		return strResult;
	}
}