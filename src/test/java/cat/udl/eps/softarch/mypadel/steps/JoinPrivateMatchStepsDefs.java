package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JoinPrivateMatchStepsDefs {

	@Autowired
	private StepDefs stepDefs;

	private ZonedDateTime eventDate;

	private JoinMatch joinMatch=new JoinMatch();

	private MatchInvitation matchInvitation;
	@Autowired
	UserRepository playerRepository;

	@Autowired
	MatchRepository matchRepository;

	private String player;

	private Long id;



	@And("^the player joining it is \"([^\"]*)\"$")
	public void theUserCreatingItIs(String player) throws Throwable {
		this.player = player;
		joinMatch.setPlayer((Player) playerRepository.findByEmail(this.player));
	}



	@And("^the player recived an invitation for Privatematch (\\d+) for (\\d+) - (\\d+) - (\\d+) at (\\d+) hours for (\\d+) minutes$")
	public void thePlayerRecivedAnInvitationForPrivatematchForAtHoursForMinutes(Long id, int day, int month, int year, int hour, int duration) throws Throwable {
		eventDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));

		this.matchInvitation = new MatchInvitation();
		this.matchInvitation.setEventDate(eventDate);
		this.matchInvitation.setInvites((Player) playerRepository.findByEmail(this.player));
		this.matchInvitation.setInvitesTo(matchRepository.findOne(id));

		String message = stepDefs.mapper.writeValueAsString(matchInvitation);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchInvitations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
		invitacionhascreated(id);



	}

	private void invitacionhascreated(Long id) throws Throwable{
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/{id}", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/{id}/invites", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/{id}/invitesTo", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}


	@When("^I join to a private match (\\d+)$")
	public void iJoinToAPrivateMatch(Long id) throws Throwable {
		if(this.matchInvitation != null) {
			joinMatch.setMatch(matchRepository.findOne(id));
			if (Objects.equals(joinMatch.getMatch().getId(), this.matchInvitation.getInvitesTo().getId()) && Objects.equals(joinMatch.getPlayer().getId(), this.matchInvitation.getInvites().getId())) {
				String message = stepDefs.mapper.writeValueAsString(joinMatch);
				stepDefs.result = stepDefs.mockMvc.perform(
					post("/joinMatches")
						.contentType(MediaType.APPLICATION_JSON)
						.content(message)
						.accept(MediaType.APPLICATION_JSON)
						.with(authenticate()))
					.andDo(print());
			}
		}else{
			stepDefs.result = stepDefs.mockMvc.perform(
				delete("/joinMatches/{id}", id)
					.accept(MediaType.APPLICATION_JSON)
					.with(authenticate()));
		}
	}

	@Then("^I successfully joined a private match$")
	public void iSuccessfullyJoinedAPrivateMatch() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@When("^the player didn't recive an invitation for Privatematch (\\d+) for (\\d+) - (\\d+) - (\\d+) at (\\d+) hours for (\\d+) minutes$")
	public void thePlayerDidnTReciveAnInvitationForPrivatematchForAtHoursForMinutes(Long id, int day, int month, int year, int hour, int duration) throws Throwable {
		eventDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/{id}", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/{id}/invites", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/{id}/invitesTo", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

	@Then("^I couldn't join a private match$")
	public void iCouldnTJoinAPrivateMatch() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinInvitations/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
}
