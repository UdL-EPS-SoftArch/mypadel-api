package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.MatchJoinRequest;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.domain.Status;
import cat.udl.eps.softarch.mypadel.repository.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Pl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.time.ZonedDateTime;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AcceptMatchJoinRequestSteps {
	@Autowired
	private StepDefs stepDefs;
	private ZonedDateTime startDate;
	private MatchJoinRequest matchJoinRequest;
	private Player player;
	private JoinMatch joinMatch;




	private Duration duration;

	private ZonedDateTime cancelationDeadline;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomMatchRepository customMatchRepository;
	@Autowired
	MatchJoinRequestRepository matchJoinRequestRepository;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	JoinMatchRepository joinMatchRepository;

	@And("^There is a MatchJoinRequest for that match by user \"([^\"]*)\"$")
	public void thereIsAMatchJoinRequestForThatMatchByUser(String username) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		long id=1;
		matchJoinRequest=new MatchJoinRequest();
		player=new Player();
		player.setEmail(username);
		player.setUsername(username);
		player.setPassword("password");
		playerRepository.save(player);
		matchJoinRequest.setPlayer(player);
		matchJoinRequest.setCustomMatch(customMatchRepository.findOne(id));
		matchJoinRequestRepository.save(matchJoinRequest);
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.status", is(Status.PENDING.toString())))
			.andExpect(status().isOk());
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}/player", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.username", is(username)))
			.andExpect(status().isOk());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}/customMatch", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is((int)id)))
			.andExpect(status().isOk());






	}


	@When("^I accept the MatchJoinRequest$")
	public void iAcceptTheMatchJoinRequest() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		long id=1;
		matchJoinRequest.setStatus(Status.ACCEPTED);
		String message = stepDefs.mapper.writeValueAsString(matchJoinRequest);
		stepDefs.result = stepDefs.mockMvc.perform(
		put("/matchJoinRequests/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(message)
			.with(authenticate()))
			.andDo(print());
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.status", is(Status.ACCEPTED.toString())))
			.andExpect(status().isOk());


	}



	@When("^I reject the MatchJoinRequest$")
	public void iRejectTheMatchJoinRequest() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		long id=1;
		matchJoinRequest.setStatus(Status.REJECTED);
		String message = stepDefs.mapper.writeValueAsString(matchJoinRequest);
		stepDefs.result = stepDefs.mockMvc.perform(
			put("/matchJoinRequests/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.with(authenticate()))
			.andDo(print());
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.status", is(Status.REJECTED.toString())))
			.andExpect(status().isOk());

	}
}
