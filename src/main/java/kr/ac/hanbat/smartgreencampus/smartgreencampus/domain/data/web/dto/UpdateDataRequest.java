package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateDataRequest(
        @NotNull Long dataId,
        @NotNull Double value) { }