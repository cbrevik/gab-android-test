var GoogleAnalyticsBridge = require('react-native').NativeModules.GoogleAnalyticsBridge;

module.exports = {
  trackEvent(trackerId, eventName, eventAction, cb) {
    GoogleAnalyticsBridge.trackEvent(trackerId, eventName, eventAction, cb);
  }
};
