package org.elsys.netprog.rest;

public class Guesser {
	public String gameId;
	public int cowsNumber;
	public int bullsNumber;
	public int turnsCount;
	public boolean success;
	
	public Guesser(String id, int cows, int bulls, int turns, boolean success) {
		this.gameId = id;
		this.cowsNumber = cows;
		this.bullsNumber = bulls;
		this.turnsCount = turns;
		this.success = success;
	}
}
