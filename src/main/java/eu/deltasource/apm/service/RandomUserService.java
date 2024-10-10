package eu.deltasource.apm.service;

import eu.deltasource.apm.database.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RandomUserService {

	private static final String RANDOM_USER_API_URL = "https://randomuser.me/api?nat=gb";
	private static final Pattern pattern = Pattern.compile(".*\"first\":\"(\\w+)\".+\"last\":\"(\\w+)\".*email\":\"([^\"]+)\".*");

	public User getRandomUser() {
		log.info("Getting a random user from {}", RANDOM_USER_API_URL);
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(RANDOM_USER_API_URL, String.class);

		return parseResponse(response);
	}

	protected User parseResponse(String response) {
		// Use regex to find first and last name in the response
		Matcher matcher = pattern.matcher(response);

		if (matcher.find()) {
			String firstName = matcher.group(1);
			String lastName = matcher.group(2);
			String email = matcher.group(3);
			log.info("Retrieved {} {} ({}) from randomuser.me", firstName, lastName, email);
			User user = new User();
			user.setName(firstName + " " + lastName);
			user.setEmail(email);
			return user;
		} else {
			throw new IllegalArgumentException(String.format("Could not find the required data in the response: %s", response));
		}
	}
}
