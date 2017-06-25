const fs = require('fs');
const crypto = require('crypto');
const path = require('path');
const filePath = path.join(process.cwd(), process.argv[2]);

fs.createReadStream(filePath).pipe(crypto.createHash('md5').setEncoding('base64')).on('finish', function () {
  console.log(this.read());
});