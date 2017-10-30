package Service;

import Data.Channel;

/**
 * Created by Ludwig on 2017-10-30.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
