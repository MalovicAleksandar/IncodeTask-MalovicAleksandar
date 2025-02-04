package rs.malovic.aleksandar.incodetask.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "verification_storage")
public class Verification {
    @Id
    @Column(name = "verification_id")
    private UUID verificationId;

    @Column(name = "verification", columnDefinition = "CHAR LARGE OBJECT")
    private String verification;

    public Verification() {
    }

    public Verification(UUID verificationId, String verification) {
        this.verificationId = verificationId;
        this.verification = verification;
    }

    public UUID getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(UUID verificationId) {
        this.verificationId = verificationId;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
