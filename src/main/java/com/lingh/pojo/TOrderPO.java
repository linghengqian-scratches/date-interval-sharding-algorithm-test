package com.lingh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TOrderPO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String testId;
    private LocalDateTime createTime;
}
