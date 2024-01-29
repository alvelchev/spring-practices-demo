package com.springpracticesdemo.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEventDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID nextAutoPlanSubJobUuid;
}
