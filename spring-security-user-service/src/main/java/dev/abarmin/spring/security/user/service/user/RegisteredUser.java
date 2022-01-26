package dev.abarmin.spring.security.user.service.user;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Aleksandr Barmin
 */
@Data
@Builder
@Table("USERS")
@NoArgsConstructor
public class RegisteredUser {
  @Id
  @Column("USERNAME")
  private String userName;

  @Column("PASSWORD")
  private String password;

  @Column("ENABLED")
  private boolean enabled;

  @Version
  @Column("VERSION")
  private int version;

  @MappedCollection(idColumn = "USERNAME")
  private Set<RegisteredUserAuthority> authorities = new HashSet<>();

  public RegisteredUser(String userName, String password, boolean enabled, int version, Set<RegisteredUserAuthority> authorities) {
    this.userName = userName;
    this.password = password;
    this.enabled = enabled;
    this.version = version;
    authorities.forEach(this::addAuthority);
  }

  public RegisteredUser addAuthority(final RegisteredUserAuthority authority) {
    this.authorities.add(authority);
    authority.setUserName(this.getUserName());
    return this;
  }
}
