package rs.malovic.aleksandar.incodetask.db;

import rs.malovic.aleksandar.incodetask.dto.Result;
import rs.malovic.aleksandar.incodetask.dto.Source;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerificationResult {
    private UUID verificationId;
    private String queryText;
    private LocalDateTime timestamp;
    private Result result;
    private Source source;

    public VerificationResult() {
    }

    public VerificationResult(UUID verificationId, String queryText, LocalDateTime timestamp, Result result, Source source) {
        this.verificationId = verificationId;
        this.queryText = queryText;
        this.timestamp = timestamp;
        this.result = result;
        this.source = source;
    }

    public UUID getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(UUID verificationId) {
        this.verificationId = verificationId;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
