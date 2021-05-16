package bruh.util.builders;

import bruh.entity.Tournament;
import bruh.exceptions.InvalidParticipantsNumberException;
import bruh.model.TournamentDto;
import org.springframework.stereotype.Component;

@Component
public class TournamentBuilder {

    public Tournament buildTournamentByTournamentDto(TournamentDto tournamentDto) {
        Tournament tournament = new Tournament(tournamentDto.getName(), tournamentDto.getDescription(), tournamentDto.getPlace(),
                tournamentDto.getStartDate(), tournamentDto.getEndDate());
        validateParticipants(tournamentDto.getParticipants());

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
                tournament.getParticipants());
    }

    private void validateParticipants(int participantsDto) {
        int i = 128;
        while (i / 2 != 0) {
            int checkNumber = i / 2;
            if (participantsDto == checkNumber) {
                return;
            }
            i /= 2;
        }
        throw new InvalidParticipantsNumberException(
                String.format("%s is unacceptable value (acceptable values = 4,8,16,32,64,128)", participantsDto));
    }
}
