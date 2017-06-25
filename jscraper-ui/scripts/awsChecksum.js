const path = require('path');
const fs = require('fs');
const filePath = path.join(process.cwd(), process.argv[2]);

if (fs.exists(filePath)) {
  const currMetadata = require(filePath).Metadata;
  if (currMetadata.md5chksum) {
    console.log(currMetadata.md5chksum);
  }
}