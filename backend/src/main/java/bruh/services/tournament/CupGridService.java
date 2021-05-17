package bruh.services.tournament;

import bruh.entity.Tournament;
import bruh.entity.TournamentParticipant;
import bruh.entity.User;
import bruh.exceptions.InvalidCredentialsException;
import bruh.exceptions.TournamentIsAlreadyFinishedException;
import bruh.exceptions.TournamentNotFoundException;
import bruh.model.UserDto;
import bruh.repo.ITournamentParticipantRepo;
import bruh.repo.ITournamentRepo;
import bruh.repo.IUserRepo;
import bruh.util.enums.TournamentStatus;
import bruh.util.validators.TournamentValidator;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static bruh.util.constants.LoggerMessages.TOURNAMENT_WAS_NOT_FOUND;
import static bruh.util.constants.LoggerMessages.USER_WAS_NOT_FOUND;

@Service
public class CupGridService {
    private final ITournamentRepo iTournamentRepo;
    private final IUserRepo iUserRepo;
    private final ITournamentParticipantRepo iTournamentParticipantRepo;
    private final TournamentValidator tournamentValidator;

    public CupGridService(ITournamentRepo iTournamentRepo, IUserRepo iUserRepo,
                          ITournamentParticipantRepo iTournamentParticipantRepo, TournamentValidator tournamentValidator) {
        this.iTournamentRepo = iTournamentRepo;
        this.iUserRepo = iUserRepo;
        this.iTournamentParticipantRepo = iTournamentParticipantRepo;
        this.tournamentValidator = tournamentValidator;
    }

    public List<UserDto> getStarterCupGrid(String tournamentName) {
        Tournament tournament = iTournamentRepo.findByName(tournamentName).orElseThrow(()
                -> new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournamentName)));
        if (tournament.getTournamentStatus() == TournamentStatus.FINISHED) {
            throw new TournamentIsAlreadyFinishedException(String.format("Tournament %S is already finished", tournamentName));
        }
        long tournamentId = tournament.getId();
        List<TournamentParticipant> participantDtos = iTournamentParticipantRepo.findByIdTournament(tournamentId);
        List<Integer> usersId = participantDtos
                .stream()
                .map(tournamentParticipant -> Integer.valueOf(tournamentParticipant.getIdUser().toString()))
                .collect(Collectors.toList());
        List<User> userList = iUserRepo.findUsersByIdIn(usersId);

        return userList
                .stream()
                .map(user -> new UserDto(user.getLogin(), user.getPassword(), user.getRole(), user.getGames(),
                        user.getDraws(), user.getLoses(), user.getWins(), user.getPower()))
                .collect(Collectors.toList());
    }

    public List<UserDto> getFinalCupGrid(List<UserDto> userList, String tournamentName) {
        Tournament tournament = iTournamentRepo.findByName(tournamentName).orElseThrow(()
                -> new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournamentName)));
        tournament.setTournamentStatus(TournamentStatus.IN_PROGRESS);
        int iterations = tournamentValidator.validateParticipants(countNumberOfParticipants(tournamentName));
        List<UserDto> nextStageList = new ArrayList<>(userList);
        for (int i = 0; i < iterations; i++) {
            nextStageList = getNextStageList(nextStageList);
            userList.addAll(nextStageList);
        }
        tournament.setTournamentStatus(TournamentStatus.FINISHED);
        return userList;
    }

    private List<UserDto> getNextStageList(List<UserDto> userList) {
        List<UserDto> nextStage = new ArrayList<>();
        for (int i = 0, k = 1; i < userList.size(); i += 2, k += 2) {
            if (userList.size() == 1) {
                break;
            }
            int finalI = i;
            User user1 = iUserRepo.findUserByLogin(userList.get(i).getLogin()).orElseThrow(()
                    -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, userList.get(finalI).getLogin())));
            int finalK = k;
            User user2 = iUserRepo.findUserByLogin(userList.get(k).getLogin()).orElseThrow(()
                    -> new InvalidCredentialsException(String.format(USER_WAS_NOT_FOUND, userList.get(finalK).getLogin())));
            if (fight(user1, user2)) {
                nextStage.add(userList.get(i));
            } else {
                nextStage.add(userList.get(k));
            }
        }
        return nextStage;
    }

    private boolean fight(User userDto1, User userDto2) {
        if (userDto1.getPower() > userDto2.getPower()) {
            userDto1.setGames(userDto1.getGames() + 1);
            userDto1.setWins(userDto1.getWins() + 1);
            userDto2.setGames(userDto2.getGames() + 1);
            userDto2.setLoses(userDto2.getLoses() + 1);
            return true;
        } else if (userDto1.getPower() == userDto2.getPower()) {
            userDto1.setGames(userDto1.getGames() + 1);
            userDto1.setDraws(userDto1.getDraws() + 1);
            userDto2.setGames(userDto2.getGames() + 1);
            userDto2.setDraws(userDto2.getDraws() + 1);
            return true;
        }
        userDto2.setGames(userDto2.getGames() + 1);
        userDto2.setWins(userDto2.getWins() + 1);
        userDto1.setGames(userDto1.getGames() + 1);
        userDto1.setLoses(userDto1.getLoses() + 1);
        return false;
    }

    private int countNumberOfParticipants(String tournamentName) {
        return iTournamentRepo.findByName(tournamentName).orElseThrow(()
                -> new TournamentNotFoundException(String.format(TOURNAMENT_WAS_NOT_FOUND, tournamentName))).getParticipants();
    }
}
