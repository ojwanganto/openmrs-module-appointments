package org.openmrs.module.appointments.model;

import org.apache.commons.lang.time.DateUtils;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.annotation.Independent;
import org.openmrs.module.appointments.notification.NotificationResult;
import org.openmrs.module.appointments.util.DateUtil;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Appointment extends BaseOpenmrsData implements Serializable {
    private Integer appointmentId;
    private String appointmentNumber;
    private Date dateCreated;
    private Patient patient;
    private AppointmentServiceDefinition service;
    private AppointmentServiceType serviceType;
    private Provider provider;
    private Location location;
    private Date startDateTime;
    private Date endDateTime;
    private AppointmentKind appointmentKind;
    private AppointmentStatus status;
    private AppointmentPriority priority;
    private String comments;
    private Set<AppointmentProvider> providers;
    private AppointmentRecurringPattern appointmentRecurringPattern;
    private Set<AppointmentAudit> appointmentAudits = new HashSet<>();
    private Appointment relatedAppointment;
    private String teleHealthVideoLink;
    @Independent
    private Set<Encounter> fulfillingEncounters;

    /**
     * This attribute is not a entity property. Just a placeholder for the clients to prepare response relevant  to notification
     */
    private List<NotificationResult> notificationResults;

    public Set<AppointmentAudit> getAppointmentAudits() {
        return appointmentAudits;
    }

    public void setAppointmentAudits(Set<AppointmentAudit> appointmentAudits) {
        this.appointmentAudits = appointmentAudits;
    }

    public AppointmentRecurringPattern getAppointmentRecurringPattern() {
        return appointmentRecurringPattern;
    }

    public void setAppointmentRecurringPattern(AppointmentRecurringPattern appointmentRecurringPattern) {
        this.appointmentRecurringPattern = appointmentRecurringPattern;
    }

    public Set<AppointmentProvider> getProviders() {
        return providers;
    }

    public Set<AppointmentProvider> getProvidersWithResponse(AppointmentProviderResponse providerResponse)
    {
        if (providers == null) return Collections.EMPTY_SET;

        return providers.stream()
            .filter(provider -> provider.getResponse().equals(providerResponse))
                .collect(Collectors.toSet());
    }

    public void setProviders(Set<AppointmentProvider> providers) {
        this.providers = providers;
    }

    public Appointment() {
        super();
        this.status = AppointmentStatus.Scheduled;
    }

    @Override
    public Integer getId() {
        return getAppointmentId();
    }

    @Override
    public void setId(Integer integer) {
        setAppointmentId(integer);
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppointmentServiceDefinition getService() {
        return service;
    }

    public void setService(AppointmentServiceDefinition service) {
        this.service = service;
    }

    public AppointmentServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(AppointmentServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public AppointmentKind getAppointmentKind() {
        return appointmentKind;
    }

    public void setAppointmentKind(AppointmentKind appointmentKind) {
        this.appointmentKind = appointmentKind;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public AppointmentPriority getPriority() {
        return priority;
    }

    public void setPriority(AppointmentPriority priority) {
        this.priority = priority;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateFromStartDateTime() {
        return DateUtils.truncate(getStartDateTime(), java.util.Calendar.DAY_OF_MONTH);
    }

    public Appointment getRelatedAppointment() {
        return relatedAppointment;
    }

    public void setRelatedAppointment(Appointment relatedAppointment) {
        this.relatedAppointment = relatedAppointment;
    }

    public Boolean isRecurring() {
        return Objects.nonNull(this.appointmentRecurringPattern);
    }

    public Boolean isFutureAppointment() {
        Date startOfDay = DateUtil.getStartOfDay();
        return this.getStartDateTime().after(startOfDay) || startOfDay.equals(this.getStartDateTime());
    }

    public boolean isSameAppointment(Appointment appointment) {
        return this.getUuid().equals(appointment.getUuid());
    }

    public Boolean hasPatientAttribute(String attrName) {
        if (this.patient == null) return false;
        return this.patient.getAttribute(attrName) != null;
    }

    public String getTeleHealthVideoLink() {
        return teleHealthVideoLink;
    }

    public void setTeleHealthVideoLink(String teleHealthVideoLink) {
        this.teleHealthVideoLink = teleHealthVideoLink;
    }

    public void setNotificationResults(List<NotificationResult> notificationResults) {
        this.notificationResults = notificationResults;
    }

    public List<NotificationResult> getNotificationResults() {
        return notificationResults;
    }

    @Override
    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<Encounter> getFulfillingEncounters() {
        return fulfillingEncounters;
    }

    public void setFulfillingEncounters(Set<Encounter> fulfillingEncounters) {
        this.fulfillingEncounters = fulfillingEncounters;
    }
}

