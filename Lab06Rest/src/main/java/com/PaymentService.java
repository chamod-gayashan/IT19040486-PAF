package com;


import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService
{
		Payment paymentObj = new Payment();
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		{
			return paymentObj.readItems();
		}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(@FormParam("totalProjects") String totalProjects,
							@FormParam("totalPrice") String totalPrice)
							
		{
				String output = paymentObj.insertItem(totalProjects, totalPrice);
				return output;
		}

		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String itemData)
		{
			//Convert the input string to a JSON object
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
			//Read the values from the JSON object
			String paymentId = itemObject.get("paymentId").getAsString();
			String totalProjects = itemObject.get("totalProjects").getAsString();
			String totalPrice = itemObject.get("totalPrice").getAsString();
			String output = paymentObj.updateItem(paymentId, totalProjects, totalPrice);
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
		 String itemID = doc.select("itemID").text();
		 String output = paymentObj.deleteItem(itemID);
		return output;
		}

		
}



