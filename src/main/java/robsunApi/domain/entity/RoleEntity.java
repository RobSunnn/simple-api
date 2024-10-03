package robsunApi.domain.entity;

import jakarta.persistence.*;
import robsunApi.domain.entity.enums.RoleEnum;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum name;

    public RoleEntity() {
    }

    public RoleEntity(RoleEnum role) {
        this.name = role;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}