const path = require('path');
const fs = require('fs');
const filePath = path.join(process.cwd(), process.argv[2]);

if (fs.existsSync(filePath)) {
  const contentFile = fs.readFileSync(filePath);
  if (contentFile) {
    try {
      const currMetadata = JSON.parse(contentFile);
      if (currMetadata && currMetadata.Metadata && currMetadata.Metadata.md5chksum) {
        console.log(currMetadata.Metadata.md5chksum);
      }
    } catch (Error) {
    }
  }
}