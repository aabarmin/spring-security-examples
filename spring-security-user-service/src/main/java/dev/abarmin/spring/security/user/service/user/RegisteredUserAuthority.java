package dev.abarmin.spring.security.user.service.user;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Aleksandr Barmin
 */
@Data
@Builder
@Table("AUTHORITIES")
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserAuthority {
  @Column("AUTHORITY")
  private String authority;

  @Column("USERNAME")
  private String userName;
}
