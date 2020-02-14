package com.hhksa.employeetimeinout.general.entity;

public class ApplicationCount {
    long pending_onboarding;
    long applied;
    long pending_contract;
    long rejected;
    long withdrawn;
    long hired;

    public long getPending_onboarding() {
        return pending_onboarding;
    }

    public void setPending_onboarding(long pending_onboarding) {
        this.pending_onboarding = pending_onboarding;
    }

    public long getApplied() {
        return applied;
    }

    public void setApplied(long applied) {
        this.applied = applied;
    }

    public long getPending_contract() {
        return pending_contract;
    }

    public void setPending_contract(long pending_contract) {
        this.pending_contract = pending_contract;
    }

    public long getRejected() {
        return rejected;
    }

    public void setRejected(long rejected) {
        this.rejected = rejected;
    }

    public long getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(long withdrawn) {
        this.withdrawn = withdrawn;
    }

    public long getHired() {
        return hired;
    }

    public void setHired(long hired) {
        this.hired = hired;
    }
}
