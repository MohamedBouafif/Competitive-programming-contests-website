package com.cp.Contests_management.announcement;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annoucements")
public class AnnouncementController {


    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping
    public AnnouncementResponseDto createAnnouncement(@Valid @RequestBody AnnouncementDto announcementDto) {
        return announcementService.createAnnouncement(announcementDto);
    }

    @GetMapping("/competition/{competitionId}")
    public List<AnnouncementResponseDto> getAnnouncementsByCompetition(@PathVariable Integer competitionId) {
        return announcementService.getAnnouncementsByCompetitionId(competitionId);
    }
}
