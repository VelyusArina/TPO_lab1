package task2;

import java.util.ArrayList;
import java.util.List;

public class BPlusTreeNode {
    private static final int MAX_KEYS = 6;

    private List<Integer> keys;
    private List<Object> values;
    private List<BPlusTreeNode> children;
    private BPlusTreeNode parent;
    private BPlusTreeNode next; // Для связи листовых узлов
    private boolean isLeaf;
    private BPlusTree root; // Добавляем поле для корневого узла

    public BPlusTreeNode(boolean isLeaf, BPlusTree root) {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.children = new ArrayList<>();
        this.isLeaf = isLeaf;
        this.root = root; // Устанавливаем ссылку на корневой узел
    }

    public void insert(int key, Object value) {
        if (isLeaf) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            keys.add(index, key);
            values.add(index, value);
            if (keys.size() > MAX_KEYS) {
                splitLeaf();
            }
        } else {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            children.get(index).insert(key, value);
            if (children.get(index).keys.size() > MAX_KEYS) {
                splitNode(index, root);
            }
        }
    }

    private void splitLeaf() {
        int midIndex = keys.size() / 2;
        BPlusTreeNode newLeafNode = new BPlusTreeNode(true, this.root); // Pass root to the new leaf node
        newLeafNode.keys.addAll(keys.subList(midIndex, keys.size()));
        newLeafNode.values.addAll(values.subList(midIndex, values.size()));
        keys.subList(midIndex, keys.size()).clear();
        values.subList(midIndex, values.size()).clear();

        if (this.next != null) {
            this.next.parent = newLeafNode;
            newLeafNode.next = this.next;
        }
        this.next = newLeafNode;

        if (this.parent != null) {
            newLeafNode.parent = this.parent;
            int index = this.parent.children.indexOf(this);
            this.parent.keys.add(index, newLeafNode.keys.get(0));
            this.parent.children.add(index + 1, newLeafNode);
            if (this.parent.keys.size() > MAX_KEYS) {
                this.parent.splitNode(index, this.root);
            }
        } else {
            BPlusTreeNode newRoot = new BPlusTreeNode(false, this.root);
            newRoot.keys.add(newLeafNode.keys.get(0));
            newRoot.children.add(this);
            newRoot.children.add(newLeafNode);
            ((BPlusTree) this.root).setRoot(newRoot); // Update the root if it's a new tree
        }
    }


    private void splitNode(int index, BPlusTree root) {
        int midIndex = keys.size() / 2;
        BPlusTreeNode newInternalNode = new BPlusTreeNode(false, root);
        newInternalNode.keys.addAll(keys.subList(midIndex + 1, keys.size())); // Exclude the key being promoted
        newInternalNode.children.addAll(children.subList(midIndex + 1, children.size()));
        keys.subList(midIndex, keys.size()).clear();
        children.subList(midIndex + 1, children.size()).clear();

        if (this.parent != null) {
            int parentIndex = this.parent.children.indexOf(this);
            this.parent.children.add(parentIndex + 1, newInternalNode);
            this.parent.keys.add(parentIndex, keys.get(midIndex)); // Promote the middle key to the parent
            this.parent.children.remove(this);
        } else {
            parent = new BPlusTreeNode(false, root);
            parent.keys.add(keys.get(midIndex));
            parent.children.add(this);
            parent.children.add(newInternalNode);
            root.setRoot(parent); // Update the root if it's a new tree
        }
        if (this == root.getRoot()) {
            BPlusTreeNode newRoot = new BPlusTreeNode(false, root);
            newRoot.keys.add(keys.get(midIndex));
            newRoot.children.add(this);
            newRoot.children.add(newInternalNode);
            root.setRoot(newRoot);
        }
    }



    public Object search(int key) {
        if (isLeaf) {
            int index = keys.indexOf(key);
            return (index != -1) ? values.get(index) : null;
        } else {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            return children.get(index).search(key);
        }
    }

    public void remove(int key) {
        if (isLeaf) {
            int index = keys.indexOf(key);
            if (index != -1) {
                keys.remove(index);
                values.remove(index);
            }
        } else {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            children.get(index).remove(key);
        }
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }
}
