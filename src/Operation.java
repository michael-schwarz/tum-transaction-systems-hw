/**
 * Created by michael on 25.04.17.
 */
public class Operation {
    public enum OperationType {R, W, C, A};

    private OperationType operationType;
    private int transaction;
    private char data;

    public Operation(OperationType operationType,int transaction,char data){
        this.operationType = operationType;
        this.transaction = transaction;
        this.data = data;
    }

    public int getTransaction(){
        return transaction;
    }

    /**
     * Check if this operation is in conflict with another operation
     * @param other other operation
     * @return true iff there is a conflict
     */
    public boolean isInConflict(Operation other){
        return (data == other.data) &&
                (operationType == OperationType.W || other.operationType == OperationType.W) &&
                (other.transaction != transaction);
    }

    @Override
    public String toString(){
        return operationType.name() + transaction + data + " ";
    }
}
