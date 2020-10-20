package pl.gregorymartin.b01.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public
class Audit {

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    void prePersist(){
        createdOn = LocalDateTime.now();
    }
    @PreUpdate
    void preMerge(){
        updatedOn = LocalDateTime.now();
    }
    public String formatCreatedOn(){
        long numberOfDays = ChronoUnit.DAYS.between(createdOn,LocalDateTime.now());
        String date = numberOfDays + " days ago";
        if(numberOfDays == 0){
            date = "Today";
        }
        if (numberOfDays >= 7){
            return "Week ago";
        }
        if (numberOfDays >= 14){
            long numberOfWeeks = ChronoUnit.WEEKS.between(createdOn,LocalDateTime.now());
            return numberOfWeeks + "weeks ago";
        }
        if (numberOfDays >= 70){
            return createdOn.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm a")).toUpperCase();
        }
        return date;
    }
    public String formatUpdatedOn(){
        return updatedOn.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm a")).toUpperCase();
    }
}
