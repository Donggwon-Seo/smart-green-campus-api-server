package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import jakarta.validation.constraints.NotNull;

public record CreateDataRequest(
        @NotNull Long memberId,
        @NotNull Double value,
        @NotNull String measurement) { }