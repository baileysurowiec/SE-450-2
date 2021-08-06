package controller;

import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

// "toolbar actions"
    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveMouseMode());

        // events for undo and redo
        uiModule.addEvent(EventName.UNDO, () -> new DoUndo().run());
        uiModule.addEvent(EventName.REDO, () -> new DoRedo().run());
        // delete
        uiModule.addEvent(EventName.DELETE, () -> new DeleteShapeCommand().run());
        // copy & paste
        uiModule.addEvent(EventName.COPY, () -> new CopyShapesCommand().run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteShapesCommand().run());
        // group & ungroup
        uiModule.addEvent(EventName.GROUP, () -> new GroupShapesCommand().run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UnGroupShapesCommand().run());

    }
}
