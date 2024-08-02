package listeners;

import models.SimulatorState;

public interface StateListener {

    void onStateChange(SimulatorState state);

}
