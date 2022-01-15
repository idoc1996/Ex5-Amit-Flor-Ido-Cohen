public class Employee extends Client {
    private int rank;

    public Employee(String firstName, String lastName, String userName, String password, int rank,boolean isVip) {
        super(firstName, lastName, userName, password,isVip);
        this.rank = rank;
    }

    @Override
    public String toString() {
        return super.toString()+ "("+rankToString(this.rank)+")";
    }
    private String rankToString (int workerRank){
        String rankToString;
        if ( workerRank== 1){
            rankToString = "regular worker";
        }else if (workerRank==2){
            rankToString = "director";
        }else {
            rankToString="board member";
        }
        return rankToString;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}