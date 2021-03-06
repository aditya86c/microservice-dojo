package msvcdojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * @author Igor Moochnick
 */
@SpringBootApplication
public class AccountsServiceApplication {

    public static void main(String[] args) {
            SpringApplication.run(AccountsServiceApplication.class, args);
    }
}

@RepositoryRestResource
interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUsername(@Param("username") String username);

// tag::findByRole[]
    List<Account> findByRole(@Param("role") String role);
// end::findByRole[]
}


@Entity
class Account {

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;

// tag::data-model[]
    private String role;
    public String getRole() { return role; }
// end::data-model[]

    Account() { // JPA only
    }

    public Account(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
