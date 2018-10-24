package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/game")
public class GameController {
	private static HashMap<String, Boolean> successes = new HashMap<String, Boolean>(); 
	private static HashMap<String, String> secrets = new HashMap<String, String>();
	private static HashMap<String, Integer> turn_store = new HashMap<String, Integer>();
	private static Random randomiser = new Random();
	
	public static String newGame() {
		String id = UUID.randomUUID().toString();
		String cows = "";
		
		while (cows.length() < 4) {
			char new_char = (char) (randomiser.nextInt(9) + 48);
			boolean repeated = false;
			for (char letter: cows.toCharArray()) if (letter == new_char) {repeated = true;break;}
			if (!repeated) cows += new_char;
		}
		
		turn_store.put(id, 0);
		secrets.put(id, cows);
		successes.put(id, false);
		return id;
	}
	
	public static boolean exists(String id) {
		return secrets.containsKey(id);
	}
	
	public static boolean isValid(String guess) {		
		if ((int) guess.chars().distinct().count() != 4) return false;
		return guess.matches("[123456789]\\d{3}");
	}
	
	private static ArrayList<Character> StringToList(String str) {
		return (ArrayList<Character>) str.chars().mapToObj((i) -> Character.valueOf((char)i)).collect(Collectors.toList());
	}
		
	
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException {
		return Response.created(new URI("/games")).entity(newGame()).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String id, @PathParam("guess") String guess) throws Exception{
		if (!exists(id)) return Response.status(404).build();
		if (!isValid(guess))return Response.status(400).build();

		int turns = turn_store.get(id),bulls = 0,cows = 0;
		turn_store.put(id, ++turns);		
		ArrayList<Character> secret = StringToList(secrets.get(id));
		ArrayList<Character> attempt = StringToList(guess);
		
		for (int i = 0; i < 4; i++) {
			if (secret.get(i) == attempt.get(i)) bulls += 1;
			else if (attempt.contains(secret.get(i))) cows += 1;
		}
		
		successes.put(id, bulls == 4);
		return Response.status(200).entity(new Guesser(id, cows, bulls, turns, bulls == 4)).build();
		
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		ArrayList<Lister> elements = new ArrayList<Lister>();
		Set<String> ids = secrets.keySet();
		for (String id: ids) elements.add(new Lister(id, turn_store.get(id), (successes.get(id)) ? secrets.get(id) : "****", successes.get(id)));
		return Response.status(200).entity(elements).build();
	}
}
