import java.util.Objects;

public class TennisGame1 implements TennisGame {

    public static final int WIN_SCORE = 4;
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
        String score = "";
        if (isPat()) {
            score = computePatScore();
        } else if (isOnePlayerReachedWinScore()) {
            int minusResult = computePlayer1ScoreMinusPlayer2Score();
            if (isPlayerOneAdvantage(minusResult)) score = "Advantage player1";
            else if (isPlayerTwoAdvantage(minusResult)) score = "Advantage player2";
            else if (isPlayerOneWinner(minusResult)) score = "Win for player1";
            else score = "Win for player2";
        } else {
            score = computeInProgressGameScore(score);
        }
        return score;
    }

    private String computeInProgressGameScore(String score) {
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = player1Score;
            else {
                score += "-";
                tempScore = player2Score;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
        return score;
    }

    private static boolean isPlayerOneWinner(int minusResult) {
        return minusResult >= 2;
    }

    private static boolean isPlayerTwoAdvantage(int minusResult) {
        return minusResult == -1;
    }

    private static boolean isPlayerOneAdvantage(int minusResult) {
        return minusResult == 1;
    }

    private int computePlayer1ScoreMinusPlayer2Score() {
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
