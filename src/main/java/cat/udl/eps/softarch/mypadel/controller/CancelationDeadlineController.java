package cat.udl.eps.softarch.mypadel.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CancelationDeadlineController {

	//This method will be called 5 seconds after the last completion of this method.
	@Scheduled(fixedDelay=5000)
	public void searchReachedDeadlines(){}
}
