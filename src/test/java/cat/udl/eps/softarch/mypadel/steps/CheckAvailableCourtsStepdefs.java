package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.domain.PublicMatch;
import cat.udl.eps.softarch.mypadel.repository.MatchInvitationRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import cat.udl.eps.softarch.mypadel.repository.PublicMatchRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CheckAvailableCourtsStepdefs {

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private PublicMatchRepository matchRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private MatchInvitationRepository invitationRepository;

	@Then("^The match has been canceled$")
	public void theMatchHasBeenCanceled() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.cancelled", is(true)));

	}

	@And("^I add the player \"([^\"]*)\" to the match$")
	public void iAddThePlayerToTheMatch(String username) throws Throwable {
		PublicMatch match = matchRepository.findOne(1L);
		Player testPlayer = playerRepository.findOne(username);
		MatchInvitation invitation = new MatchInvitation();
		invitation.setInvites(testPlayer);
		invitation.setInvitesTo(match);
		invitationRepository.save(invitation);
		match.setInvitations(Arrays.asList(invitation));
		matchRepository.save(match);
	}
}
