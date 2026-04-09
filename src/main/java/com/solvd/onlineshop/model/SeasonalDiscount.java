package com.solvd.onlineshop.model;

import java.time.LocalDateTime;

public class SeasonalDiscount {
    private Long id;
    private Double value;
    private LocalDateTime effectiveFrom;
    private LocalDateTime effectiveTo;
    private Long campaignId;

    public SeasonalDiscount() {
    }

    public SeasonalDiscount(Long id,
                            Double value,
                            LocalDateTime effectiveFrom,
                            LocalDateTime effectiveTo,
                            Long campaignId) {
        this.id = id;
        this.value = value;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.campaignId = campaignId;
    }

    @Override
    public String toString() {
        return "SeasonalDiscount(id=" + this.getId() + ", value=" + this.getValue() + ", effectiveFrom=" + this.getEffectiveFrom() + ", effectiveTo=" + this.getEffectiveTo() + ", campaignId=" + this.getCampaignId() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public Double getValue() {
        return this.value;
    }

    public LocalDateTime getEffectiveFrom() {
        return this.effectiveFrom;
    }

    public LocalDateTime getEffectiveTo() {
        return this.effectiveTo;
    }

    public Long getCampaignId() {
        return this.campaignId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setEffectiveFrom(LocalDateTime effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public void setEffectiveTo(LocalDateTime effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

}
