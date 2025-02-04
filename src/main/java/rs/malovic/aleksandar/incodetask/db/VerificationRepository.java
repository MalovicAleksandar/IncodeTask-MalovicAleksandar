package rs.malovic.aleksandar.incodetask.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerificationRepository extends JpaRepository<Verification, UUID> {
    Verification findByVerificationId(UUID id);
}
