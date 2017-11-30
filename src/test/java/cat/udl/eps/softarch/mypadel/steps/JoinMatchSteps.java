package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.repository.JoinMatchRepository;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class JoinMatchSteps {

    @Autowired
    private StepDefs stepDefs;


	private JoinMatch joinMatch;
	private PublicMatch publicMatch = new PublicMatch();
	private PrivateMatch privateMatch = new PrivateMatch();
	private MatchInvitation matchInv;


	private ZonedDateTime startDate;

	private Duration duration;

	private ZonedDateTime cancelationDeadline;

	private String player;

	@Autowired
	UserRepository playerRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	JoinMatchRepository joinMatchRepository;


	@When("^I join to a match$")
    public void iJoinToAMatch() throws Throwable {
		JoinMatch joinMatch = new JoinMatch();

		String message = stepDefs.mapper.writeValueAsString(joinMatch);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/joinMatches")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(message)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(authenticate()))
                .andDo(print());
    }

	@And("^the user joining it is \"([^\"]*)\"$")
	public void theUserCreatingItIs(String player) throws Throwable {
		this.player = player;
		publicMatch.setMatchCreator((Player) playerRepository.findByEmail(this.player));
		privateMatch.setMatchCreator((Player) playerRepository.findByEmail(this.player));
	}


	@When("^I join to a created match (\\d+)$")
	public void iJoinToACreatedMatch(long id) throws Throwable {
		joinMatch = new JoinMatch();
		joinMatch.setPlayer((Player) playerRepository.findByEmail("testplayer@mypadel.cat"));
		joinMatch.setMatch(matchRepository.findOne(id));
		String message = stepDefs.mapper.writeValueAsString(joinMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/joinMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());

	}

	private void createPublicMatch(){
		publicMatch.setStartDate(startDate);
		publicMatch.setDuration(this.duration);
		publicMatch.setCancelationDeadline(cancelationDeadline);
		publicMatch.setCourtType(CourtType.INDOOR);
		publicMatch.setLevel(Level.ADVANCED);
	}

	private void createPrivateMatch(){
		privateMatch.setStartDate(startDate);
		privateMatch.setDuration(this.duration);
		privateMatch.setCancelationDeadline(cancelationDeadline);
		privateMatch.setCourtType(CourtType.INDOOR);
	}

	@And("^There is a \"([^\"]*)\" match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+)$")
	public void thereIsPublicMatchOnAtPmForMinutesAndDeadline(String matchType, int day, int month, int year, int hour, int duration,
															  int cancelationDay, int cancelationMonth,
															  int cancelationYear) throws Throwable {
		startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0, 0, ZoneId.of("+00:00"));

		if (Objects.equals(matchType, "public")){
			createPublicMatch();
			String message = stepDefs.mapper.writeValueAsString(publicMatch);
			stepDefs.result = stepDefs.mockMvc.perform(
				post("/publicMatches")
					.contentType(MediaType.APPLICATION_JSON)
					.content(message)
					.accept(MediaType.APPLICATION_JSON)
					.with(authenticate()))
				.andDo(print());
		}else {
			createPrivateMatch();
			String message = stepDefs.mapper.writeValueAsString(privateMatch);
			stepDefs.result = stepDefs.mockMvc.perform(
				post("/privateMatches")
					.contentType(MediaType.APPLICATION_JSON)
					.content(message)
					.accept(MediaType.APPLICATION_JSON)
					.with(authenticate()))
				.andDo(print());
		}

	}

	@Then("^A player has successfully joined a match (\\d+)$")
	public void aPlayerHasSuccessfullyJoinedAMatch(int resourcesId) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}", resourcesId)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}/player", resourcesId)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}/match", resourcesId)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@When("^I leave a match with id (\\d+)$")
	public void iLeaveAMatchWithId(long id) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			delete("/joinMatches/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
		;
	}


	@Then("^I successfully leave the match with id (\\d+)$")
	public void iSuccessfullyLeaveTheMatchWithId(int id) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}", id)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}


	@And("^I already joined a match with id (\\d+)$")
	public void iAlreadyJoinedAMatchWithId(int id) throws Throwable {
		String matchType = "public";
		String player = "testplayer@mypadel.cat";
		int day = 11;
		int month = 10;
		int year = 2017;
		int hour = 1;
		int duration = 30;
		int cancelationDay = 30;
		int cancelationMonth = 10;
		int cancelationYear = 2017;

		thereIsPublicMatchOnAtPmForMinutesAndDeadline(matchType, day, month, year, hour, duration, cancelationDay, cancelationMonth, cancelationYear);
		theUserCreatingItIs(player);
		iJoinToACreatedMatch(id);
		aPlayerHasSuccessfullyJoinedAMatch(id);

	}

	@And("^I have been invited to a private match (\\d+) with the message \"([^\"]*)\"$")
	public void iHaveBeenInvitedToAPrivateMatchWithTheMessage(long id, String inviteMessage) throws Throwable {
		this.matchInv = new MatchInvitation();
		this.matchInv.setMessage(inviteMessage);
		this.matchInv.setInvites((Player) playerRepository.findByEmail(this.player));
		this.matchInv.setInvitesTo(matchRepository.findOne(id));

		String message = stepDefs.mapper.writeValueAsString(matchInv);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchInvitations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@When("^I join to a created private match (\\d+)$")
	public void iJoinToACreatedPrivateMatch(long id) throws Throwable {
			joinMatch = joinMatchRepository.findOne(id);
			String message = stepDefs.mapper.writeValueAsString(joinMatch);
			stepDefs.result = stepDefs.mockMvc.perform(
				post("/joinMatches")
					.contentType(MediaType.APPLICATION_JSON)
					.content(message)
					.accept(MediaType.APPLICATION_JSON)
					.with(authenticate()))
				.andDo(print());
	}


	@And("^And It has been created a new match invitation (\\d+)$")
	public void andItHasBeenCreatedANewMatchInvitation(int id) throws Throwable {
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

	@Then("^I couldn't join to the private match (\\d+)$")
	public void iCouldnTJoinToThePrivateMatch(int id) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@When("^I try to join to another match on the same datetime$")
	public void iTryToJoinToAnotherMatchOnTheSameDatetime() throws Throwable {
		createPublicMatch();
		String message = stepDefs.mapper.writeValueAsString(publicMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^I joined to a \"([^\"]*)\" match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+)-(\\d+)-(\\d+)$")
	public void iJoinedToAMatchOnAtPmForMinutesAndDeadline(String matchType, int day, int month, int year, int hour, int duration,
														   int cancelationDay, int cancelationMonth,
														   int cancelationYear) throws Throwable {
		this.startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0, 0, ZoneId.of("+00:00"));

		createPublicMatch();
		String message = stepDefs.mapper.writeValueAsString(publicMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^There is a \"([^\"]*)\" match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+)-(\\d+)-(\\d+)$")
	public void thereIsAMatchOnAtPmForMinutesAndDeadline(String matchType, int day, int month, int year, int hour, int duration,
														 int cancelationDay, int cancelationMonth,
														 int cancelationYear) throws Throwable {
		PublicMatch publicMatch = new PublicMatch();
		publicMatch.setMatchCreator(new Player());
		this.startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0, 0, ZoneId.of("+00:00"));
		publicMatch.setStartDate(startDate);
		publicMatch.setDuration(this.duration);
		publicMatch.setCancelationDeadline(cancelationDeadline);
		publicMatch.setCourtType(CourtType.INDOOR);
		publicMatch.setLevel(Level.ADVANCED);

		String message = stepDefs.mapper.writeValueAsString(publicMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^there are three more players in this match$")
	public void thereAreThreeMorePlayersInThisMatch() throws Throwable {
		JoinMatch joinMatch_1 = new JoinMatch();
		joinMatch_1.setMatch(matchRepository.findOne((long)1));
		String message_1 = stepDefs.mapper.writeValueAsString(joinMatch_1);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/joinMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message_1)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());

		JoinMatch joinMatch_2 = new JoinMatch();
		joinMatch_2.setMatch(matchRepository.findOne((long)1));
		String message_2 = stepDefs.mapper.writeValueAsString(joinMatch_2);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/joinMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message_2)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@Then("^A court is reserved$")
	public void aCourtIsReserved() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/reservations/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@And("^I create a public match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+)-(\\d+)-(\\d+)$")
	public void iCreateAMatchOnAtPmForMinutesAndDeadline(int day, int month, int year, int hour, int duration,
														 int cancelationDay, int cancelationMonth,
														 int cancelationYear) throws Throwable {
		PublicMatch publicMatch = new PublicMatch();
		publicMatch.setMatchCreator((Player) playerRepository.findByEmail("testplayer@mypadel.cat"));
		this.startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0, 0, ZoneId.of("+00:00"));
		publicMatch.setStartDate(startDate);
		publicMatch.setDuration(this.duration);
		publicMatch.setCancelationDeadline(cancelationDeadline);
		publicMatch.setCourtType(CourtType.INDOOR);
		publicMatch.setLevel(Level.ADVANCED);

		String message = stepDefs.mapper.writeValueAsString(publicMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^There is a match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+)-(\\d+)-(\\d+)$")
	public void thereIsAMatchOnAtPmForMinutesAndDeadline(int day, int month, int year, int hour, int duration,
														 int cancelationDay, int cancelationMonth,
														 int cancelationYear) throws Throwable {
		PublicMatch publicMatch = new PublicMatch();
		this.startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0, 0, ZoneId.of("+00:00"));
		publicMatch.setStartDate(startDate);
		publicMatch.setDuration(this.duration);
		publicMatch.setCancelationDeadline(cancelationDeadline);
		publicMatch.setCourtType(CourtType.INDOOR);
		publicMatch.setLevel(Level.ADVANCED);

		matchRepository.save(publicMatch);
	}

	@Then("^The reservation for this match is cancelled$")
	public void theReservationForThisMatchIsCancelled() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/reservations/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@When("^A player leaves this match$")
	public void aPlayerLeavesThisMatch() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			delete("/joinMatches/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
		;
	}
}
