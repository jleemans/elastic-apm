package eu.deltasource.apm.service;

import co.elastic.apm.api.CaptureSpan;
import com.mysql.cj.util.StringUtils;
import eu.deltasource.apm.database.User;
import eu.deltasource.apm.database.UserRepository;
import java.util.Optional;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
	private final Random random = new Random();
	private UserRepository userRepository;

	private RandomUserService randomUserService;

	@Autowired
	public UserService(UserRepository userRepository, RandomUserService randomUserService) {
		this.userRepository = userRepository;
		this.randomUserService = randomUserService;
	}

	public User save(User user) {
		if (Math.random() < 0.2) {
			try {
				User randomUser = randomUserService.getRandomUser();
				log.info("Overwriting user data with data from randomuser.me API: {}", randomUser);
				user.setName(randomUser.getName());
				user.setEmail(randomUser.getEmail());
			} catch (IllegalArgumentException ex) {
				log.debug("Ignoring response from randomuser.me API");
			}
		}
		sleep();
		User savedUser = userRepository.save(user);
		log.info("Saving user: {}", savedUser);
		return savedUser;
	}

	public Optional<User> get(Integer id) {
		log.info("Getting user with id {}", id);
		sleep();
		return userRepository.findById(id);
	}

	public void delete(Integer id) {
		log.info("Deleting user with id {}", id);
		sleep();
		userRepository.deleteById(id);
	}

	@SuppressWarnings("checkstyle:MagicNumber")
	@CaptureSpan("otherOperations")
	private void sleep() {
		try {
			int millis = random.nextInt(79) + 20; // Sleep randomly between 20 and 99 ms
			log.trace("Sleep ---> {} ms", millis);
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			log.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
	}
}
