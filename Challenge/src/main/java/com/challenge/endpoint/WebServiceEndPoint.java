package com.challenge.endpoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.challenge.pokemonchallenge.GetIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.challenge.beans.AbilityDet;
import com.challenge.beans.BaseExp;
import com.challenge.beans.GetAbilitiesRequest;
import com.challenge.beans.GetAbilitiesResponse;
import com.challenge.beans.GetBaseExperienceRequest;
import com.challenge.beans.GetBaseExperienceResponse;
import com.challenge.beans.GetHeldItemsRequest;
import com.challenge.beans.GetHeldItemsResponse;
import com.challenge.beans.GetIdResponse;
import com.challenge.beans.GetLocationAreaEncountersRequest;
import com.challenge.beans.GetLocationAreaEncountersResponse;
import com.challenge.beans.GetNameRequest;
import com.challenge.beans.GetNameResponse;
import com.challenge.beans.HeldItemVD;
import com.challenge.beans.HeldItemVersion;
import com.challenge.beans.ItemVD;
import com.challenge.beans.LocationAreaEncounters;
import com.challenge.beans.PokemonName;
import com.challenge.dto.Ability;
import com.challenge.dto.HeldItem;
import com.challenge.dto.VersionDetails;
import com.challenge.entities.ChallegeData;
import com.challenge.repositories.ChallengeDataRepository;
import com.challenge.repositories.WebServiceRepository;

@Endpoint
public class WebServiceEndPoint {
	private static final String NAMESPACE_URI = "http://www.challenge.org/pokemonChallenge";
	
	private WebServiceRepository wSrepo;
	
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	ChallengeDataRepository cdRepo;
	
	@Autowired
	public WebServiceEndPoint(WebServiceRepository wSrepo) {
		this.wSrepo = wSrepo;
	}
	
    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    /**
     * Metodo que guarda en la BD la informacion reqerida ip, fecha y metodo ejecutado
     * @param ipAdress
     * @param method
     */
    @Transactional
    private void saveReqInfo(String ipAdress, String method) {
    	ChallegeData cD = new ChallegeData();
    	String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	cD.setIp(ipAdress);
    	cD.setFecha(date);
    	cD.setMetodo(method);
    	cdRepo.save(cD);
    }

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
	@ResponsePayload
	public GetAbilitiesResponse getAbilities(@RequestPayload GetAbilitiesRequest request) {
		String incomingIP = this.httpServletRequest.getRemoteAddr();
		GetAbilitiesResponse response = new GetAbilitiesResponse();
		ArrayList<Ability> ares = wSrepo.getAbilities(request.getPokemonName());
		ArrayList<com.challenge.beans.Ability> abilityLs = new ArrayList<>();
		
		saveReqInfo(incomingIP, "getAbilities");
		
		for(Ability a : ares) {
			com.challenge.beans.Ability ability = new com.challenge.beans.Ability();
			AbilityDet aDet = new AbilityDet();
			aDet.setName(a.ability.name);
			aDet.setUrl(a.ability.url);
			ability.setIsHidden(a.is_hidden);
			ability.setSlot(a.slot);
			ability.setAbilityDetail(aDet);
			abilityLs.add(ability);
		}
		
		response.getAbilities().addAll(abilityLs);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBaseExperienceRequest")
	@ResponsePayload
	public GetBaseExperienceResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest request) {
		String incomingIP = this.httpServletRequest.getRemoteAddr();
		GetBaseExperienceResponse response = new GetBaseExperienceResponse();
		int bExp = wSrepo.getBaseExperience(request.getPokemonName());
		BaseExp bExpRes = new BaseExp();
		
		saveReqInfo(incomingIP, "getBaseExperience");
		
		bExpRes.setBexperience(bExp);
		
		response.setBaseExperience(bExpRes);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeldItemsRequest")
	@ResponsePayload
	public GetHeldItemsResponse getHeldItemsRq(@RequestPayload GetHeldItemsRequest request) {
		String incomingIP = this.httpServletRequest.getRemoteAddr();
		GetHeldItemsResponse response = new GetHeldItemsResponse();
		ArrayList<HeldItem> heldItLs = wSrepo.getHeldItems(request.getPokemonName());
		ArrayList<com.challenge.beans.HeldItem> heldItLsRes = new ArrayList<com.challenge.beans.HeldItem>();
		
		saveReqInfo(incomingIP, "getHeldItemsRq");
		
		for(HeldItem h : heldItLs) {
			com.challenge.beans.HeldItem hI = new com.challenge.beans.HeldItem();
			ItemVD iVd = new ItemVD();
			iVd.setName(h.item.name);
			iVd.setUrl(h.item.url);
			
			ArrayList<HeldItemVD> vdLs = new ArrayList<HeldItemVD>();
			for(VersionDetails vd : h.version_details) {
				HeldItemVD hVd = new HeldItemVD();
				HeldItemVersion hIv = new HeldItemVersion();
				hIv.setName(vd.version.name);
				hIv.setUrl(vd.version.url);
				hVd.setRarity(vd.rarity);
				hVd.setVersion(hIv);
				
				vdLs.add(hVd);
			}
			hI.setItem(iVd);
			hI.getVersionDetails().addAll(vdLs);
			heldItLsRes.add(hI);
		}
		
		response.getHeldItems().addAll(heldItLsRes);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
	@ResponsePayload
	public GetIdResponse getIdRq(@RequestPayload GetIdRequest request) {
		String incomingIP = this.httpServletRequest.getRemoteAddr();
		GetIdResponse response = new GetIdResponse();
		
		saveReqInfo(incomingIP, "getIdRq");
		
		response.setId(wSrepo.getId(request.getPokemonName()));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
	@ResponsePayload
	public GetNameResponse getNameRq(@RequestPayload GetNameRequest request) {
		String incomingIP = this.httpServletRequest.getRemoteAddr();
		GetNameResponse response = new GetNameResponse();
		PokemonName pokeName = new PokemonName();
		
		saveReqInfo(incomingIP, "getNameRq");
		
		pokeName.setNamePoke(wSrepo.getName(request.getPokemonName()));
		response.setName(pokeName);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationAreaEncountersRequest")
	@ResponsePayload
	public GetLocationAreaEncountersResponse getLocationAreaEncountersRq(@RequestPayload GetLocationAreaEncountersRequest request) {
		String incomingIP = this.httpServletRequest.getRemoteAddr();
		GetLocationAreaEncountersResponse response = new GetLocationAreaEncountersResponse();
		LocationAreaEncounters lAe = new LocationAreaEncounters();
		
		saveReqInfo(incomingIP, "getLocationAreaEncountersRq");
		
		lAe.setLocationAencouters(wSrepo.getLocationAreaEncounters(request.getPokemonName()));
		response.setLocationAreaEncounters(lAe);

		return response;
	}
}
