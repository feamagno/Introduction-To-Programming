import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        //create bank account
        SafeBankAccount account = createAccount(in);

        //shows menu
        printMenu();

        //selects and executes an operation
        executeOperation(in, account);

        //returns balance
        showBalance(account);

        in.nextLine();
    }

    private static void printMenu() {
        //prints menu options

        System.out.println("(D)DEPOSIT <amount>: Deposits amount");
        System.out.println("(W)WITHDRAW <amount>: Withdraws amount");
        System.out.println("(G)GET_INTEREST: Returns yearly interest");
        System.out.println("(A)APPLY_INTEREST: Applies interest");
    }
    private static void executeOperation(Scanner input, SafeBankAccount account){
        //reads users input
        //executes relevant option

        String option = input.next().toUpperCase();

        switch (option){
            case ("D"):
            case ("DEPOSIT"):
                processDeposit(input,account);
                break;
            case ("W"):
            case ("WITHDRAW"):
                processWithdraw(input,account);
                break;
            case ("G"):
            case ("GET_INTEREST"):
                processInterest(account);
                break;
            case ("A"):
            case ("APPLY_INTEREST"):
                processApplyInterest(account);
                break;
            default:
                showUnknownOption(option);
                break;
        }
    }

    private static int getIntValue(Scanner input,String msg){
        //prints a message and returns users answer

        System.out.println(msg);
        int value = input.nextInt();
        input.nextLine();

        return value;
    }

    private static void processInterest(SafeBankAccount account){
        System.out.println("TEU INTERESSE?(SLA NN SEI TRADUZIR) É: "+account.computeInterest()+" DOLS");
    }

    private static void processApplyInterest(SafeBankAccount account){
        account.applyInterest();
        System.out.println("AEE NEGAO TU BOTO "+account.computeInterest()+" DOLS NA XOTA");
    }

    private static void showUnknownOption(String msg){
        System.out.println("O SEU RETARDADO SABE LER NÃO?? "+msg+" NÃO TA LISTA");
    }

    private static void showBalance(SafeBankAccount account){
        System.out.println("");
        System.out.println("BOA NEGAO TU TA COM "+account.getBalance()+" DOLS NO BOLSO");
        System.out.println("");
    }
    private static void processDeposit(Scanner input, SafeBankAccount account){
        //asks amount user wants to deposit
        //informs user if deposit is successful

        int amount = getIntValue(input,"QUANTO QUER BOTA NA XOTA(CONTA)??" );

        if (amount >= 0){
            account.deposit(amount);
            System.out.println("AE PORRA TU BOTOU "+amount+" DOLS NA XOTA(CONTA)" );
        }
        else {
            System.out.println("OOO MULEKE COMO Q TU VAI BOTA "+amount+" DOLS NA XOTA(CONTA)??? NAO ROLA");
        }
    }
    private static void processWithdraw(Scanner input, SafeBankAccount account) {
        //asks amount user wants to withdraw
        //informs user if withdraw is successful

        int amount = getIntValue(input, "QUANTO QUER TIRA DA XOTA(CONTA)??");

        if (amount >= 0) {
            account.withdraw(amount);

            if (amount <= account.getBalance()) {
                System.out.println("AE PORRA TU TIROU "+amount+" DOLS DA XOTA(CONTA)");
            } else {
                System.out.println("OOO MULEKE COMO Q TU VAI TIRA "+amount+" DOLS SE TU SÓ TEM "+account.getBalance()+" DOLS TOMA AINDA UMA MULTA DE "+account.getFine()+" DOLS");
            }
        }
        else{
            System.out.println("OOO MULEKE COMO Q TU VAI TIRA "+amount+" ????");
        }
    }
    private static SafeBankAccount createAccount(Scanner input){
        //asks user initial amount
        //returns bank account

        System.out.println();
        int initial = getIntValue(input,"PenisAccount (in euros, positive): ");

        return new SafeBankAccount(Math.max(initial, 0));
    }
}
