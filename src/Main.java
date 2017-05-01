import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

/**
 * Created by michael on 25.04.17.
 */
public class Main {
    public static void main(String[] args){
        List<Operation> history = readHistory();

        DirectedGraph<Integer,DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Build nodes
        for(Operation op: history){
            if (!graph.vertexSet().contains(op.getTransaction())){
                graph.addVertex(op.getTransaction());
            }
        }

        // Build edges
        for(int i=0;i < history.size()-1;i++){
            for(int j=i+1;j < history.size();j++){
                if(history.get(i).isInConflict(history.get(j))){
                    graph.addEdge(history.get(i).getTransaction(), history.get(j).getTransaction());
                }
            }
        }

        CycleDetector<Integer,DefaultEdge> cd = new CycleDetector<>(graph);

        if(cd.detectCycles()) {
            System.out.println("no");
        } else {
            System.out.println("yes");
        }
    }

    /**
     * Read in the history from stdin
     * @return
     */
    public static List<Operation> readHistory(){
        ArrayList<Operation> operations = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while(true){
            Operation.OperationType operationType = scanner.next().charAt(0) == 'w'? Operation.OperationType.W : Operation.OperationType.R;
            int transaction = scanner.nextInt();

            String dataRaw = scanner.next();
            char data = dataRaw.charAt(0);
            operations.add(new Operation(operationType,transaction,data));

            if(dataRaw.contains(",")){
                break;
            }
        }
        return operations;
    }
}
