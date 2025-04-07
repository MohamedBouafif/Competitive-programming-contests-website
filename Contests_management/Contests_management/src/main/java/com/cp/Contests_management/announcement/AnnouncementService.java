package com.cp.Contests_management.announcement;

import com.cp.Contests_management.competition.Competition;
import com.cp.Contests_management.competition.CompetitionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AnnouncementService {



        private final AnnouncementRepository announcementRepository;
        private final CompetitionRepository competitionRepository;

        public AnnouncementService(AnnouncementRepository announcementRepository, CompetitionRepository competitionRepository) {
            this.announcementRepository = announcementRepository;
            this.competitionRepository = competitionRepository;
        }

        @Transactional
        public AnnouncementResponseDto createAnnouncement(AnnouncementDto announcementDto) {
            Competition competition = competitionRepository.findById(announcementDto.getCompetitionId())
                    .orElseThrow(() -> new RuntimeException("Competition not found with id: " + announcementDto.getCompetitionId()));

            Announcement announcement = new Announcement();
            announcement.setCompetition(competition);
            announcement.setContent(announcementDto.getContent());

            Announcement savedAnnouncement = announcementRepository.save(announcement);
            return mapToResponseDto(savedAnnouncement);
        }

        public List<AnnouncementResponseDto> getAnnouncementsByCompetitionId(Integer competitionId) {
            List<Announcement> announcements = announcementRepository.findByCompetitionId(competitionId);
            return announcements.stream().map(this::mapToResponseDto).collect(Collectors.toList());
        }

        private AnnouncementResponseDto mapToResponseDto(Announcement announcement) {
            AnnouncementResponseDto responseDto = new AnnouncementResponseDto();
            responseDto.setId(announcement.getId());
            responseDto.setCompetitionId(announcement.getCompetition().getId());
            responseDto.setContent(announcement.getContent());
            return responseDto;
        }
    }
