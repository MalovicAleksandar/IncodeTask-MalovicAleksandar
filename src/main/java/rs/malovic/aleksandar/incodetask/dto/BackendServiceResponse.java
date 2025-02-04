package rs.malovic.aleksandar.incodetask.dto;

import java.util.UUID;

public class BackendServiceResponse {
    private UUID verificationId;
    private String query;
    private Result result;

    public BackendServiceResponse() {
    }

    public BackendServiceResponse(UUID verificationId, String query, Result result) {
        this.verificationId = verificationId;
        this.query = query;
        this.result = result;
    }

    public UUID getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(UUID verificationId) {
        this.verificationId = verificationId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
