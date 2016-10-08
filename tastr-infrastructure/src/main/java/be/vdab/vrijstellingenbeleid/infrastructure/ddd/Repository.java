package be.vdab.vrijstellingenbeleid.infrastructure.ddd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repository<T extends AggregateRoot<ID>, ID extends Id> extends JpaRepository<T, ID> {
}
