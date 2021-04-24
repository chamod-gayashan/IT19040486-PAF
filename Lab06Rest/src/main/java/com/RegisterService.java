package com;


import model.RegisterFunder;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Register")
public class RegisterService
{
		RegisterFunder regObj = new RegisterFunder();
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		{
			return regObj.readItems();
		}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(@FormParam("userName") String userName,
							@FormParam("userPassword") String userPassword,
							@FormParam("userCode") String userCode,
							@FormParam("userEmail") String userEmail,
							@FormParam("userPhone") String userPhone)
		{
				String output = regObj.insertItem(userName, userPassword, userCode, userEmail, userPhone);
				return output;
		}

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String fundId)
		{
			//Convert the input string to a JSON object
			JsonObject registerObject = new JsonParser().parse(fundId).getAsJsonObject();
			//Read the values from the JSON object
			String id = registerObject.get("id").getAsString();
			String userName = registerObject.get("userName").getAsString();
			String userPassword = registerObject.get("userPassword").getAsString();
			String userCode = registerObject.get("userCode").getAsString();
			String userEmail = registerObject.get("userEmail").getAsString();
			String userPhone = registerObject.get("userPhone").getAsString();
			String output = regObj.updateItem(id, userName, userPassword, userCode, userEmail,userPhone);
			return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String itemData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String itemID = doc.select("id").text();
		 String output = regObj.deleteItem(itemID);
		return output;
		}
		
		
		
		@GET
	    @Path("/Email/{userEmail}/Password/{userPassword}")
		//@Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_HTML)
	    public String userLogin(@PathParam("userEmail") String email,
				@PathParam("userPassword") String password) {
			
				return regObj.getUser(email,password);
			
	    }
}
