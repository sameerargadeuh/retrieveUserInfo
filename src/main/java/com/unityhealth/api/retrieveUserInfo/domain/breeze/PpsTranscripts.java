/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.api.retrieveUserInfo.domain.breeze;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sameer S Argade
 */
@Entity
@Table(name = "PPS_TRANSCRIPTS")
@NamedQueries({
    @NamedQuery(name = "PpsTranscripts.findAll", query = "SELECT p FROM PpsTranscripts p")})
public class PpsTranscripts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSCRIPT_ID")
    private Integer transcriptId;
    @Column(name = "ASSET_ID")
    private Integer assetId;
    @Column(name = "PRINCIPAL_ID")
    private Integer principalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TICKET")
    private String ticket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private Character status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCORE")
    private double score;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MAX_SCORE")
    private Double maxScore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BYTES_UP")
    private int bytesUp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BYTES_DOWN")
    private int bytesDown;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "DATE_CLOSED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClosed;
    @Column(name = "RecordCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordCreated;
    @Column(name = "TIME_AICC")
    private Double timeAicc;

    public PpsTranscripts() {
    }

    public PpsTranscripts(Integer transcriptId) {
        this.transcriptId = transcriptId;
    }

    public PpsTranscripts(Integer transcriptId, String ticket, Character status, double score, int bytesUp, int bytesDown, Date dateCreated) {
        this.transcriptId = transcriptId;
        this.ticket = ticket;
        this.status = status;
        this.score = score;
        this.bytesUp = bytesUp;
        this.bytesDown = bytesDown;
        this.dateCreated = dateCreated;
    }

    public Integer getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(Integer transcriptId) {
        this.transcriptId = transcriptId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Integer principalId) {
        this.principalId = principalId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public int getBytesUp() {
        return bytesUp;
    }

    public void setBytesUp(int bytesUp) {
        this.bytesUp = bytesUp;
    }

    public int getBytesDown() {
        return bytesDown;
    }

    public void setBytesDown(int bytesDown) {
        this.bytesDown = bytesDown;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public Date getRecordCreated() {
        return recordCreated;
    }

    public void setRecordCreated(Date recordCreated) {
        this.recordCreated = recordCreated;
    }

    public Double getTimeAicc() {
        return timeAicc;
    }

    public void setTimeAicc(Double timeAicc) {
        this.timeAicc = timeAicc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transcriptId != null ? transcriptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PpsTranscripts)) {
            return false;
        }
        PpsTranscripts other = (PpsTranscripts) object;
        if ((this.transcriptId == null && other.transcriptId != null) || (this.transcriptId != null && !this.transcriptId.equals(other.transcriptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.api.retrieveUserInfo.domain.PpsTranscripts[ transcriptId=" + transcriptId + " ]";
    }
    
}
