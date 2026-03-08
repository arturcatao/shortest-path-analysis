/**Esse código utiliza a implementação de fibonacci heap encontrada no repositório abaixo:
* https://github.com/j4orz/iruka.git
* Adicionei modificações para poder utilizar esse algoritmo em um djikstra, no qual eu tive que adicionar ao nó os atributos de vertex
* e priority, sendo eles obrigatórios para criar o nó, alterei o método decrease key para receber o vertex e a priority, como parâmetro,
* para poder achar o nó. E criei um Map para poder acessar os nós, passando como identificador único o vertex
* 
*/
package heaps;
import java.util.*;
public class FibonacciHeap implements MyPriorityQueue {

    private FibonacciNode minRoot, head;
    private int size;
    private Map<Integer, FibonacciNode> nodeMap;

    public FibonacciHeap() {
        this.nodeMap = new HashMap<>();
    }
    

    /**
     * Esse método é a inserção preguiçosa, no qual eu só adiciono o novo nó na lista de raízes
     * sem reorganizar, garantindo O(1), e atualiza o ponteiro de menor nó, se necessário
     */
    @Override
    public void insert(int vertex, int priority) {
        FibonacciNode newNode = new FibonacciNode(vertex, priority);
        nodeMap.put(vertex, newNode);
        if(head != null) {
            newNode.sibling = head;
            head.prevSibling = newNode;
        }

        head = newNode;
        size++;
        if(minRoot == null || head.priority < minRoot.priority) {
            minRoot = head;
        }
    }

    /**
     * Esse método de extração realiza três operações:
     * 1. Remove o nó de menor prioridade da lista de raízes, utilizando o método privado removeSmallestRoot()
     * 2. Joga todos os filhos do nó removido na lista de raízes
     * 3. Chama o método consolidate() para ajeitar esses nós que foram jogados
     */
    @Override
    public int extractMin() {
        FibonacciNode min = removeSmallestRoot();
        if(min == null) return -1;
        size--;
        nodeMap.remove(min.vertex);

        if(min.child != null) {
            FibonacciNode child = min.child;
            FibonacciNode lastChild = null;

            while(child != null) {
                lastChild = child;
                child.parent = null;
                child = child.sibling;
            }

            if(head != null && lastChild != null) {
                lastChild.sibling = head;
                head.prevSibling = lastChild;
            }

            head = min.child;
        }
        head = consolidate();
        recalculateMin();
        return min.vertex;
    }


    /**
     * Diminui a prioridade de um nó, e verifica se ainda segue a propriedade do Heap, se seguir apenas atualiza o valor
     * se não, corta o nó e o joga na lista de raízes utilizando o cut, e chama o cascadingCut para propagar o impacto da remoção
     */
    @Override
    public void decreaseKey(int vertex, int newPriority) {
        FibonacciNode node = nodeMap.get(vertex);
        if(node == null || newPriority >= node.priority) return;

        node.priority = newPriority;
        if(node.parent != null && node.priority < node.parent.priority) {
            cut(node.parent, node);
            cascadingCut(node.parent);
        }

        if(minRoot == null || node.priority < minRoot.priority){
            minRoot = node;
        }
    }

    /**
     * Método simples de verificar se a árvore está vazia
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
    /**
     * Percorre a lista de raízes procurando o nó de menor prioridade e o retira da lista
     * @return o nó removido
     */
    private FibonacciNode removeSmallestRoot() {
        if(head == null) return null;
        FibonacciNode min = minRoot;

        if(min.prevSibling != null) {
            min.prevSibling.sibling = min.sibling;
        } else {
            head = min.sibling;
        }

        if(min.sibling != null) {
            min.sibling.prevSibling = min.prevSibling;
        }

        min.sibling = null;
        min.prevSibling = null;
        return min;
    }

    /**
     * Percorre a lista das raízes para achar a nova raíz mínima
     */
    private void recalculateMin() {
        if(head == null) {
            minRoot = null;
            return;
        }
        
        FibonacciNode cur = head.sibling;
        FibonacciNode min = head;

        while(cur != null) {
            if(cur.priority < min.priority) min = cur;
            cur = cur.sibling;
        }
        minRoot = min;
    }

