package cat.udl.eps.softarch.mypadel.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CancelationDeadlineController {

	//This method will be called every 30 minutes after the last completion of this method.
	@Scheduled(fixedDelay=1800000)
	public void searchReachedDeadlines(){}
}
