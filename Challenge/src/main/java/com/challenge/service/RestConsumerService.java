package com.challenge.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.dto.Ability;
import com.challenge.dto.HeldItem;
import com.challenge.dto.Pokemon;


/**
 * Servicio que consume el servicio REST
 * @author uben Tenorio
 *
 */
@Service
public class RestConsumerService {
	
	
	private static Pokemon getPokemon(String pokemonName)
	{
	    final String uri = "https://pokeapi.co/api/v2/pokemon/{pokemonName}";
	 
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> request = new HttpEntity<String>(headers);
	     
	    ResponseEntity<Pokemon> result = restTemplate.exchange(uri, HttpMethod.GET, request, Pokemon.class, pokemonName);
	    
	    if(result.getStatusCode() == HttpStatus.OK) {
	        return result.getBody();
	    } else {
	        return result.getBody();
	    }
	}
	
	public static ArrayList<Ability> getAbilities(String pokemonName) {
		ArrayList<Ability> abilitiesRes = new ArrayList<Ability>();
		Pokemon res = getPokemon(pokemonName);
		
		abilitiesRes = res.abilities;
		
		return abilitiesRes;
	}
	
	public static int getBaseExperience(String pokemonName) {
		Pokemon res = getPokemon(pokemonName);
		
		return res.base_experience;
	}
	
	public static ArrayList<HeldItem> getHeldItems(String pokemonName) {
		Pokemon res = getPokemon(pokemonName);
		
		return res.held_items;
	}
	
	public static int getId(String pokemonName) {
		Pokemon res = getPokemon(pokemonName);
		
		return res.id;
	}
	
	public static String getName(String pokemonName) {
		Pokemon res = getPokemon(pokemonName);
		
		return res.name;
	}
	
	public static String getLocationAreaEncounters(String pokemonName) {
		Pokemon res = getPokemon(pokemonName);
		
		return res.location_area_encounters;
	}
	
	
}
