package esprit.pi.SoftIB.entities;

import javax.persistence.*;

import esprit.pi.SoftIB.enumeration.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@javax.persistence.Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column
	private RoleEnum name;
}
