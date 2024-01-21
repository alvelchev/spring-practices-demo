package com.springpageable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEventDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID nextAutoPlanSubJobUuid;
}
