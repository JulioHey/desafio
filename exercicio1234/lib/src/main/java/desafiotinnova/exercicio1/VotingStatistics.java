package desafiotinnova.exercicio1;

public class VotingStatistics {
    int totalVoters = 1000;
    int validVoters = 800;
    int whiteVoters = 150;
    int nullVoters = 50;

    public VotingStatistics() {}

    // Caso seja necessário reutlizar com valores diferentes da tabela
    public VotingStatistics(
        int validVoters,
        int whiteVoters,
        int nullVoters
    ) {
        this.validVoters = validVoters;
        this.whiteVoters = whiteVoters;
        this.nullVoters = nullVoters;
        this.totalVoters = validVoters + whiteVoters + nullVoters;
    }

    public int percentageValidVoters() {
        return (validVoters * 100) / totalVoters;
    }

    public int percentageWhiteVoters() {
        System.out.println(whiteVoters*100/totalVoters);
        return (whiteVoters * 100) / totalVoters;
    }

    public int percentageNullVoters() {
        return (nullVoters * 100) / totalVoters;
    }

}
