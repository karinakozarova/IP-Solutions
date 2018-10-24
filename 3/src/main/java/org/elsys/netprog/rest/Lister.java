package org.elsys.netprog.rest;

public class Lister {
	public String gameId;
	public String secret;
	public int turnsCount;
	public boolean success;
	
	public Lister(String id, int turns, String secret, boolean success) {
		this.gameId = id;
		this.secret = secret;
		this.turnsCount = turns;
		this.success = success;
	}
}
