

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.util.Arrays.asList;

/**
 * Servlet implementation class MongoDB
 */
@WebServlet("/MongoDB")
public class MongoDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MongoDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MongoCollection<Document> table = establishDatabase();
		//insertData(table);
		String toShow = queryData(table);
		response.getWriter().append("Served at: Songtao Jiang ").append(request.getContextPath()).append(toShow);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected MongoCollection<Document> establishDatabase(){
		MongoClient dbClient = new MongoClient("localhost",27017);
		MongoDatabase db = dbClient.getDatabase("test");
		MongoCollection<Document> table = db.getCollection("restaurants");
		return table;
	}

	protected void insertData(MongoCollection<Document> table){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		try {
			table.insertOne(
			        new Document("address",
			                new Document()
			                        .append("street", "2 Avenue")
			                        .append("zipcode", "10075")
			                        .append("building", "1480")
			                        .append("coord", asList(-73.9557413, 40.7720266)))
			                .append("borough", "Manhattan")
			                .append("cuisine", "Italian")
			                .append("grades", asList(
			                        new Document()
			                                .append("date", format.parse("2014-10-01T00:00:00Z"))
			                                .append("grade", "A")
			                                .append("score", 11),
			                        new Document()
			                                .append("date", format.parse("2014-01-16T00:00:00Z"))
			                                .append("grade", "B")
			                                .append("score", 17)))
			                .append("name", "Vella")
			                .append("restaurant_id", "41704620"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String queryData(MongoCollection<Document> table){
		String result = "";
		Document queryDoc = new Document("grades.score", new Document("$gt", 5));
		FindIterable<Document> iterable = table.find(queryDoc);
		
	    if (iterable != null) {
		    for (Document current : iterable) {
		    	String cuisine = current.getString("cuisine");
		    	result +=cuisine;
		    }
	    }

	    return result;
	    
	}

}
