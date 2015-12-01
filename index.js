var React, { GoogleAnalyticsBridge } = require('react-native');

module.exports = {
  var _trackerId = null;
  setTrackerId(trackerId) {
    _trackerId = trackerId;
  }

  trackEvent(eventName, eventAction, cb) {
    GoogleAnalyticsBridge.trackEvent(_trackerId, eventName, eventAction, cb);
  }
};
