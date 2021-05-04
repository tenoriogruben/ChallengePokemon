package com.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.service.RestConsumerService;

class TestRestConsumerServiceTest extends ChallengeApplicationTests  {

	@Autowired RestConsumerService restService;
	
	@Test
	public void testRestService () {
		String pokemonName = "charizard";
		
//		restService.getThePokemon(pokemonName);
		
	}

}
