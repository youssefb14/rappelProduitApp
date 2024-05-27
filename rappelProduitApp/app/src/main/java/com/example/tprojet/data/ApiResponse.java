package com.example.tprojet.data;

import com.squareup.moshi.Json;
import java.util.List;

public class ApiResponse {
    @Json(name = "total_count")
    private int totalCount;
    private List<Link> links;
    private List<Record> records;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static class Link {
        private String rel;
        private String href;

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Record {
        private List<Link> links;
        private RecordDetail record;

        public List<Link> getLinks() {
            return links;
        }

        public void setLinks(List<Link> links) {
            this.links = links;
        }

        public RecordDetail getRecordDetail() {
            return record;
        }

        public void setRecordDetail(RecordDetail recordDetail) {
            this.record = recordDetail;
        }
    }

    public static class RecordDetail {
        private String id;
        private String timestamp;
        private int size;
        private Fields fields;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Fields getFields() {
            return fields;
        }

        public void setFields(Fields fields) {
            this.fields = fields;
        }

        public static class Fields {

            @Json(name = "id")
            private String recordId;
            @Json(name = "noms_des_modeles_ou_references")
            private String productName;
            @Json(name = "liens_vers_les_images")
            private String imageUrl;
            @Json(name = "nature_juridique_du_rappel")
            private String recallNature;
            @Json(name = "motif_du_rappel")
            private String recallReason;
            @Json(name = "lien_vers_la_fiche_rappel")
            private String productUrl;

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getRecallNature() {
                return recallNature;
            }

            public void setRecallNature(String recallNature) {
                this.recallNature = recallNature;
            }

            public String getRecallReason() {
                return recallReason;
            }

            public void setRecallReason(String recallReason) {
                this.recallReason = recallReason;
            }

            public String getRecordId() {
                return recordId;
            }

            public void setRecordId(String recordId) {
                this.recordId = recordId;
            }

            public String getProductUrl() {
                return productUrl;
            }

            public void setProductUrl(String productUrl) {
                this.productUrl = productUrl;
            }
        }
    }
}