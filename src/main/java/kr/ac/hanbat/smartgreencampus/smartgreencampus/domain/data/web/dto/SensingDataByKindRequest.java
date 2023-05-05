package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import jakarta.validation.constraints.NotNull;

public record SensingDataByKindRequest(@NotNull String kind) {
}