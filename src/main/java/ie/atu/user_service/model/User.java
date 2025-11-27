package ie.atu.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank@Size(max=50)@Column(unique = true)
    private String userID;
    @Email@NotBlank
    private String email;
    @NotBlank@Size(min = 3, max = 100)
    private String password;
}