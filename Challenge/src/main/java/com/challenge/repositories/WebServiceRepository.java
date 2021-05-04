package com.challenge.repositories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.challenge.dto.Ability;
import com.challenge.dto.HeldItem;
import com.challenge.service.RestConsumerService;

@Component
public class WebServiceRepository {
	
	@Autowired RestConsumerService restService;

	public ArrayList<Ability> getAbilities(String pokemonName) {
		Assert.notNull(pokemonName, "The name must not be null");
		return restService.getAbilities(pokemonName);
	}
	
	public int getBaseExperience(String pokemonName) {
		Assert.notNull(pokemonName, "The name must not be null");
		return restService.getBaseExperience(pokemonName);
	}
		
	public ArrayList<HeldItem> getHeldItems(String pokemonName) {
		Assert.notNull(pokemonName, "The name must not be null");
		return restService.getHeldItems(pokemonName);
	}
	
	public int getId(String pokemonName) {
		Assert.notNull(pokemonName, "The name must not be null");
		return restService.getId(pokemonName);
	}
	
	public String getName(String pokemonName) {
		Assert.notNull(pokemonName, "The name must not be null");
		return restService.getName(pokemonName);
	}
	
	public String getLocationAreaEncounters(String pokemonName) {
		Assert.notNull(pokemonName, "The name must not be null");
		return restService.getLocationAreaEncounters(pokemonName);
	}
	
	
	
}
