package br.com.act.platform.model.request;

import br.com.act.platform.util.DateUtils;
import br.com.act.platform.util.IdUtils;

import java.time.LocalDate;

public final class RequestCashFlowReport {
    private String requestId = IdUtils.createUUID();
    private LocalDate beginDate = DateUtils.now().toLocalDate();
    private LocalDate endDate = DateUtils.now().toLocalDate();

    public String getRequestId() {
        return requestId;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public RequestCashFlowReport() {
    }

    private RequestCashFlowReport(
            final String requestId,
            final LocalDate beginDate,
            final LocalDate endDate
    ) {
        this.requestId = requestId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RequestCashFlowReport other && requestId.equalsIgnoreCase(other.getRequestId());
    }

    @Override
    public int hashCode() {
        return requestId.hashCode();
    }

    public static class Builder {
        private String requestId;
        private LocalDate beginDate = DateUtils.now().toLocalDate();
        private LocalDate endDate = DateUtils.now().toLocalDate();

        public Builder withRequestId(final String requestId) {
            this.requestId = requestId != null ? requestId.trim().toLowerCase() : IdUtils.createUUID();
            return this;
        }

        public Builder withBeginDate(final LocalDate beginDate) {
            this.beginDate = beginDate != null ? beginDate : DateUtils.now().toLocalDate();
            return this;
        }

        public Builder withEndDate(final LocalDate endDate) {
            this.endDate = endDate != null ? endDate : DateUtils.now().toLocalDate();
            return this;
        }

        public RequestCashFlowReport build() {
            return new RequestCashFlowReport(
                    requestId,
                    beginDate,
                    endDate
            );
        }
    }
}
