package io.spring.securityjpa.user;

import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String userName;
  private String password;
  private boolean active;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<UserRole> roles;

  @Builder
  public User(String userName, String password, boolean active, Set<UserRole> roles) {
    this.userName = userName;
    this.password = password;
    this.active = active;
    this.roles = roles;
  }
}
