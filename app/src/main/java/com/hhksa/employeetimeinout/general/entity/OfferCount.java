package com.hhksa.employeetimeinout.general.entity;

public class OfferCount {
    long sent;
    long viewed;
    long applied;
    long pending_onboarding;
    long pending_contract;
    long confirmed;
    long withdrawn;
    long rejected;
    long cancelled;
    long no_show;
    long contract_ended;

    public long getSent() {
        return sent;
    }

    public void setSent(long sent) {
        this.sent = sent;
    }

    public long getViewed() {
        return viewed;
    }

    public void setViewed(long viewed) {
        this.viewed = viewed;
    }

    public long getApplied() {
        return applied;
    }

    public void setApplied(long applied) {
        this.applied = applied;
    }

    public long getPending_onboarding() {
        return pending_onboarding;
    }

    public void setPending_onboarding(long pending_onboarding) {
        this.pending_onboarding = pending_onboarding;
    }

    public long getPending_contract() {
        return pending_contract;
    }

    public void setPending_contract(long pending_contract) {
        this.pending_contract = pending_contract;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(long withdrawn) {
        this.withdrawn = withdrawn;
    }

    public long getRejected() {
        return rejected;
    }

    public void setRejected(long rejected) {
        this.rejected = rejected;
    }

    public long getCancelled() {
        return cancelled;
    }

    public void setCancelled(long cancelled) {
        this.cancelled = cancelled;
    }

    public long getNo_show() {
        return no_show;
    }

    public void setNo_show(long no_show) {
        this.no_show = no_show;
    }

    public long getContract_ended() {
        return contract_ended;
    }

    public void setContract_ended(long contract_ended) {
        this.contract_ended = contract_ended;
    }
}
