package bruh.util.builders;

import bruh.entity.Tournament;
import bruh.model.TournamentDto;
import bruh.util.validators.TournamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TournamentBuilder {
    private final TournamentValidator tournamentValidator;

    @Autowired
    public TournamentBuilder(TournamentValidator tournamentValidator) {
        this.tournamentValidator = tournamentValidator;
    }


    public Tournament buildTournamentByTournamentDto(TournamentDto tournamentDto) {
        Tournament tournament = new Tournament(tournamentDto.getName(), tournamentDto.getDescription(), tournamentDto.getPlace(),
                tournamentDto.getStartDate(), tournamentDto.getEndDate());
        tournamentValidator.validateParticipants(tournamentDto.getParticipants());

        if (tournamentDto.getMode() != null) {
            tournament.setMode(tournamentDto.getMode());
        }

        if (tournamentDto.getPlace() != null) {
            tournament.setPlace(tournamentDto.getPlace());
        }

        if (tournamentDto.getTournamentLevel() != null) {
            tournament.setTournamentLevel(tournamentDto.getTournamentLevel());
        }

        if (tournamentDto.getParticipants() != 32) {
            tournament.setParticipants(tournamentDto.getParticipants());
        }

        if (tournamentDto.getScenario() != null) {
            tournament.setScenario(tournamentDto.getScenario());
        }
        return tournament;
    }

    public TournamentDto buildTournamentDtoByTournament(Tournament tournament) {
        return new TournamentDto(tournament.getName(), tournament.getDescription(), tournament.getMode(), tournament.getPlace(),
                tournament.getStartDate(), tournament.getEndDate(), tournament.getTournamentLevel(),
                tournament.getParticipants(), tournament.getScenario(), tournament.getTournamentStatus(), null,
                tournament.getCurrentParticipants());
    }
}