    /**
     * Após o extractMin jogar os filhos na lista de raízes, esse método vai garantir que não existam duas árvores com o mesmo grau
     * utilizando duas etapas para esse processo:
     * 1. Distribui as árvores em buckets baseado no seu grau, se já existir alguma com aquele grau, as árvores serão unidas, utilizando
     * o método linkTrees, e o resultado da união vai para o próximo grau
     * 2. Percorre os buckets e reconstrói a lista de raízes com as árvores consolidadas
     * 
     * @return
     */
    private FibonacciNode consolidate() {
        if(head == null) return null;
        int maxDegree = (int) Math.ceil(Math.log(size + 1)/Math.log(2)) + 2;
        FibonacciNode[] buckets = new FibonacciNode[maxDegree];

        FibonacciNode cur = head;
        while(cur != null) {
            FibonacciNode next = cur.sibling;
            cur.sibling = null;
            cur.prevSibling = null;
            cur.parent = null;
            int degree = cur.degree;
            while(buckets[degree] != null) {
                FibonacciNode other = buckets[degree];
                buckets[degree] = null;

                if(cur.priority > other.priority) {
                    FibonacciNode temp = cur;
                    cur = other;
                    other = temp;
                }
                linkTrees(cur, other);
                degree++;
            }
            buckets[degree] = cur;
            cur = next;
        }

        FibonacciNode newHead = null;
        for(FibonacciNode tree : buckets) {
            if(tree == null) continue;
            tree.sibling = null;
            tree.prevSibling = null;

            if(newHead == null) {
                newHead = tree;
            } else {
                tree.sibling = newHead;
                newHead.prevSibling = tree;
                newHead = tree;
            }
        }
        return newHead;
    }

    /**
     * Une duas árvores que possuem o mesmo grau, a que tiver a menor prioridade vai virar a raíz e a outra vai virar filha dela
     * @param treeA
     * @param treeB
     * @return
     */
    private FibonacciNode linkTrees(FibonacciNode treeA, FibonacciNode treeB) {
        treeB.parent = treeA;
        treeB.sibling = treeA.child;
        if(treeA.child != null) {
            treeA.child.prevSibling = treeB;
        }
            treeB.prevSibling = null;
            treeB.mark = false;
            treeA.child = treeB;
            treeA.degree++;
            treeA.sibling = null;

        return treeA;
    }

    /**
     * Desconecta o filho do pai e tranfosrma ele em uma raiz na lista de raizes, limpa o mark do filho, e decrementa o grau do pai
     * @param parent
     * @param child
     */
    private void cut(FibonacciNode parent, FibonacciNode child) {
        if(parent.child == child) {
            parent.child = child.sibling;
            if(child.sibling != null) child.sibling.prevSibling = null;
        } else {
            if(child.prevSibling != null) {
                child.prevSibling.sibling = child.sibling;
            }
            if(child.sibling != null) {
                child.sibling.prevSibling = child.prevSibling;
            }
        }

        parent.degree--;
        child.parent = null;
        child.mark = false;
        child.sibling = null;
        child.prevSibling = null;

        if(head != null) {
            child.sibling = head;
            head.prevSibling = child;
        }
        head = child;
    }
    /**
     * Propaga para cima até encontrar um nó não marcado ou raiz, garantindo que as árvores não degenerem
     * Verifica se o nó foi marcado, e se tiver perdido o primeiro filho, marca ele como True. Se o mark do nó já for True,
     * e ele for perder outro filho, então vai cortar o nó e o colocar na lista de raízes.
     * @param parent
     */
    private void cascadingCut(FibonacciNode parent) {
        if(parent == null || parent.parent == null) return;

        if(!parent.mark) {
            parent.mark = true;
        }
        else {
            cut(parent.parent, parent);
            cascadingCut(parent.parent);
        }
    }
    
}

class FibonacciNode {
    public int vertex, degree, priority;
    public boolean mark;
    public FibonacciNode parent, child, sibling, prevSibling;

    public FibonacciNode (int vertex, int priority){
        this.vertex = vertex;
        this.priority = priority;
        this.degree = 0;
        this.mark = false;
    }
}