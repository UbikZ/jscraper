#!/bin/bash

set -e

while read line;
do
    file=$(echo ${line} | awk '{print $1}')
    metadata=$(echo ${line} | awk '{print $2}')

    # Get current object
    aws s3api head-object --bucket ${S3_BUCKET_NAME} --key ${file} > metadata

    awsChecksum = $(node ./scripts/awsChecksum.js metadata)
    currCheckum = $(node ./scripts/currentChecksum.js ./static/${file})

    echo "Metadata for ${file}"
    cat metadata
    echo "Current Checksum for ${file}"
    cat metadata

    if [[ ${currChecksum} != ${awsChecksum} ]]; then
        # Push files
        aws s3 cp ./static/${file} s3://${S3_BUCKET_NAME}/${file} \
            --metadata md5chksum=${currChecksum},${metadata} \
            --content-md5 ${currChecksum}

        # Invalidate Cloud Front Cache
        aws cloudfront create-invalidation --distribution-id ${CF_DISTRIBUTION_ID} --paths "/*";
    fi
done < manifest
