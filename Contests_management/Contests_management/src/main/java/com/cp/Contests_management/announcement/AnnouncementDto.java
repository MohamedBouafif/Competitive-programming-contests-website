package com.cp.Contests_management.announcement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AnnouncementDto {


        @NotNull
        private Integer competitionId;

        @NotBlank
        private String content;


}
