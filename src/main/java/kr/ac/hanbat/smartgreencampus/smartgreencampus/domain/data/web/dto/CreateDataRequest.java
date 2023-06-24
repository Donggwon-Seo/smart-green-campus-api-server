package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import jakarta.validation.constraints.NotNull;

public record CreateDataRequest(
        @NotNull Double value,
        @NotNull String measurement) { }