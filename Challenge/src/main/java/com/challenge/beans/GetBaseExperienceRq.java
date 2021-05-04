package com.challenge.beans;

public class GetBaseExperienceRq {

	public GetBaseExperienceRq createGetBaseExperienceRequest() {
		  return new GetBaseExperienceRq();
		}
	
	public String pokemonName;

	public String getPokemonName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}
}
