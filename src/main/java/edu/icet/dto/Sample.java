package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sample {
    private String userId;
    private String id;
    private String title;
    private Boolean completed;
}
