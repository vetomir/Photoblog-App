package pl.gregorymartin.b01.core.model;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Getter
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
        return createdOn.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm a"));
    }
    public String formatUpdatedOn(){
        return updatedOn.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm a"));
    }
}
