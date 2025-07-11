import java.util.Objects;

//  TODO: implement player record to encapsulate player behavior
public class TennisGame1 implements TennisGame {

    public static final int WIN_SCORE = 4;
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int WIN_GAP = 2;
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, "player1"))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        if (isPat()) {
            return computePatScore();
        }
        if (!isOnePlayerReachedWinScore()) {
            return computeInProgressGameScore();
        }
        int minusResult = computePlayer1AndPlayer2ScoreGap();
        if (isPlayerOneAdvantage(minusResult)) return "Advantage player1";
        if (isPlayerTwoAdvantage(minusResult)) return "Advantage player2";
        if (isPlayerOneWinner(minusResult)) return "Win for player1";
        return "Win for player2";
    }

    private String computeInProgressGameScore() {
        var score = new StringBuilder();
        int currentScore;
        for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
            if (i == 1) currentScore = player1Score;
            else {
                score.append("-");
                currentScore = player2Score;
            }
            score.append(getLiteralScoreForScore(currentScore));
        }
        return score.toString();
    }

    private static String getLiteralScoreForScore(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new IllegalStateException("Unexpected value: " + score);
        };
    }

    private static boolean isPlayerOneWinner(int minusResult) {
        return minusResult >= WIN_GAP;
    }

    private static boolean isPlayerTwoAdvantage(int minusResult) {
        return minusResult == -1;
    }

    private static boolean isPlayerOneAdvantage(int minusResult) {
        return minusResult == 1;
    }

    private int computePlayer1AndPlayer2ScoreGap() {
        return player1Score - player2Score;
    }

    private boolean isOnePlayerReachedWinScore() {
        return player1Score >= WIN_SCORE || player2Score >= WIN_SCORE;
    }

    private String computePatScore() {
        String score;
        score = switch (player1Score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
        return score;
    }

    private boolean isPat() {
        return player1Score == player2Score;
    }
}
