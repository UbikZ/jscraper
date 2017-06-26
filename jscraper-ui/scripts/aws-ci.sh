#!/bin/bash

set -e

while read line || [ -n "$line" ];
do
    file=$(echo ${line} | awk '{print $1}')
    metadata=$(echo ${line} | awk '{print $2}')

    echo "Check file ${line}..."
    # Get current object
    set +e
    aws s3api head-object --bucket ${S3_BUCKET_NAME} --key ${file} > metadata
    set -e

    awsChecksum=$(node ./scripts/awsChecksum.js metadata)
    currChecksum=$(node ./scripts/currentChecksum.js ./static/${file})

    echo "Current ${currChecksum} vs Aws ${awsChecksum}"
    [ "$currChecksum" != "$awsChecksum" ] && echo "Need to update." || echo "Up to date."

    if [ "$currChecksum" != "$awsChecksum" ]; then
        invalidate=1
        echo "Pushing file ${file} to s3"
        # Push files
        aws s3 cp ./static/${file} s3://${S3_BUCKET_NAME}/${file} \
            --metadata md5chksum=${currChecksum},${metadata}

    fi
done < manifest