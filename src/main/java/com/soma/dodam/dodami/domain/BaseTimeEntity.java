package com.soma.dodam.dodami.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @ApiModelProperty(readOnly = true)
    @CreatedDate
    private LocalDateTime createdDate;

    @ApiModelProperty(readOnly = true)
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
