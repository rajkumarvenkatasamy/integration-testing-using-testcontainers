package com.example.employeeservice;

import com.example.employeeservice.dto.Department;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Objects;

@Testcontainers
@SpringBootTest
class EmployeeServiceApplicationTests {

	@Container
	public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14")
			.withDatabaseName("postgres")
			.withUsername("postgres")
			.withPassword("P@ssw0rd");


	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
	}

	@Container
	public static GenericContainer<?> departmentService =
			new GenericContainer<>(DockerImageName.parse("department-service:latest"))
					.withExposedPorts(8081, 8081)
					.waitingFor(Wait.forHttp("/department/1"));

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeDetailsService employeeDetailsService;

	@Test
	void assertEmployeeCountByDepartmentId() {

		List<Employee> employeeList = employeeRepository.findByDepartmentId(1);
		System.out.println("Retrieved employeeList count: " + employeeList.size());
		assert employeeList.size() == 3;
	}

	@Test
	void assertDepartmentServiceCall(){
		RestTemplate restTemplate = new RestTemplate();
		String departmentRestEndpoint = "http://"
				+ departmentService.getHost()
				+ ":"  + departmentService.getMappedPort(8081)
				+ "/department/1";
		Department department =
				restTemplate.getForObject(departmentRestEndpoint, Department.class);

		System.out.println("Retrieved department details: " + department);
		assert Objects.equals(Objects.requireNonNull(department).getName(), "Engineering");
	}

}
