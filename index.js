var React, { GoogleAnalyticsBridge } = require('react-native');

module.exports = {
  trackEvent(eventName, cb) {
    GoogleAnalyticsBridge.trackEvent(eventName, cb);
  }
};
