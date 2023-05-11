package br.com.act.platform.model.cashflow;

import br.com.act.platform.model.request.RequestCashFlowReport;
import br.com.act.platform.util.EncodeUtils;

public final class ReportCashFlow {
    private RequestCashFlowReport request;
    private String content;
    private String linkContent;

    public RequestCashFlowReport getRequest() {
        return request;
    }

    public String getContent() {
        return content;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public ReportCashFlow() {
    }

    private ReportCashFlow(final RequestCashFlowReport request, final String content, final String linkContent) {
        this.request = request;
        this.content = content;
        this.linkContent = linkContent;
    }

    public static class Builder {
        private RequestCashFlowReport request;
        private String content;

        private String generateFilename() {
            StringBuilder buf = new StringBuilder();

            buf.append("cash_flow");

            if (request != null && request.getRequestId() != null) {
                buf.append("[").append(request.getRequestId()).append("]");
            }

            buf.append(".pdf");

            return buf.toString();
        }

        private String generateLinkContent() {
            return "<a href=\"data:application/pdf;base64," +
                    content +
                    "\" download=\"" +
                    generateFilename() +
                    "\"/>";
        }

        public Builder withRequest(final RequestCashFlowReport request) {
            this.request = request;
            return this;
        }

        public Builder withContent(final String content) {
            this.content = content;
            return this;
        }

        public Builder withContent(final byte[] content) {
            this.content = content != null ? EncodeUtils.encodeBase64(content) : null;
            return this;
        }

        public ReportCashFlow build() {
            return new ReportCashFlow(
                    request,
                    content,
                    generateLinkContent()
            );
        }
    }
}
