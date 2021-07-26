package controller;

import java.util.Stack;
public class CommandHistory {
    private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
    private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

    public static void add(IUndoable cmd) {
       // System.out.println("added to CH");
        undoStack.push(cmd);
        redoStack.clear();
    }

    public static boolean undo() {
//        System.out.println("did undo in CH");
        boolean result = !undoStack.empty();
        if (result) {
            IUndoable c = undoStack.pop();
            redoStack.push(c);
            c.undo();
        }
        return result;
    }

    public static boolean redo() {
//        System.out.println("did redo in CH");
        boolean result = !redoStack.empty();
        if (result) {
            IUndoable c = redoStack.pop();
            undoStack.push(c);
            c.redo();

        }
        return result;
    }
}

