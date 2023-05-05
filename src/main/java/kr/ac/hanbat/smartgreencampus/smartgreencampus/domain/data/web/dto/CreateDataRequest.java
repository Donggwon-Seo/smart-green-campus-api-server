package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import jakarta.validation.constraints.NotNull;

public record CreateDataRequest(
        @NotNull Long memberId,
        @NotNull String name,
        @NotNull Double value,
        @NotNull String sensingKind,
        @NotNull String building,
        @NotNull String details) { }