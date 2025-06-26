package ch.wiss.m223.StudyBuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.wiss.m223.StudyBuddy.model.ERole;
import ch.wiss.m223.StudyBuddy.model.Role;
import ch.wiss.m223.StudyBuddy.repositories.RoleRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class StudyBuddyApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudyBuddyApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception{
		if (roleRepository.count()== 0) {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}
	}

}
