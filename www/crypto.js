var Crypto = {
  md5: function(str, success, failure) {
    cordova.exec(
      success,
      failure,
      'Crypto',
      'md5',
      [str]
    );
  }
};

module.exports = Crypto;
