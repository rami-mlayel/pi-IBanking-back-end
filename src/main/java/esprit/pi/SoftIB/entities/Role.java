package esprit.pi.SoftIB.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import esprit.pi.SoftIB.enumeration.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@javax.persistence.Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue()
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column
	private RoleEnum name;
}
